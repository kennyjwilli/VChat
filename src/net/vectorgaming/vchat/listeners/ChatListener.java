
package net.vectorgaming.vchat.listeners;

import net.arcanerealm.arcanelib.ArcaneTools;
import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.Settings;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vchat.util.ChatLogger;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author Kenny
 */
public class ChatListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onChatEvent(final AsyncPlayerChatEvent event)
    {
        final Player p = event.getPlayer();
        
        if(!ChatManager.hasFocusedChannel(p))
        {
            p.sendMessage(ChatColor.RED+"You must be focused on a channel to chat. Type "+ChatColor.YELLOW+"/ch join <channel>"+ChatColor.RED+" to join a channel.");
            event.setCancelled(true);
            return;
        }
        
        Channel channel = ChatManager.getFocusedChannel(p);
        try
        {
            String message = channel.onChat(p, event.getMessage());
            if(channel.isVerbose())
            {
                ChatLogger.logToConsole(message);
            }
            if(Settings.isLogChat())
            {
                ChatLogger.logToFile(message);
            }
        }catch(Exception e)
        {
            p.sendMessage(ChatColor.RED+"Uh oh! There seems to be a problem with the chat. "
                    + "If you see a crazy developer tell them this: "+ArcaneTools.getStackTraceError(e.getStackTrace()));
            e.printStackTrace();
        }
        event.setCancelled(true);
    }
}
