
package net.vectorgaming.vchat.framework.channel.type;

import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vchat.framework.channel.ChannelCreator;

/**
 *
 * @author Kenny
 */
public class BasicChannelCreator extends ChannelCreator
{

    @Override
    public Channel createChannel(String name)
    {
        return new BasicChannel(name);
    }

    @Override
    public String getType()
    {
        return("BASIC_CHANNEL");
    }

}
