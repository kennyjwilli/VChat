
package net.vectorgaming.vchat.util;

import java.util.HashMap;
import net.vectorgaming.vchat.Settings;
import org.bukkit.ChatColor;

/**
 *
 * @author Kenny
 */
public class ChatParser 
{
    private final HashMap<String, String> replaceMap = new HashMap<>();
    
    public void addReplaceString(String original, String replace)
    {
        replaceMap.put("%"+original+"%", replace);
    }
    
    public String replaceAll(String message)
    {
        if(message.equalsIgnoreCase("{DEFAULT}"))
        {
            message = Settings.getDefaultFormat();
        }
        for(String s : replaceMap.keySet())
        {
            message = message.replaceAll(s, replaceMap.get(s));
        }
        replaceMap.clear();
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
