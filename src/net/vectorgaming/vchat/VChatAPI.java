
package net.vectorgaming.vchat;

import java.util.HashMap;
import net.vectorgaming.vchat.framework.channel.ChannelCreator;
import net.vectorgaming.vcore.framework.VertexPlugin;

/**
 *
 * @author Kenny
 */
public class VChatAPI
{
    private static HashMap<String, ChannelCreator> creator = new HashMap<>();
    private static VChat plugin;
    
    public VChatAPI(VChat instance) 
    {
        plugin = instance;
    }
    
    public static VertexPlugin getPlugin()
    {
        return plugin;
    }
    
    public static void registerChannelType(String type, ChannelCreator creator)
    {
        VChatAPI.creator.put(type, creator);
    }
    
    public static ChannelCreator getChannelCreator(String type)
    {
        return creator.get(type);
    }
}
