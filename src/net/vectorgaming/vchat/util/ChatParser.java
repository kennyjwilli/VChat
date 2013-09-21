
package net.vectorgaming.vchat.util;

import java.util.HashMap;
import net.vectorgaming.vchat.VChatAPI;
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
        if(message.equalsIgnoreCase("{DEFAULT}"))
        {
            message = VChatAPI.getPlugin().getConfig().getString("format.default");
        }
        for(String s : replaceMap.keySet())
        {
            message = message.replaceAll(s, replaceMap.get(s));
        }
        replaceMap.clear();
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
