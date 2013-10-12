package net.vectorgaming.vchat;

import net.vectorgaming.vchat.commands.user.ChannelCommand;
import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vchat.framework.channel.SLChannel;
import net.vectorgaming.vchat.framework.channel.type.BasicChannelCreator;
import net.vectorgaming.vchat.listeners.ChatListener;
import net.vectorgaming.vchat.listeners.PlayerJoinListener;
import net.vectorgaming.vchat.listeners.PlayerQuitListener;
import net.vectorgaming.vchat.util.SLAPI;
import net.vectorgaming.vcore.framework.VertexAPI;
import net.vectorgaming.vcore.framework.VertexPlugin;
import net.vectorgaming.vcore.framework.commands.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 *
 * @author Kenny
 */
public class VChat extends VertexPlugin
{
    private VChatAPI api;
    private Settings settings;
    
    @Override
    public void onEnable()
    {
        api = new VChatAPI(this);
        settings = new Settings();
        this.saveDefaultConfig();
        setupCommands();
        setupListeners();
        registerChannels();
        SLAPI.loadAllChannels();
        SLAPI.loadJoinedChannels();
        loadJoinedPlayers();
    }

    @Override
    public void onDisable()
    {
        SLAPI.saveAllChannels();
        SLAPI.saveJoinedChannels();
    }

    @Override
    public void setupCommands()
    {
        CommandManager.registerCommand(new ChannelCommand());
    }

    @Override
    public Plugin getPlugin()
    {
        return this;
    }

    @Override
    public VertexAPI getAPI()
    {
        return api;
    }
    
    private void setupListeners()
    {
        PluginManager pm = Bukkit.getPluginManager();
        
        ChatListener chatListener = new ChatListener();
        pm.registerEvents(chatListener, this);
        
        PlayerJoinListener joinListener = new PlayerJoinListener();
        pm.registerEvents(joinListener, this);
        
        PlayerQuitListener quitListener = new PlayerQuitListener();
        pm.registerEvents(quitListener, this);
    }
    
    private void registerChannels()
    {
        VChatAPI.registerChannelType("BASIC_CHANNEL", new BasicChannelCreator());
    }

    private void loadJoinedPlayers()
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
