
package net.vectorgaming.vchat.commands.user;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vcore.framework.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public class ChannelLeave extends SubCommand
{
    public ChannelLeave()
    {
        super("leave", VChatAPI.getPlugin());
        addAlias("l");
    }
    
    @Override
    public void run(CommandSender cs, String[] args)
    {
        Player p = (Player) cs;
        
        if(ChatManager.getJoinedChannels(p).isEmpty())
        {
            this.sendErrorMessage(cs, "You are not currently in any channels.");
            return;
        }
        
        if(args.length == 0)
        {
            Channel c = ChatManager.getFocusedChannel(p);
            ChatManager.leaveChannel(p, c, true);
            p.sendMessage(ChatColor.RED+"You have left channel "+ChatColor.translateAlternateColorCodes('&', c.getColor())+c.getName());
            return;
        }
        
        if(args.length == 1)
        {
            if(!ChatManager.channelExists(args[0]))
            {
                sendErrorMessage(cs, "Channel "+ChatColor.YELLOW+args[0]+ChatColor.RED+" does not exist.");
                return;
            }
            if(!ChatManager.isJoined(p, args[0]))
            {
                sendErrorMessage(cs, "You are in channel "+ChatColor.YELLOW+args[0]+ChatColor.RED+".");
                return;
            }
            
            ChatManager.leaveChannel(p, args[0], true);
            Channel c = ChatManager.getChannel(args[0]);
            p.sendMessage(ChatColor.RED+"You have left channel "+ChatColor.translateAlternateColorCodes('&', c.getColor())+c.getName());
        }
    }

    @Override
    public String getDescription()
    {
        return "Leaves a channel";
    }

    @Override
    public String getUsage()
    {
        return "/ch leave [<channel>]";
    }

    @Override
    public String getPermission()
    {
        return "vchat.leave";
    }

    @Override
    public Integer getMinArgsLength()
    {
        return 0;
    }
    
    @Override
    public Integer getMaxArgsLength()
    {
        return 1;
    }

    @Override
    public boolean isPlayerOnlyCommand()
    {
        return true;
    }

}
