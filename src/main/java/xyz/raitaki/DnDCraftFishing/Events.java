package xyz.raitaki.DnDCraftFishing;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Objects;

public class Events implements Listener {
    @EventHandler
    public void CatchingFish(PlayerFishEvent event){
        if(Methods.isFished(event.getState())){
            event.getCaught().remove();
            event.getPlayer().getInventory().addItem(Main.rc.next());
        }
    }
}
