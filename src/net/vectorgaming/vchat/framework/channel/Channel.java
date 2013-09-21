
package net.vectorgaming.vchat.framework.channel;

import java.util.ArrayList;
import java.util.List;
import net.vectorgaming.vchat.util.ChatParser;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @author Kenny
 */
public abstract class Channel 
{
    private List<Player> chatters = new ArrayList<>();
    
    //Config variables
    private String name;
    private String nick;
    private String format;
    private String password;
    private String color;
    private String type;
    private int distance;
    private boolean forcejoin;
    private boolean verbose;
    private List<World> worlds;
    
    //everything else
    private ChatParser chatParser = new ChatParser();
    
    
    public Channel(String name, String type)
    {
        this.name = name;
        this.nick = name.substring(0, 1).toUpperCase();
        this.format = "{default}";
        this.password = "";
        this.color = "&f";
        this.type = type;
        this.distance = 0;
        this.forcejoin = false;
        this.verbose = true;
        this.worlds = new ArrayList<>();
    }
    
    /**
     * Gets the name of the channel
     * @return Name of the channel
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Adds a players directly to the channel. This should generally be avoided as
     * this will cause issues with gettting a player's channel through the ChatManager.
     * To properly add a player to a channel use ChatManager.addPlayer
     * @param p Player to add
     */
    public void addPlayer(Player p)
    {
        if(!chatters.contains(p))
            chatters.add(p);
    }
    
    /**
     * Removes a player from the channel.
     * 
     * To properly remove a player to a channel use ChatManager.removePlayer
     * @param p Player to be removed
     */
    public void removePlayer(Player p)
    {
        if(chatters.contains(p))
            chatters.remove(p);
    }
    
    /**
     * Gets a list of all the ACTIVE players in the channel. This will contain a list of
     * ONLY online players, hence the Player object.
     * @return A list of players
     */
    public List<Player> getPlayers()
    {
        return chatters;
    }
    
    /**
     * Gets a list of player names in String format. This will include ONLINE players only.
     * @return List of online players
     */
    public List<String> getPlayerNames()
    {
        List<String> result = new ArrayList<>();
        for(Player p : getPlayers())
            result.add(p.getName());
        return result;
    }
    
    /**
     * Gets the nickname of the channel. Generally the nickname of a channel will be 
     * the first letter of the channel name capitalized. 
     * @return Nickname of the channel
     */
    public String getNick() {return nick;}
    
    /**
     * Sets the nickname for the channel
     * @param nick Channel nickname
     */
    public void setNick(String nick) {this.nick = nick;}
    
    /**
     * Gets the format for the channel. 
     * @return The channel format
     */
    public String getFormat() {return format;}
    
    /**
     * Sets the format for the channel
     * @param format Format for the channel
     */
    public void setFormat(String format) {this.format = format;}
    
    /**
     * Gets the password for the channel. This password is stored as a String.
     * @return The channel password
     */
    public String getPassword() {return password;}
    
    /**
     * Sets the password for the channel
     * @param password Channel password
     */
    public void setPassword(String password){this.password = password;}
    
    /**
     * Gets the color of the channel
     * @return Color of the channel
     */
    public String getColor() {return color;}
    
    /**
     * Sets the color of the channel
     * @param color Color of the channel
     */
    public void setColor(String color) {this.color = color;}
    
    /**
     * Gets the type of the channel. This should be all CAPS and spaces should be underscores.
     * @return The type of the channel
     */
    public String getType() {return type;}
    
    /**
     * Sets the type of the channel
     * @param type Type of the channel
     */
    public void setType(String type) {this.type = type;}
    
    /**
     * Gets the radius of a circle a player must be inside in order to receive the chat
     * message
     * @return The radius distance of a circle
     */
    public int getDistance() {return distance;}
    
    /**
     * Sets the radius of a circle a player must be inside in order to receive the chat
     * @param radius The radius distance of a circle
     */
    public void setDistance(int radius){distance = radius;}
    
    /**
     * Checks to see if the channel should force a player to join it when he/she joins the
     * server
     * @return TRUE if it will force-join the player. FALSE if not.
     */
    public boolean isForceJoin() {return forcejoin;}
    
    /**
     * Sets the channel to force a player to join a channel when he/she joins the server
     * @param value TRUE if it will force-join the player. FALSE if not.
     */
    public void setForceJoin(boolean value) {forcejoin = value;}
    
    /**
     * Checks to see if the channel should be logged to the console
     * @return TRUE if will log. FALSE if not.
     */
    public boolean isVerbose() {return verbose;}
    
    /**
     * Sets if the channel should be logged to the console
     * @param value TRUE if will log. FALSE if not.
     */
    public void setVerbose(boolean value) {verbose = value;}
    
    /**
     * Gets a list of worlds to limit the channel to. If the list is empty the channel will
     * broadcast to every world
     * @return List of worlds
     */
    public List<World> getWorlds() {return worlds;}
    
    /**
     * Sets the worlds the channel will broadcast in
     * @param worlds List of worlds
     */
    public void setWorlds(List<World> worlds) {this.worlds = worlds;}
    
    /**
     * Adds a world to the list of worlds that the channel will broadcast in
     * @param world World to be added
     */
    public void addWorld(World world)
    {
        if(!worlds.contains(world))
            worlds.add(world);
    }
    
    /**
     * Removes a world from the list of worlds that the channel will broadcast in
     * @param world World to be removed
     */
    public void removeWorld(World world)
    {
        if(worlds.contains(world))
            worlds.remove(world);
    }
    
    /**
     * Called whenever a player chats 
     * @param p Name of the player sent a chat message
     * @param message The message that the player sent
     */
    public void onChat(final Player p, final String message)
    {
        setupParsing(p, message);
    }
    
    /**
     * Gets the ChatParser for the channel. This should be used to get the final, parsed message.
     * 
     * NOTE: The replace Strings will reset everytime the parser replaces all the Strings
     * @return The chat parser for the channel
     */
    public ChatParser getChatParser() {return chatParser;}
    
    private void setupParsing(Player p, String message)
    {
        chatParser.addReplaceString("color", getColor());
        chatParser.addReplaceString("channel", getName());
        chatParser.addReplaceString("nick", getNick());
        chatParser.addReplaceString("player", p.getName());
        chatParser.addReplaceString("displayname", p.getDisplayName());
        chatParser.addReplaceString("prefix", "");
        chatParser.addReplaceString("suffix", "");
        chatParser.addReplaceString("world", p.getWorld().getName());
        chatParser.addReplaceString("message", message);
    }
}
