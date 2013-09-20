
package net.vectorgaming.vchat.framework.channel;

/**
 *
 * @author Kenny
 */
public abstract class ChannelCreator 
{
    public abstract Channel createChannel(String name);
    
    public abstract String getType();
}
