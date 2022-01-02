package xyz.raitaki.DnDCraftFishing;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.io.File;
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

    //Checks item is fish
    public static boolean isFish(ItemStack item){
        boolean b = false;
        if(!item.hasItemMeta()) return false;
        for(CustomFish fish : Main.fishList){
            if(item.getItemMeta().displayName().toString().contains(fish.getName())){
                b = true;
            }
        }
        return b;
    }

    //Gets item's fishtype
    public static CustomFish getFishType(ItemStack item){
        CustomFish fishType = null;
        for(CustomFish fish : Main.fishList){
            if(item.getItemMeta().displayName().toString().contains(fish.getName()) &&
                    fish.getFish().getType().equals(item.getType())) fishType = fish;
        }
        return fishType;
    }

    //Checks item is rod
    public static boolean isRod(ItemStack item){
        boolean b = false;
        for(CustomRod rod : Main.rodList){
            if(item.getItemMeta().displayName().toString().contains(rod.getRodname())) b = true;
        }
        return b;
    }

    //Gets item's rodtype
    public static CustomRod getRodType(ItemStack item){
        CustomRod rodType = null;
        for(CustomRod rod : Main.rodList){
            if(item.getItemMeta().displayName().toString().contains(rod.getRodname()) &&
                    rod.getRod().getType().equals(item.getType())) rodType = rod;
        }
        return rodType;
    }

    //Adds mass to item
    public static void addMass(ItemStack item, Integer mass){
        ItemMeta itemMeta = item.getItemMeta();
        List<Component> list = itemMeta.lore();
        list.add(Component.text("§6"+mass+"oz"));

        itemMeta.lore(list);
        item.setItemMeta(itemMeta);
    }

    //Gets random mass from fish's type
    public static Integer getRandomMass(CustomFish fish){
        Random r = new Random();
        int mass = r.nextInt(fish.getMaxWeight() - fish.getMinWeight()) + fish.getMinWeight();
        return  mass;
    }

    //Adds random quality to item
    public static void addRandomQuality(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();
        for(String s : Main.qualityList){
            for(Component component : itemMeta.lore()){
                if(component.toString().contains(s)) return;
            }
        }

        ArrayList<Component> lorelist = new ArrayList<>(item.getItemMeta().lore());
        lorelist.add(lorelist.get(lorelist.size()-1));
        lorelist.set(lorelist.size()-1, getRandomQuality());
        itemMeta.lore(lorelist);
        item.setItemMeta(itemMeta);
    }

    //Gets random quality
    public static Component getRandomQuality(){
        String quality = Main.qualityList.get(new Random().nextInt(Main.qualityList.size()));
        return Component.text(quality);
    }

    //Gets name from config
    public static String getName(List list){
        return (String) list.get(0);
    }

    //Gets lore from config
    public static ArrayList<String> getLore(List list){
        ArrayList<String> lorelist = new ArrayList<>();
        lorelist.add((String) list.get(1));
        return lorelist;
    }

    //Gets fish rarity from config
    public static CustomFish.FishRarity getRarity(List list){
        String rarity = String.valueOf(list.get(2));
        CustomFish.FishRarity rarity1 = null;
        for(CustomFish.FishRarity fishRarity : CustomFish.FishRarity.values()){
            if(fishRarity.toString().contains(rarity)) rarity1 = fishRarity;
        }
        return rarity1;
    }

    //Gets chance from config
    public static Integer getChance(List list){
        return (Integer) list.get(3);
    }

    //Gets min weight from config
    public static Integer getMinWeight(List list){
        return (Integer) list.get(4);
    }

    //Gets max weight from config
    public static Integer getMaxWeight(List list){
        return (Integer) list.get(5);
    }

    //Gets itemtype from config
    public static ItemStack getItemType(List list){
        String text = String.valueOf(list.get(6));
        Material mat = Material.getMaterial(text.toUpperCase());
        return new ItemStack(mat);
    }

    //Gets custommedeldata from config
    public static Integer getCustomModelData(List list){
        return (Integer) list.get(7);
    }

    //Checks fishing state from config
    public static Boolean isFished(PlayerFishEvent.State state){
        return state == PlayerFishEvent.State.CAUGHT_FISH || state == PlayerFishEvent.State.CAUGHT_ENTITY;
    }

    //Creates fishes from config
    public static void createFishes() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(Main.getInstance().getDataFolder(), "config.yml"));
        int fishcount = config.getInt("fishcount");
        for (int i = 1; i <= fishcount; i++) {
            List<?> fishconfig = config.getList("fish" + i);
            CustomFish fish = new CustomFish();
            fish.setRarity(Methods.getRarity(fishconfig));
            fish.setFish(Methods.getItemType(fishconfig));
            fish.setChance(Methods.getChance(fishconfig));
            fish.setCustommodeldata(Methods.getCustomModelData(fishconfig));
            fish.setLore(Methods.getLore(fishconfig));
            fish.setName(Methods.getName(fishconfig));
            fish.setWeights(Methods.getMaxWeight(fishconfig), Methods.getMinWeight(fishconfig));
            fish.createItem();
            Main.fishList.add(fish);
            Main.rc.add(fish);
        }
        Main.qualityList.add("§7Poor Quality");
        Main.qualityList.add("§eExcellent Quality");
        Main.qualityList.add("§dNormal Quality");
    }
}
