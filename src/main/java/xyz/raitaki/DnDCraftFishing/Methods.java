package xyz.raitaki.DnDCraftFishing;

import net.kyori.adventure.text.Component;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.ArrayList;

public class Methods {

    public static ArrayList<Component> stringToComp(ArrayList<String> list){
        ArrayList<Component> comps = new ArrayList<>();
        for(String string : list){
            comps.add(Component.text(string));
        }
        return comps;
    }

    public static Boolean isFished(PlayerFishEvent.State state){
        return state == PlayerFishEvent.State.CAUGHT_FISH || state == PlayerFishEvent.State.CAUGHT_ENTITY;
    }
}
