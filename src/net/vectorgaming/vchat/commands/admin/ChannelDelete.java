
package net.vectorgaming.vchat.commands.admin;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vcore.framework.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class ChannelDelete extends SubCommand
{
    public ChannelDelete()
    {
        super("delete", VChatAPI.getPlugin());
    }

    @Override
    public void run(CommandSender cs, String[] args)
    {
        if(!ChatManager.channelExists(args[0]))
        {
            sendErrorMessage(cs, "Channel "+ChatColor.YELLOW+args[0]+ChatColor.RED+" does not exist.");
            return;
        }

        ChatManager.deleteChannel(args[0]);
        cs.sendMessage(ChatColor.GREEN+"Successfully deleted channel "+ChatColor.YELLOW+args[0]);
    }

    @Override
    public String getDescription()
    {
        return "Deletes a channel";
    }

    @Override
    public String getUsage()
    {
        return "/ch delete <channel>";
    }

    @Override
    public String getPermission()
    {
       return "vchat.delete";
    }

    @Override
    public Integer getMinArgsLength()
    {
        return 1;
    }

    @Override
    public Integer getMaxArgsLength()
    {
        return 1;
    }

    @Override
    public boolean isPlayerOnlyCommand()
    {
        return false;
    }
}
