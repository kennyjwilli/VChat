
package net.vectorgaming.vchat;

import info.jeppes.ZoneCore.ZoneTools;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vchat.framework.channel.SLChannel;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Kenny
 */
public class ChatManager 
{
    private static final HashMap<String, Channel> channels = new HashMap<>();
    private static final HashMap<Player, Channel> focusedChannel = new HashMap<>();
    private static final HashMap<Player, ArrayList<Channel>> joinedChannels = new HashMap<>();
    
    /**
     * Gets a channel from the specified name
     * @param name Name of the channel
     * @return Channel object
     */
    public static Channel getChannel(String name) 
    {
        return channels.get(name.toLowerCase());
    }
    
    /**
     * Creates a channel
     * @param name Name of the channel
     * @param type Type of the channel. 
     * NOTE: The channel type should be all caps and spaces should be replaced with
     * underscores.
     * Ensure all channel types have been registered with a ChannelCreator in VChatAPI
     * @return The channel that was created
     */
    public static Channel createChannel(String name, String type)
    {
        Channel channel = VChatAPI.getChannelCreator(type.toUpperCase()).createChannel(name);
        channels.put(name.toLowerCase(), channel);
        return channel;
    }

    /**
     * Deletes a channel
     * @param name Name of the channel to be deleted
     */
    public static void deleteChannel(String name)
    {
        //converted to object array to prevent concurrent modification
        Object[] list = getChannel(name.toLowerCase()).getPlayers().toArray();
        Channel ch = getChannel(name);
        for(Object p : list)
        {
            ChatManager.leaveChannel((Player) p, name.toLowerCase(), true);
        }

        if(ch instanceof SLChannel)
        {
            ZoneTools.deleteDirectory(((SLChannel) ch).getDirectory());
        }
        channels.remove(name.toLowerCase());
    }
    
    /**
     * Focuses a player to a channel
     * @param p Player to set focused channel
     * @param channel Channel to be focused on
     */
    public static void focusChannel(Player p, String channel)
    {
        if(!isJoined(p, channel.toLowerCase()))
        {
            joinChannel(p, channel.toLowerCase());
        }
        focusedChannel.put(p, getChannel(channel.toLowerCase()));
    }
    
    /**
     * Focuses a player to a channel
     * @param p Player to set focused channel
     * @param channel Channel to be focused on
     */
    public static void focusChannel(Player p, Channel channel) 
    {
        focusChannel(p, channel.getName());
    }
    
    /**
     * Gets the focused channel for the player
     * @param p Player
     * @return The player's focused channel
     */
    public static Channel getFocusedChannel(Player p)
    {
        return focusedChannel.get(p);
    }
    
    /**
     * Joins a player to a channel so he/she can receive messages from that channel
     * @param p Player to add to the channel
     * @param channel Name of the channel to add
     */
    public static void joinChannel(Player p, String channel)
    {
        Channel ch = getChannel(channel.toLowerCase());
        ch.addPlayer(p);
        if(joinedChannels.containsKey(p))
        {
            if(!joinedChannels.get(p).contains(ch))
            {
                ArrayList<Channel> temp = new ArrayList<>();
                temp.add(ch);
                joinedChannels.put(p, temp);
            }
        }else
        {
            ArrayList<Channel> temp = new ArrayList<>();
            temp.add(ch);
            joinedChannels.put(p, temp);
        }
        if(ch instanceof SLChannel)
        {
            ch = (SLChannel) ch;
            ch.addPlayer(p);
        }
    }
    
    /**
     * Joins a player to a channel so he/she can receive messages from that channel
     * @param p Player to join the channel
     * @param channel Channel to join
     */
    public static void joinChannel(Player p, Channel channel) {joinChannel(p, channel.getName());}
    
    /**
     * Removes a player from a channel so they no longer receive messages from that channel
     * @param p Player to leave the channel
     * @param channel Name of the channel being left
     * @param permanent Should the player be permanently remove from the channel? If the channel is
     * a SL channel then the player will be removed from the lists stored in the players.yml for the channel
     */
    public static void leaveChannel(Player p, String channel, boolean permanent)
    {
        ArrayList<Channel> list = getJoinedChannels(p);
        Channel ch = getChannel(channel);
        if(list.contains(ch))
            list.remove(ch);
        joinedChannels.put(p, list);
        if(focusedChannel.get(p) == ch)
            focusedChannel.remove(p);
        if(ch instanceof SLChannel && permanent)
        {
            if(permanent)
            {
                ch.removePlayer(p);
            }else
            {
                ((SLChannel) ch).removePlayerTemp(p);
            }
        }else
        {
            ch.removePlayer(p);
        }
    }
    
    /**
     * Removes a player from a channel so they no longer receive messages from that channel
     * @param p Player to leave the channel
     * @param channel Channel being left
     * @param permanent Should the player be permanently remove from the channel? If the channel is
     * a SL channel then the player will be removed from the lists stored in the players.yml for the channel
     */
    public static void leaveChannel(Player p, Channel channel, boolean permanent) 
    {
        leaveChannel(p, channel.getName(), permanent);
    }
    
    /**
     * Gets a list of channels that the player is joined to
     * @param p Player to get
     * @return List of Channels
     */
    public static ArrayList<Channel> getJoinedChannels(Player p)
    {
        return joinedChannels.get(p);
    }
    
    /**
     * Checks to see if a channel exists
     * @param channel Name of the channel
     * @return boolean value
     */
    public static boolean channelExists(String channel)
    {
        return channels.containsKey(channel.toLowerCase());
    }
    
    /**
     * Checks to see if a channel exists
     * @param channel Channel to check
     * @return boolean value
     */
    public static boolean channelExists(Channel channel) {return channelExists(channel.getName());}
    
    /**
     * Checks to see if a player is joined to the specified channel
     * @param p Player to check
     * @param channel Name of the channel
     * @return boolean value
     */
    public static boolean isJoined(Player p, String channel)
    {
        if(!joinedChannels.containsKey(p))
            return false;
        ArrayList<Channel> list = joinedChannels.get(p);
        return list.contains(getChannel(channel.toLowerCase()));
    }
    
    /**
     * Checks to see if a player is joined to the specified channel
     * @param p Player to check
     * @param channel Channel
     * @return boolean value
     */
    public static boolean isJoined(Player p, Channel channel) {return isJoined(p, channel.getName());}
    
    /**
     * Checks to see if the player has a focused channel
     * @param p Player to check
     * @return boolean value
     */
    public static boolean hasFocusedChannel(Player p)
    {
        return focusedChannel.containsKey(p);
    }
    
    /**
     * Gets a list of all channels that are currently avaliable
     * @return List of channels
     */
    public static ArrayList<Channel> getChannels()
    {
        ArrayList<Channel> result = new ArrayList<>();
        for(Channel ch : channels.values())
            result.add(ch);
        return result;
    }
    
    /**
     * Clears all maps that store loaded channels and joined channels
     */
    public static void resetChannels()
    {
        channels.clear();
        focusedChannel.clear();
        joinedChannels.clear();
    }
}
