
package net.vectorgaming.vchat.commands.admin;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vcore.VCoreAPI;
import net.vectorgaming.vcore.framework.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class ChannelCreate extends SubCommand
{
    private ChatColor argc = VCoreAPI.getColorScheme().getArgumentColor();
    
    public ChannelCreate()
    {
        super("create", VChatAPI.getPlugin());
    }

    @Override
    public void run(CommandSender cs, String[] args)
    {
        if(ChatManager.channelExists(args[0]))
        {
            sendErrorMessage(cs, "Channel "+argc+args[0]+ChatColor.RED+" already exists.");
            return;
        }
        
        ChatManager.createChannel(args[0], "BASIC_CHANNEL");
        cs.sendMessage(ChatColor.GREEN+"Successfully create channel"+argc+args[0]);
    }

    @Override
    public String getDescription()
    {
        return "Creates a chat channel";
    }

    @Override
    public String getUsage()
    {
        return "/ch create <channel> [<params>]";
    }

    @Override
    public String getPermission()
    {
        return "vchat.create";
    }

    @Override
    public Integer getMinArgsLength()
    {
        return 1;
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
