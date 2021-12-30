package xyz.raitaki.DnDCraftFishing;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class Main extends JavaPlugin {

    public static RandomCollection<ItemStack> rc = new RandomCollection<>();
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
        registerCommands(new String[] { "raitest"}, new Commands() );
        Bukkit.getLogger().info("DnDCraft Fishing plugin is on.");
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        createFishes();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void createFishes(){
        ArrayList<String> string = new ArrayList<>();
        string.add("test1");
        string.add("test2");
        CustomFishes fishes = new CustomFishes();
        fishes.setFish(new ItemStack(Material.SALMON));
        fishes.setChance(5);
        fishes.setCustommodeldata(0);
        fishes.setLore(string);
        fishes.setName("test1");
        fishes.setSound(Sound.ENTITY_WITHER_AMBIENT);
        fishes.setWeights(12,5);
        fishes.createItem();


        ArrayList<String> string2 = new ArrayList<>();
        string.add("test1");
        string.add("test2");
        CustomFishes fishes2 = new CustomFishes();
        fishes2.setFish(new ItemStack(Material.SALMON));
        fishes2.setChance(10);
        fishes2.setCustommodeldata(0);
        fishes2.setLore(string2);
        fishes2.setName("test2");
        fishes2.setSound(Sound.ENTITY_WITHER_AMBIENT);
        fishes2.setWeights(12,5);
        fishes2.createItem();

        ArrayList<String> string3 = new ArrayList<>();
        string.add("test1");
        string.add("test2");
        CustomFishes fishes3 = new CustomFishes();
        fishes3.setFish(new ItemStack(Material.SALMON));
        fishes3.setChance(20);
        fishes3.setCustommodeldata(0);
        fishes3.setLore(string3);
        fishes3.setName("test3");
        fishes3.setSound(Sound.ENTITY_WITHER_AMBIENT);
        fishes3.setWeights(12,5);
        fishes3.createItem();

        ArrayList<String> string4 = new ArrayList<>();
        string.add("test1");
        string.add("test2");
        CustomFishes fishes4 = new CustomFishes();
        fishes4.setFish(new ItemStack(Material.SALMON));
        fishes4.setChance(30);
        fishes4.setCustommodeldata(0);
        fishes4.setLore(string4);
        fishes4.setName("test4");
        fishes.setSound(Sound.ENTITY_WITHER_AMBIENT);
        fishes4.setWeights(12,5);
        fishes4.createItem();

        rc.add(fishes.getChance(), fishes.getFish());
        rc.add(fishes2.getChance(), fishes2.getFish());
        rc.add(fishes3.getChance(), fishes3.getFish());
        rc.add(fishes4.getChance(), fishes4.getFish());
    }
}
