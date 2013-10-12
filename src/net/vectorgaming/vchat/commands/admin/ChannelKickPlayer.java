
package net.vectorgaming.vchat.commands.admin;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vcore.framework.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public class ChannelKickPlayer extends SubCommand
{
    public ChannelKickPlayer()
    {
        super("kickplayer", VChatAPI.getPlugin());
    }

    @Override
    public void run(CommandSender cs, String[] args)
    {
        Player p = Bukkit.getPlayer(args[0]);
        if(p == null)
        {
            sendErrorMessage(cs, "Player "+ChatColor.YELLOW+args[0]+ChatColor.RED+" is not online.");
            return;
        }

        Channel ch;

        if(args.length == 1)
        {
            if(cs instanceof ConsoleCommandSender)
            {
                cs.sendMessage("Console command requires additional arguments.");
                cs.sendMessage(ChatColor.RED+"Usage: "+getUsage());
                return;
            }
            ch = ChatManager.getFocusedChannel((Player) cs);
            if(ch == null)
            {
                sendErrorMessage(cs, "You must be focused on a channel to issue this command with the given arguments.");
            }
        }else
        {
            if(!ChatManager.channelExists(args[1]))
            {
                sendErrorMessage(cs, "Channel "+ChatColor.YELLOW+args[1]+ChatColor.RED+" does not exist.");
                return;
            }
            ch = ChatManager.getChannel(args[1]);
        }
        ChatManager.leaveChannel(p, ch, true);
        cs.sendMessage(ChatColor.GREEN+"Kicked player "+ChatColor.YELLOW+p.getName()+ChatColor.GREEN+" from channel "+ch.getColor()+ch.getName());
        p.sendMessage(ChatColor.RED+"You have been kicked from channel "+ch.getColor()+ch.getName());
    }

    @Override
    public String getDescription()
    {
        return "Kicks a player from a channel";
    }

    @Override
    public String getUsage()
    {
        return "/ch kickplayer <name> [<channel>]";
    }

    @Override
    public String getPermission()
    {
        return "vchat.kick";
    }

    @Override
    public Integer getMinArgsLength()
    {
        return 1;
    }

    @Override
    public Integer getMaxArgsLength()
    {
        return 2;
    }

    @Override
    public boolean isPlayerOnlyCommand()
    {
        return false;
    }
}
