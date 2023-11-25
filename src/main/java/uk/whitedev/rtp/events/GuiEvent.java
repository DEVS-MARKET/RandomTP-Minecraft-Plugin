package uk.whitedev.rtp.events;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import uk.whitedev.rtp.RandomTP;
import uk.whitedev.rtp.objects.ItemsObj;
import uk.whitedev.rtp.tasks.MessageTask;
import uk.whitedev.rtp.tasks.TeleportTask;
import uk.whitedev.rtp.utils.MessageType;

import java.util.List;
import java.util.Objects;

public class GuiEvent {
    private final TeleportTask tpTask = new TeleportTask();
    private final Plugin plugin = RandomTP.getPlugin(RandomTP.class);
    private final FileConfiguration config = plugin.getConfig();
    private final MessageTask messageTask = new MessageTask();

    public void GuiClickEvent(InventoryClickEvent event) {
        List<ItemsObj> itemsObjList = ItemsObj.ALLITEMS;
        for (ItemsObj obj : itemsObjList) {
            if (event.getSlot() == obj.getSlot()) {
                Player player = (Player) event.getWhoClicked();
                if(player.hasPermission(obj.getPermissions())) {
                    Location safeLocation = tpTask.getRandomSafeLocation(player.getWorld(), obj.getMinX(), obj.getMaxX(), obj.getMinZ(), obj.getMaxZ());
                    player.teleport(safeLocation);
                    String message = Objects.requireNonNull(config.getString("player-teleported-message"))
                            .replace("%x%", String.valueOf(player.getX()))
                            .replace("%z%", String.valueOf(player.getZ()));
                    messageTask.sendClearMessageToPlayer(player, message);
                }else{
                    messageTask.sendMessageToPlayer(player, "no-permissions-message", MessageType.STRING);
                }
                break;
            }
        }
    }

}
