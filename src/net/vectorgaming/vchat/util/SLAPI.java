
package net.vectorgaming.vchat.util;

import info.jeppes.ZoneCore.ZoneConfig;
import java.io.File;
import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vchat.framework.channel.SLChannel;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vchat.framework.enums.ChatDirectory;

/**
 *
 * @author Kenny
 */
public class SLAPI 
{
    /**
     * Saves all the channels to their config files
     */
    public static void saveAllChannels()
    {
        for(Channel channel : ChatManager.getChannels())
        {
            if(channel instanceof SLChannel)
            {
                SLChannel sl = (SLChannel) channel;
                sl.save();
            }
        }
    }
    
    /**
     * Saves all players channels that they have joined
     */
    public static void saveJoinedChannels()
    {
        for(Channel ch : ChatManager.getChannels())
        {
            if(ch instanceof SLChannel)
            {
                SLChannel sl = (SLChannel) ch;
                sl.savePlayers();
            }
        }
    }
    
    /**
     * Loads all the channels that a player has joinedS
     */
    public static void loadAllChannels()
    {
        for(File f : new File(ChatDirectory.CHANNELS).listFiles())
        {
            ZoneConfig config = new ZoneConfig(VChatAPI.getPlugin(), f);
            String name = config.getString("name");
            String type = config.getString("type");
            SLChannel channel = (SLChannel) VChatAPI.getChannelCreator(type).createChannel(name);
            channel.load();
        }
    }
    
    /**
     * Loads all players from the channel players config
     */
    public static void loadJoinedChannels()
    {
        for(Channel ch : ChatManager.getChannels())
        {
            if(ch instanceof SLChannel)
            {
                SLChannel sl = (SLChannel) ch;
                sl.loadPlayers();
            }
        }
    }
}
