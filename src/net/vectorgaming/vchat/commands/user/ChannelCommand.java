
package net.vectorgaming.vchat.commands.user;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vchat.commands.admin.*;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vcore.VCoreAPI;
import net.vectorgaming.vcore.framework.commands.VCommand;
import net.vectorgaming.vpromote.commands.CommandManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public class ChannelCommand extends VCommand
{
    public ChannelCommand()
    {
        super("channel", VChatAPI.getPlugin());
        addSubCommand(new ChannelJoin());
        addSubCommand(new ChannelCreate());
        addSubCommand(new ChannelLeave());
        addSubCommand(new ChannelReload());
        addSubCommand(new ChannelAddPlayer());
        addSubCommand(new ChannelDelete());
        addSubCommand(new ChannelList());
        addSubCommand(new ChannelWho());
        addSubCommand(new ChannelKickPlayer());
        this.addAlias("ch");
    }

    @Override
    public void run(CommandSender cs, String[] args)
    {
        if(args.length != 0 && ChatManager.channelExists(args[0]))
        {
            Channel channel = ChatManager.getChannel(args[0]);
            
            if(!cs.hasPermission("vchat.join."+channel.getName()) && !cs.hasPermission("vchat.join.*"))
            {
                sendErrorMessage(cs, ChatColor.RED+"You do not have permission to join " + ChatColor.YELLOW+channel.getName()+ChatColor.RED + ".");
                return;
            }
            
            ChatManager.focusChannel((Player) cs, args[0]);
            cs.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN+"Focused on "+channel.getColor()+channel.getName()));
            return;
        }
        cs.sendMessage(VCoreAPI.getColorScheme().getTitleBar("VChat Help"));
        cs.sendMessage(ChatColor.GREEN+"Type "+VCoreAPI.getColorScheme().getArgumentColor()+"/ch help "+ChatColor.GREEN+"for a list of commands.");
    }

    @Override
    public String getDescription()
    {
        return "The base channel command";
    }

    @Override
    public String getUsage()
    {
        return "/ch [<param>]";
    }

    @Override
    public String getPermission()
    {
        return "vchat.help";
    }

    @Override
    public Integer getMinArgsLength()
    {
        return 0;
    }
    
    @Override
    public Integer getMaxArgsLength()
    {
        return -1;
    }

    @Override
    public boolean isPlayerOnlyCommand()
    {
        return false;
    }

}
