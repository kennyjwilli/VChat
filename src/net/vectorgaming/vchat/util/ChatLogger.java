
package net.vectorgaming.vchat.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
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
        logFile.mkdirs();
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        String formatted = dateFormat.format(new Date())+" "+message+"\n";
        try
        {
            FileWriter fw = new FileWriter(logFile, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(formatted);
            pw.flush();
        }catch(IOException ex)
        {
            Bukkit.getLogger().log(Level.SEVERE, "[VChat] Could not log chat to file!");
        }
    }
}
