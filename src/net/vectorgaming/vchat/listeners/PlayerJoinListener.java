
package net.vectorgaming.vchat.listeners;

import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vchat.framework.channel.SLChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author Kenny
 */
public class PlayerJoinListener implements Listener 
{
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
        for(Channel ch : ChatManager.getChannels())
        {
            if(ch.isForceJoin())
            {
                ChatManager.joinChannel(p, ch);
            }else
            {
                if(ch instanceof SLChannel)
                {
                    SLChannel sl = (SLChannel) ch;
                    if(sl.getAllPlayers().contains(p.getName()))
                    {
                        ChatManager.joinChannel(p, ch);
                    }
                }
            }
            //possibly add joining of temp channels?
        }
        if(ChatManager.getDefaultFocusChannel() != null)
        {
            ChatManager.focusChannel(p, ChatManager.getDefaultFocusChannel());
        }
    }
}
