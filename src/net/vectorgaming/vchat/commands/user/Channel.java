
package net.vectorgaming.vchat.commands.user;

import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vcore.VCoreAPI;
import net.vectorgaming.vcore.framework.VertexPlugin;
import net.vectorgaming.vcore.framework.commands.VCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class Channel extends VCommand
{
    public Channel()
    {
        super("channel", VChatAPI.getPlugin());
    }

    @Override
    public void run(VertexPlugin plugin, CommandSender cs, String[] args)
    {
        cs.sendMessage(VCoreAPI.getColorScheme().getTitleBar("VCore Help"));
        cs.sendMessage(ChatColor.GREEN+"Type "+VCoreAPI.getColorScheme().getArgumentColor()+"/ch help "+ChatColor.GREEN+" for a list of commands.");
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
    public boolean isPlayerOnlyCommand()
    {
        return false;
    }

}
