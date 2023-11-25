package uk.whitedev.rtp.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import uk.whitedev.rtp.RandomTP;
import uk.whitedev.rtp.tasks.MessageTask;
import uk.whitedev.rtp.utils.MessageType;

public class ReloadCommand {

    private final CommandSender sender;
    private final Plugin plugin = RandomTP.getPlugin(RandomTP.class);
    private final MessageTask messageTask = new MessageTask();

    public ReloadCommand(CommandSender sender) {
        this.sender = sender;
    }

    public boolean runCommand(){
        if(sender.hasPermission("rtp.admin")) {
            plugin.reloadConfig();
            messageTask.sendMessageToPlayer((Player) sender, "reload-message", MessageType.STRING);
        }else{
            messageTask.sendMessageToPlayer((Player) sender, "no-permissions-message", MessageType.STRING);
        }
        return true;
    }
}