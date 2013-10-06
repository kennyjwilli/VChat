
package net.vectorgaming.vchat.commands.admin;

import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vchat.util.SLAPI;
import net.vectorgaming.vcore.framework.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Kenny
 */
public class ChannelReload extends SubCommand
{
    public ChannelReload()
    {
        super("reload", VChatAPI.getPlugin());
    }
    
    @Override
    public void run(CommandSender cs, String[] args)
    {
        SLAPI.reloadSettings();
        cs.sendMessage(ChatColor.GREEN+"Successfully reloaded VChat.");
    }

    @Override
    public String getDescription()
    {
        return "Reloads VChat";
    }

    @Override
    public String getUsage()
    {
        return "/ch reload";
    }

    @Override
    public String getPermission()
    {
        return "vchat.reload";
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
