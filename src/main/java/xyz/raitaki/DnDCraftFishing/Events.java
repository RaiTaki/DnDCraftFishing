package xyz.raitaki.DnDCraftFishing;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import java.util.HashMap;

public class Events implements Listener {

    //list of fishers
    public static HashMap<Player, FishingMenu> fishinglist = new HashMap<>();


    @EventHandler
    public void CatchingFish(PlayerFishEvent event){
        if(event.getState() == PlayerFishEvent.State.BITE){
            event.setCancelled(true);
        }
        if(event.getState() == PlayerFishEvent.State.FISHING && fishinglist.get(event.getPlayer()) == null){
            FishingMenu fishingMenu = new FishingMenu();
            Bukkit.getPluginManager().registerEvents(fishingMenu, Main.getInstance());
            fishingMenu.setPlayer(event.getPlayer());
            fishingMenu.start();
            fishinglist.put(event.getPlayer(), fishingMenu);
        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event){
        if(fishinglist.containsKey(event.getWhoClicked())){
            fishinglist.get(event.getWhoClicked()).eventchecker(event);
        }
    }
}
