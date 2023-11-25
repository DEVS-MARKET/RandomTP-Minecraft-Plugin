package uk.whitedev.rtp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import uk.whitedev.rtp.RandomTP;
import uk.whitedev.rtp.events.GuiEvent;
import uk.whitedev.rtp.utils.ColorUtils;

public class InventoryListener implements Listener {
    private final Plugin plugin = RandomTP.getPlugin(RandomTP.class);
    private final FileConfiguration config = plugin.getConfig();
    private final GuiEvent guiEvent = new GuiEvent();

    public void registerListeners() {
        InventoryClick();
    }

    private void InventoryClick(){
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent event) {
                if (event.getView().getTitle().equals(ColorUtils.formatColor(config.getString("gui_name")))) {
                    guiEvent.GuiClickEvent(event);
                    event.setCancelled(true);
                }
            }
        }, plugin);
    }

}