
package net.vectorgaming.vchat.util;

import info.jeppes.ZoneCore.ZoneConfig;
import java.io.File;
import net.vectorgaming.vchat.ChatManager;
import net.vectorgaming.vchat.Settings;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vchat.framework.channel.SLChannel;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vchat.framework.enums.ChatDirectory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

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
        File directory = new File(ChatDirectory.CHANNELS);
        if(!directory.exists())
        {
            return;
        }
        
        for(File f : directory.listFiles())
        {
            ZoneConfig config = new ZoneConfig(VChatAPI.getPlugin(), new File(f.getAbsolutePath()+File.separator+"config.yml"));
            String name = config.getString("name");
            String type = config.getString("type").toUpperCase();
            ((SLChannel) ChatManager.createChannel(name, type)).load();
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
    
    /**
     * Loads all the settings that are set in the config.yml 
     */
    public static void loadDefaultConfig()
    {
        Plugin plugin = VChatAPI.getPlugin();
        plugin.reloadConfig();
        
        Settings.setDefaultFormat(plugin.getConfig().getString("format.default"));
        Settings.setFacitonReplacement(plugin.getConfig().getString("parse-faction"));
        Settings.setLogChat(plugin.getConfig().getBoolean("log-chat"));
    }
    
    /**
     * Reloads the plugin from all config settings. 
     */
    public static void reloadSettings()
    {
        saveJoinedChannels();
        ChatManager.resetChannels();
        loadDefaultConfig();
        loadAllChannels();
        loadJoinedChannels();
        loadDefaultConfig();
    }

    /**
     * Joins all players who are currently active on the server to any channels saved to file
     */
    public static void loadJoinedPlayers()
    {
        for(Player p : Bukkit.getOnlinePlayers())
        {
            for(Channel c : ChatManager.getChannels())
            {
                if(c instanceof SLChannel)
                {
                    if(((SLChannel) c).getAllPlayers().contains(p.getName())) ChatManager.focusChannel(p, c);
                }

            }
        }
    }
}
