
package net.vectorgaming.vchat.framework.channel.type;

import net.vectorgaming.vchat.framework.channel.SLChannel;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public class BasicChannel extends SLChannel
{
    public BasicChannel(String name) 
    {
        super(name, "BASIC_CHANNEL");
    }
    
    @Override
    public void onChat(Player player, String message)
    {
        super.onChat(player, message);
        
        if(getWorlds().isEmpty())
        {
            for(Player p : getPlayers())
            {
                p.sendMessage(this.getChatParser().replaceAll(message));
            }
        }else
        {
            for(Player p : getPlayers())
            {
                if(getWorlds().contains(p.getWorld()))
                {
                    p.sendMessage(this.getChatParser().replaceAll(message));
                }
            }
        }
    }

}
