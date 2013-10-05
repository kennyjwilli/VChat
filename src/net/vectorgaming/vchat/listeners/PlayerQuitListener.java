
package net.vectorgaming.vchat.listeners;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.framework.channel.Channel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Kenny
 */
public class PlayerQuitListener implements Listener
{
    @EventHandler 
    public void onQuit(PlayerQuitEvent event)
    {
        Player p = event.getPlayer();
        
        for(Channel c : ChatManager.getJoinedChannels(p))
        {
            ChatManager.leaveChannel(p, c);
        }
    }
    
}
