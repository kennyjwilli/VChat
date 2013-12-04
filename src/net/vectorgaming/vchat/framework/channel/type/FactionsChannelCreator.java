
package net.vectorgaming.vchat.framework.channel.type;

import net.vectorgaming.vchat.framework.channel.Channel;
import net.vectorgaming.vchat.framework.channel.ChannelCreator;

/**
 *
 * @author Kenny
 */
public class FactionsChannelCreator extends ChannelCreator
{

    @Override
    public Channel createChannel(String name)
    {
        return new FactionsChannel(name);
    }

    @Override
    public String getType()
    {
        return "FACTIONS";
    }

}
