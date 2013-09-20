
package net.vectorgaming.vchat.framework.enums;

import java.io.File;
import net.vectorgaming.vchat.VChatAPI;

/**
 *
 * @author Kenny
 */
public class ChatDirectory 
{
    private static final String dataFolder = VChatAPI.getPlugin().getDataFolder().getAbsolutePath();
    
    public static final String CHANNELS = dataFolder+File.separator+"channels";
}
