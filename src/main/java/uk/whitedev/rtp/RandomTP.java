package uk.whitedev.rtp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import uk.whitedev.rtp.commands.GuiCommand;
import uk.whitedev.rtp.commands.ReloadCommand;
import uk.whitedev.rtp.listeners.InventoryListener;
import uk.whitedev.rtp.utils.RTPTabCompleter;

import java.util.Locale;

public final class RandomTP extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        initializeModules();
        getLogger().info("RandomTP Plugin - by 0WhiteDev [devs.market] has been enabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        switch (cmd.getName()){
            case "rtp":
                if(args.length != 0) {
                    if ("reload".equals(args[0].toLowerCase(Locale.ROOT))) {
                        return new ReloadCommand(sender).runCommand();
                    }
                }
                return new GuiCommand(sender).runCommand();
        }
        return false;
    }

    @Override
    public void onDisable() {
        getLogger().info("RandomTP Plugin - by 0WhiteDev [devs.market] has been disabled");
    }

    private void initializeModules(){
        PluginCommand rtpCommand =  getCommand("rtp");
        rtpCommand.setExecutor(this);
        rtpCommand.setTabCompleter(new RTPTabCompleter());
        new InventoryListener().registerListeners();
    }
}
