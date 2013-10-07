
package net.vectorgaming.vchat.commands.admin;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vcore.framework.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public class ChannelAddPlayer extends SubCommand
{
    public ChannelAddPlayer()
    {
        super("addplayer", VChatAPI.getPlugin());
    }

    @Override
    public void run(CommandSender cs, String[] args)
    {
        Player p = Bukkit.getPlayer(args[0]);
        Channel ch = null;
        
        if(p == null)
        {
            sendErrorMessage(cs, "Player "+ChatColor.YELLOW+args[0]+ChatColor.RED+" is not online.");
            return;
        }
        
        if(cs instanceof Player)
        {
            if(args.length != 2 && args.length == 1)
            {
                if(ChatManager.getJoinedChannels(p).isEmpty())
                {
                    sendErrorMessage(cs, "You are not currently joined to any channels. Provide a channel argument to use this command.");
                    return;
                }
                ch = ChatManager.getFocusedChannel((Player) cs);
            }
        }else
        {
            if(args.length != 2)
            {
                sendErrorMessage(cs, "Channel argument is required for ConsoleCommandSender.");
                return;
            }
        }
        
        if(args.length == 2)
        {
            if(!ChatManager.channelExists(args[1]))
            {
                sendErrorMessage(cs, "Channel "+ChatColor.YELLOW+args[1]+ChatColor.RED+" does not exist.");
                return;
            }
            ch = ChatManager.getChannel(args[1]);
        }
        
        ChatManager.joinChannel(p, ch);
        cs.sendMessage(ChatColor.GREEN+"Added player "+ChatColor.YELLOW+args[0]+ChatColor.GREEN+" to channel "+ChatColor.translateAlternateColorCodes('&', ch.getColor())+ch.getName());
    }

    @Override
    public String getDescription()
    {
        return "Adds a player to a channel";
    }

    @Override
    public String getUsage()
    {
        return "/ch addplayer <name> [<channel>]";
    }

    @Override
    public String getPermission()
    {
        return "vchat.addplayer";
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
