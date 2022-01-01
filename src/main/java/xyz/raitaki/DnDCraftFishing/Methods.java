package xyz.raitaki.DnDCraftFishing;

import net.kyori.adventure.text.Component;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Methods {

    public static ArrayList<Component> stringToComp(ArrayList<String> list){
        ArrayList<Component> comps = new ArrayList<>();
        for(String string : list){
            comps.add(Component.text(string));
        }
        return comps;
    }

    public static boolean isFish(ItemStack item){
        boolean b = false;
        for(CustomFishes fish : Main.fishList){
            if(item.getItemMeta().displayName() == fish.getFish().displayName()) b = true;
        }
        return b;
    }

    public static CustomFishes getFishType(ItemStack item){
        CustomFishes fishType = null;
        for(CustomFishes fish : Main.fishList){
            if(fish.getFish().displayName().equals(item.displayName()) && fish.getFish().getType().equals(item.getType())) fishType = fish;
        }
        return fishType;
    }

    public static boolean isRod(ItemStack item){
        boolean b = false;
        for(CustomRod rod : Main.rodList){
            if(item.getItemMeta().displayName() == rod.getRod().displayName()) b = true;
        }
        return b;
    }

    public static CustomRod getRodType(ItemStack item){
        CustomRod rodType = null;
        for(CustomRod rod : Main.rodList){
            if(rod.getRod().displayName().equals(item.displayName()) && rod.getRod().getType().equals(item.getType())) rodType = rod;
        }
        return rodType;
    }

    public static void addMass(ItemStack item, Integer mass){
        ItemMeta itemMeta = item.getItemMeta();
        List<Component> list = itemMeta.lore();
        list.add(Component.text("§6"+mass+"oz"));

        itemMeta.lore(list);
        item.setItemMeta(itemMeta);
    }

    public static Integer getRandomMass(CustomFishes fish){
        Random r = new Random();
        int mass = r.nextInt(fish.getMaxWeight() - fish.getMinWeight()) + fish.getMinWeight();
        return  mass;
    }

    public static List<Component> addRandomQuality(ItemStack item){
        List<Component> lorelist = item.getItemMeta().lore();
        lorelist.add(lorelist.get(lorelist.size()));
        lorelist.set(lorelist.size()-1, getRandomQuality());
        return lorelist;
    }

    public static Component getRandomQuality(){
        String quality = Main.qualityList.get(new Random().nextInt(Main.qualityList.size()));
        return Component.text(quality);
    }

    public static Boolean isFished(PlayerFishEvent.State state){
        return state == PlayerFishEvent.State.CAUGHT_FISH || state == PlayerFishEvent.State.CAUGHT_ENTITY;
    }
}
