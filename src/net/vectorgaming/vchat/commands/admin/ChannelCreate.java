
package net.vectorgaming.vchat.commands.admin;

import net.arcanerealm.arcanelib.ArcaneTools;
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
        
        if(!VChatAPI.channelTypeExists(args[1].toUpperCase()))
        {
            sendErrorMessage(cs, "Channel type "+argc+args[1]+ChatColor.RED+" does not exist.");
            cs.sendMessage(ChatColor.DARK_BLUE+"Avaliable types: "+ArcaneTools.convertListToString(VChatAPI.getChannelTypes(), true));
            return;
        }
        
        ChatManager.createChannel(args[0], args[1].toUpperCase());
        cs.sendMessage(ChatColor.GREEN+"Successfully create channel "+argc+args[0]);
    }

    @Override
    public String getDescription()
    {
        return "Creates a chat channel";
    }

    @Override
    public String getUsage()
    {
        return "/ch create <channel> <type> [<params>]";
    }

    @Override
    public String getPermission()
    {
        return "vchat.create";
    }

    @Override
    public Integer getMinArgsLength()
    {
        return 2;
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
