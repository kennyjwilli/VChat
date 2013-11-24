
package net.vectorgaming.vchat.listeners;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vchat.framework.channel.SLChannel;
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
        
        if(ChatManager.getJoinedChannels(p) == null)
        {
            return;
        }

        if(ChatManager.getJoinedChannels(p).isEmpty()) return;
        
        for(Channel c : ChatManager.getJoinedChannels(p))
        {
            if(c instanceof SLChannel)
            {
                SLChannel ch = (SLChannel) c;
                ch.removePlayerTemp(p);
            }else
            {
                ChatManager.leaveChannel(p, c, false);
            }
        }
    }
    
}
