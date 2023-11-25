package uk.whitedev.rtp.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import uk.whitedev.rtp.RandomTP;
import uk.whitedev.rtp.objects.ItemsObj;
import uk.whitedev.rtp.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GuiTask {
    private final Plugin plugin = RandomTP.getPlugin(RandomTP.class);
    private final FileConfiguration config = plugin.getConfig();

    public Inventory createGui() {
        Inventory inv = Bukkit.createInventory(null, config.getInt("gui_size_value") * 9, ColorUtils.formatColor(config.getString("gui_name")));
        ItemStack empty = new ItemStack(Material.getMaterial(config.getString("empty_item")));
        ItemMeta meta = empty.getItemMeta();
        meta.setDisplayName(config.getString("empty_item_name"));
        empty.setItemMeta(meta);

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, empty);
        }
        generateItems(inv);
        return inv;
    }

    private void generateItems(Inventory inventory) {
        List<?> items = config.getList("gui_items");
        assert items != null;
        for (Object item : items) {
            if (item instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) item;
                String id = (String) map.get("id");
                Material type = Material.valueOf((String) map.get("type"));
                int amount = (int) map.get("amount");
                ItemStack itemStack = new ItemStack(type, amount);
                ItemMeta meta = itemStack.getItemMeta();
                int slot = config.getInt(id + ".place_in_gui");
                String[] xCords = Objects.requireNonNull(config.getString(id + ".x")).split(";");
                String[] zCords = Objects.requireNonNull(config.getString(id + ".z")).split(";");
                int minX = Integer.parseInt(xCords[0]);
                int maxX = Integer.parseInt(xCords[1]);
                int minZ = Integer.parseInt(zCords[0]);
                int maxZ = Integer.parseInt(zCords[1]);
                String name = config.getString(id + ".name");
                String permissions = config.getString(id + ".permissions");
                List<String> lores = config.getStringList(id + ".lores");
                List<String> enchants = config.getStringList(id + ".enchants");
                meta.setDisplayName(ColorUtils.formatColor(name));
                List<String> newLores = new ArrayList<>();
                for (String lore : lores){
                    newLores.add(ColorUtils.formatColor(lore));
                }
                meta.setLore(newLores);
                if (enchants.size() != 0) {
                    for (String enchant : enchants) {
                        if (!enchant.equals("")) {
                            String[] enchArray = enchant.split(" ");
                            meta.addEnchant(Objects.requireNonNull(Enchantment.getByName(enchArray[0])), Integer.parseInt(enchArray[1]), true);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        }
                    }
                }
                ItemsObj itemsObj = new ItemsObj(minX, maxX, minZ, maxZ, slot, permissions);
                ItemsObj.ALLITEMS.add(itemsObj);
                itemStack.setItemMeta(meta);
                inventory.setItem(slot, itemStack);
            }
        }
    }

}
