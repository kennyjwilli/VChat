package net.vectorgaming.vchat;

import net.vectorgaming.vchat.framework.channel.type.BasicChannelCreator;
import net.vectorgaming.vchat.listeners.ChatListener;
import net.vectorgaming.vcore.framework.VertexAPI;
import net.vectorgaming.vcore.framework.VertexPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 *
 * @author Kenny
 */
public class VChat extends VertexPlugin
{
    private VChatAPI api;
    
    @Override
    public void onEnable()
    {
        api = new VChatAPI(this);
        setupCommands();
        setupListeners();
        registerChannels();
    }

    @Override
    public void onDisable()
    {
        
    }

    @Override
    public void setupCommands()
    {
        
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
    }
    
    private void registerChannels()
    {
        VChatAPI.registerChannelType("BASIC_CHANNEL", new BasicChannelCreator());
    }
}
