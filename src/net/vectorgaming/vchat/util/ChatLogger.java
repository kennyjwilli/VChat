
package net.vectorgaming.vchat.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.vectorgaming.vchat.VChatAPI;
import org.bukkit.Bukkit;

/**
 *
 * @author Kenny
 */
public class ChatLogger 
{

    /**
     * Logs the chat message to the console
     * @param message Message to be logged
     */
    public static void logToConsole(String message)
    {
        Bukkit.getLogger().log(Level.INFO, "[VChat] "+message.replaceAll("§", "@"));
    }
    
    /**
     * Logs the message to the chat log
     * @param message Message to be logged
     */
    public static void logToFile(String message)
    {
        File logFile = new File(VChatAPI.getPlugin().getDataFolder()+File.separator+"logs"+File.separator+"log.txt");
        try
        {
            if(!logFile.exists())
            {
                logFile.createNewFile();
            }
        } catch (IOException ex){}
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        String formatted = dateFormat.format(new Date())+" "+message;
        BufferedWriter writer;
        try
        {
            writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.write(formatted);
            writer.newLine();
            writer.close();
        }catch(IOException ex)
        {
            Bukkit.getLogger().log(Level.SEVERE, "[VChat] Could not log chat to file!");
        }
    }
}
