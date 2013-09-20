
package net.vectorgaming.vchat;

import java.util.HashMap;
import net.vectorgaming.vchat.framework.channel.ChannelCreator;
import net.vectorgaming.vcore.framework.VertexAPI;

/**
 *
 * @author Kenny
 */
public class VChatAPI extends VertexAPI
{
    private static HashMap<String, ChannelCreator> creator = new HashMap<>();
    
    public VChatAPI(VChat instance) {super(instance);}
    
    public static void registerChannelType(String type, ChannelCreator creator)
    {
        VChatAPI.creator.put(type, creator);
    }
    
    public static ChannelCreator getChannelCreator(String type)
    {
        return creator.get(type);
    }
}
