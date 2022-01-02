package xyz.raitaki.DnDCraftFishing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;


public final class Main extends JavaPlugin {

    public static ArrayList<CustomFish> fishList   = new ArrayList<>();
    public static ArrayList<String> qualityList      = new ArrayList<>();
    public static ArrayList<CustomRod> rodList       = new ArrayList<>();
    public static RandomCollection rc                = new RandomCollection();


    private static Main instance;
    public static Main getInstance() {return instance;}
    private void registerCommands(String[] cmds, CommandExecutor cmdExecutor)
    {
        for (String cmd : cmds)
        {
            getCommand(cmd).setExecutor(cmdExecutor);
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        registerCommands(new String[] { "qualitycheck" }, new Commands() );
        Bukkit.getLogger().info(ChatColor.LIGHT_PURPLE+"DnDCraft Fishing plugin is on.");
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        saveDefaultConfig();
        Methods.createFishes();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
