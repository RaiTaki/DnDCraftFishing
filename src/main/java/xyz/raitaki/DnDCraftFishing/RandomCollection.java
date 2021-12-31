package xyz.raitaki.DnDCraftFishing;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection {

    //i took this class in this post
    //https://stackoverflow.com/questions/6409652/random-weighted-selection-in-java

    private final NavigableMap<Double, CustomFishes> map = new TreeMap<Double, CustomFishes>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection add(CustomFishes fish) {
        int weight = fish.getChance();
        if (weight <= 0) return this;
        total += weight;
        map.put(total, fish);
        return this;
    }

    public ItemStack next() {
        return this.getrandomfromlist(map, total);
    }

    private ItemStack getrandomfromlist(NavigableMap<Double, CustomFishes> map, Double total){
        ItemStack item = null;
        do{
            double value = random.nextDouble() * total;
            if(map.higherEntry(value) != null){
                item = map.higherEntry(value).getValue().getFish();
                break;
            }
        }
        while(item == null);
        return item;
    }

    public ItemStack nextEdited(Integer commonchance, Integer rarechance, Integer legendarychance){
        int totaledited = 0;
        NavigableMap<Double, CustomFishes> map2 = new TreeMap<>();
        for(CustomFishes fish : map.values()){
            if(fish.getRarity() == CustomFishes.FishRarity.Common){
                map2.put((double) (commonchance+fish.getChance()), fish);
                totaledited += commonchance+fish.getChance();
            }
            else if(fish.getRarity() == CustomFishes.FishRarity.Rare){
                map2.put((double) (rarechance+fish.getChance()), fish);
                totaledited += rarechance+fish.getChance();
            }
            else{
                map2.put((double) (legendarychance+fish.getChance()), fish);
            }
        }
        Bukkit.getPlayer("RaiTaki").sendMessage(String.valueOf((double) totaledited));
        return this.getrandomfromlist(map2, (double) totaledited);
    }
}
