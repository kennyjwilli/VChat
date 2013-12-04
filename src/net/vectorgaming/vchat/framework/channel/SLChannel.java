
package net.vectorgaming.vchat.framework.channel;

import info.jeppes.ZoneCore.ZoneConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.vectorgaming.vchat.VChatAPI;
import net.vectorgaming.vchat.framework.enums.ChatDirectory;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public abstract class SLChannel extends Channel
{
    private List<String> allPlayers = new ArrayList<>();
    
    //Configs
    private final ZoneConfig config;
    private final ZoneConfig playersConfig;
    
    public SLChannel(String name, String type) 
    {
        super(name, type);
        config = new ZoneConfig(VChatAPI.getPlugin().getPlugin(), new File(ChatDirectory.CHANNELS+File.separator+name+File.separator+"channel-settings.yml"));
        playersConfig = new ZoneConfig(VChatAPI.getPlugin(), new File(ChatDirectory.CHANNELS+File.separator+name+File.separator+"players.yml"));
        
        config.set("name", getName());
        config.set("nick", getNick());
        config.set("color", getColor());
        config.set("format", getFormat());
        config.set("password", getPassword());
        config.set("type", getType().toUpperCase());
        config.set("verbose", isVerbose());
        config.set("forcejoin", isForceJoin());
        config.set("worlds", getWorlds());
        config.save();
    }
    
    @Override
    public void addPlayer(Player p)
    {
        super.addPlayer(p);
        if(!allPlayers.contains(p.getName()))
            allPlayers.add(p.getName());
    }
    
    @Override
    public void removePlayer(Player p)
    {
        super.removePlayer(p);
        if(allPlayers.contains(p.getName()))
            allPlayers.remove(p.getName());
    }
    
    /**
     * Temporarily removes a player from a channel. This will make it so the player does not
     * receive chat messages, however, when he or she logs back in they will be joined to the channel
     * again. To permanently remove a player use removePlayer(Player p).
     * 
     * This method is primarily used for when a player quits the game
     * @param p Name of the player
     */
    public void removePlayerTemp(Player p)
    {
        super.removePlayer(p);
    }
    
    /**
     * Gets a list of ALL the players who have joined the channel. This list WILL contain
     * offline players. This is primarily used for saving and loading players into the channel.
     * @return A list of all players who joined the channel
     */
    public List<String> getAllPlayers()
    {
        return allPlayers;
    }
    
    /**
     * Sets the list of ALL the players who have joined the channel. This list WILL contain
     * offline players. This is primarily used for saving and loading players into the channel.
     * @param players A list of all players who joined the channel
     */
    public void setAllPlayers(List<String> players)
    {
        allPlayers = players;
    }
    
    /**
     * Runs all default save operations for a channel. This can easily be overridden
     * to implement custom saves.
     */
    public void save()
    {
        config.set("name", getName());
        config.set("nick", getNick());
        config.set("color", getColor());
        config.set("format", getFormat());
        config.set("password", getPassword());
        config.set("type", getType().toUpperCase());
        config.set("verbose", isVerbose());
        config.set("forcejoin", isForceJoin());
        config.set("worlds", getWorlds());
        config.save();
    }
    
    /**
     * Saves all players in the channel to the config file
     */
    public void savePlayers()
    {
        playersConfig.set("players", getPlayerNames());
        playersConfig.save();
    }
    
    /**
     * Loads all default settings. Additional values can be loaded by overriding this
     * method
     */
    public void load()
    {
        setNick(config.getString("nick"));
        setColor(config.getString("color"));
        setFormat(config.getString("format"));
        setPassword(config.getString("password"));
        setType(config.getString("type"));
        setVerbose(config.getBoolean("verbose"));
        setForceJoin(config.getBoolean("forcejoin"));
        
        List<World> list = new ArrayList<>();
        for(String s : config.getStringList("worlds"))
        {
            if(Bukkit.getWorld(s) != null)
                list.add(Bukkit.getWorld(s));
        }
        setWorlds(list);
    }
    
    /**
     * Loads all the players that have joined the channel from the config
     */
    public void loadPlayers()
    {
        setAllPlayers(playersConfig.getStringList("players"));
    }

    /**
     * Gets the directory the channel is located in
     * @return The channel directory
     */
    public File getDirectory()
    {
        return new  File(VChatAPI.getPlugin().getDataFolder()+File.separator+"channels"+File.separator+getName());
    }
    
    /**
     * Gets the config file for the channel
     * @return The config file for the channel
     */
    public ZoneConfig getConfig()
    {
        return config;
    }
    
    /**
     * Gets the config where all the players 
     * @return The config file where all joined players are stored
     */
    public ZoneConfig getPlayersConfig()
    {
        return playersConfig;
    }
}
