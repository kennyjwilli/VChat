
package net.vectorgaming.vchat;

import org.bukkit.plugin.Plugin;

/**
 *
 * @author Kenny
 */
public class Settings 
{
    private Plugin plugin = VChatAPI.getPlugin();
    private static String defaultFormat;
    private static boolean logChat;
    
    public Settings()
    {
        defaultFormat = plugin.getConfig().getString("format.default");
        logChat = plugin.getConfig().getBoolean("log-chat");
    }
    
    /**
     * Gets the default format for the channels
     * @return Default channel format
     */
    public static String getDefaultFormat()
    {
        return defaultFormat;
    }
    
    /**
     * Sets the default format for a channel
     * @param format Format for the channel
     */
    public static void setDefaultFormat(String format)
    {
        defaultFormat = format;
    }
        
    /**
     * Checks to see if the plugin should log the chat into a separate chat log
     * @return TRUE if loging is enabled, FALSE if not
     */
    public static boolean isLogChat()
    {
        return logChat;
    }
    
    /**
     * Enables or disabled the logging of the chat to a separate chat log
     * @param value boolean value
     */
    public static void setLogChat(boolean value)
    {
        logChat = value;
    }
}
