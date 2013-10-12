
package net.vectorgaming.vchat.commands.user;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vcore.VCoreAPI;
import net.vectorgaming.vcore.framework.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class ChannelList extends SubCommand
{
    protected ChannelList()
    {
        super("list", VChatAPI.getPlugin());
    }

    @Override
    public void run(CommandSender cs, String[] args)
    {
        String channels = "";
        boolean first = false;
        for(Channel c : ChatManager.getChannels())
        {
            if(first)
            {
                channels += ChatColor.WHITE+", ";
            }else
            {
                first = true;
            }
            channels += ChatColor.translateAlternateColorCodes('&', c.getColor());
            channels += c.getName();
        }

        cs.sendMessage(VCoreAPI.getColorScheme().getTitleBar("Channels"));
        cs.sendMessage(channels);
    }

    @Override
    public String getDescription()
    {
        return "Lists all the channels created";
    }

    @Override
    public String getUsage()
    {
        return "/ch list [<true>]";
    }

    @Override
    public String getPermission()
    {
        return "vchat.list";
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
        return false;
    }
}
