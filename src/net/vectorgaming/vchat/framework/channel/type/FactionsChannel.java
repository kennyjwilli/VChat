
package net.vectorgaming.vchat.framework.channel.type;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import net.vectorgaming.vchat.framework.channel.SLChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public class FactionsChannel extends SLChannel
{

    public FactionsChannel(String name)
    {
        super(name, "FACTIONS");
    }
    
    @Override
    public String onChat(Player player, String message)
    {
        super.onChat(player, message);
        String formatted = getChatParser().replaceAll(getFormat());
        FPlayer fp = FPlayers.i.get(player);
        
        if(!fp.hasFaction())
        {
            fp.sendMessage(ChatColor.RED+"You must belong to a faction to chat in the channel.");
            return "Player "+fp.getName()+" tried to chat in a faction channel without a faction.";
        }
        
        for(Player p : fp.getFaction().getOnlinePlayers())
        {
            p.sendMessage(formatted);
        }
        return formatted;
    }
}
