package uk.whitedev.rtp.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import uk.whitedev.rtp.tasks.GuiTask;

public class GuiCommand {

    private final CommandSender sender;
    private final GuiTask guiTask = new GuiTask();

    public GuiCommand(CommandSender sender) {
        this.sender = sender;
    }

    public boolean runCommand(){
        if (sender instanceof Player) {
            Inventory inv = guiTask.createGui();
            ((Player) sender).openInventory(inv);
            return true;
        }
        return false;
    }

}