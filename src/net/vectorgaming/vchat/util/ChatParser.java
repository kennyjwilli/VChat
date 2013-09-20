
package net.vectorgaming.vchat.util;

import java.util.HashMap;
import org.bukkit.ChatColor;

/**
 *
 * @author Kenny
 */
public class ChatParser 
{
    private HashMap<String, String> replaceMap = new HashMap<>();
    
    public void addReplaceString(String original, String replace)
    {
        replaceMap.put("%"+original+"%", replace);
    }
    
    public String replaceAll(String message)
    {
        for(String s : replaceMap.keySet())
        {
            message = message.replaceAll(s, replaceMap.get(s));
        }
        replaceMap.clear();
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
