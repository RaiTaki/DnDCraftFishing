package xyz.raitaki.DnDCraftFishing;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;
import java.util.Objects;

public class Events implements Listener {
    @EventHandler
    public void CatchingFish(PlayerFishEvent event){
        if(Methods.isFished(event.getState())){
            event.getCaught().remove();
            ItemStack fish = Main.rc.nextEdited(20,15,3).clone();
            int mass       = Methods.getRandomMass(Methods.getFishType(fish));
            Methods.addMass(fish, mass);
            event.getPlayer().getInventory().addItem(fish);
        }
    }
}
