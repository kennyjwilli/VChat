
package net.vectorgaming.vchat;

import org.bukkit.plugin.Plugin;

/**
 *
 * @author Kenny
 */
public class Settings 
{
    private final Plugin plugin = VChatAPI.getPlugin();
    private static String defaultFormat;
    private static boolean logChat;
    private static String parseFaction;
    
    public Settings()
    {
        defaultFormat = plugin.getConfig().getString("format.default");
        logChat = plugin.getConfig().getBoolean("log-chat");
        parseFaction = plugin.getConfig().getString("format.parse-faction");
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
     * Returns the replacement for the faction tag (%faction%) replacement
     * @return Faction tag replacement
     */
    public static String getFactionReplacement()
    {
        return parseFaction;
    }
    
    /**
     * Sets the replacement for the faction tag (%faction%) replacement
     * @param format Replacement for %faction%
     */
    public static void setFacitonReplacement(String format)
    {
        parseFaction = format;
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
