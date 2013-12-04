
package net.vectorgaming.vchat;

import java.util.HashMap;
import java.util.Set;
import net.vectorgaming.vchat.framework.channel.ChannelCreator;
import net.vectorgaming.vcore.framework.VertexPlugin;

/**
 *
 * @author Kenny
 */
public class VChatAPI
{
    private static final HashMap<String, ChannelCreator> creator = new HashMap<>();
    private static VChat plugin;
    
    public VChatAPI(VChat instance) 
    {
        plugin = instance;
    }
    
    /**
     * Gets the plugin
     * @return Plugin
     */
    public static VertexPlugin getPlugin()
    {
        return plugin;
    }
    
    /**
     * Registers a channel creator so that the channel can be created via the in-game
     * command
     * @param type The type of the channel. This is generally all upper case and spaces
     * should be replaced with underscores
     * @param creator The channel creator class for the channel type
     */
    public static void registerChannelType(String type, ChannelCreator creator)
    {
        VChatAPI.creator.put(type, creator);
    }
    
    /**
     * Gets the channel creator for a given channel type
     * @param type Type of channel
     * @return A channel creator object
     */
    public static ChannelCreator getChannelCreator(String type)
    {
        return creator.get(type);
    }
    
    /**
     * Checks to see if the channel type has been registered
     * @param type Type of channel
     * @return true if the channel type exists
     */
    public static boolean channelTypeExists(String type)
    {
        return creator.containsKey(type);
    }
    
    /**
     * Returns a list of channel types that are avaliable
     * @return List of channel types
     */
    public static Set<String> getChannelTypes()
    {
        return creator.keySet();
    }
}
