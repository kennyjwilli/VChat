
package net.vectorgaming.vchat.commands.user;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vcore.VCoreAPI;
import net.vectorgaming.vcore.framework.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public class ChannelWho extends SubCommand
{
    public ChannelWho()
    {
        super("who", VChatAPI.getPlugin());
    }

    @Override
    public void run(CommandSender cs, String[] args)
    {
        Player p = (Player) cs;
        if(args.length == 0)
        {
            cs.sendMessage(VCoreAPI.getColorScheme().getTitleBar("Player List"));

            for(Channel c : ChatManager.getJoinedChannels(p))
            {
                String output = "";
                boolean first = false;
                for(Player player : c.getPlayers())
                {
                    if(first)
                    {
                        output += ChatColor.WHITE+", ";
                    }else
                    {
                        first = true;
                    }
                    output += player.getName();
                }
                cs.sendMessage(ChatColor.BOLD+ChatColor.translateAlternateColorCodes('&', c.getColor())+c.getName()+": "+ChatColor.RESET+output);
            }
        }else
        {
            if(args[0].equalsIgnoreCase("all"))
            {
                cs.sendMessage("Not implemented yet..");
            }else
            {
                if(!ChatManager.channelExists(args[0]))
                {
                    this.sendErrorMessage(cs, "Channel "+ChatColor.YELLOW+args[0]+ChatColor.RED+" does not exist.");
                    return;
                }

                String output = "";
                boolean first = false;
                for(Player player : ChatManager.getChannel(args[0]).getPlayers())
                {
                    if(first)
                    {
                        output += ChatColor.WHITE+", ";
                    }else
                    {
                        first = true;
                    }
                    output += player.getName();
                }
                cs.sendMessage(VCoreAPI.getColorScheme().getTitleBar(ChatManager.getChannel(args[0]).getName()+" Players"));
                cs.sendMessage(output);
            }
        }
    }

    @Override
    public String getDescription()
    {
        return "Gets who is in a channel";
    }

    @Override
    public String getUsage()
    {
        return "/ch who [<channel/all>]";
    }

    @Override
    public String getPermission()
    {
        return "vchat.who";
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
