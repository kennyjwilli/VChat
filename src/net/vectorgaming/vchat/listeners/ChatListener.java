
package net.vectorgaming.vchat.listeners;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.framework.channel.Channel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author Kenny
 */
public class ChatListener implements Listener
{
    @EventHandler
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
        
        channel.onChat(p, event.getMessage());
        event.setCancelled(true);
    }
}
