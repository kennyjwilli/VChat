
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
public class ChannelJoin extends SubCommand
{
    public ChannelJoin()
    {
        super("join", VChatAPI.getPlugin());
    }
    
    @Override
    public void run(CommandSender cs, String[] args)
    {
        Player p = (Player) cs;
        
        if(!ChatManager.channelExists(args[0]))
        {
            this.sendErrorMessage(cs, "Channel "+ChatColor.YELLOW+args[0]+ChatColor.RED+" does not exist.");
            return;
        }
        Channel channel = ChatManager.getChannel(args[0]);
        ChatManager.focusChannel(p, args[0]);
        cs.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN+"Focused on "+channel.getColor()+channel.getName()));
    }

    @Override
    public String getDescription()
    {
        return "Joins a channel";
    }

    @Override
    public String getUsage()
    {
        return "/ch join <channel>";
    }

    @Override
    public String getPermission()
    {
        return "vchat.join";
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
        return true;
    }
}
