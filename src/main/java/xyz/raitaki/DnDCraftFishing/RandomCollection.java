package xyz.raitaki.DnDCraftFishing;

import org.bukkit.inventory.ItemStack;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection {

    //i took this class in this stack overflow post
    //https://stackoverflow.com/questions/6409652/random-weighted-selection-in-java

    private final NavigableMap<Double, CustomFish> map = new TreeMap<Double, CustomFish>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection add(CustomFish fish) {
        int weight = fish.getChance();
        if (weight <= 0) return this;
        total += weight;
        map.put(total, fish);
        return this;
    }

    public ItemStack next() {
        return this.getrandomfromlist(map, total);
    }

    private ItemStack getrandomfromlist(NavigableMap<Double, CustomFish> map, Double total){
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
        NavigableMap<Double, CustomFish> map2 = new TreeMap<>();
        for(CustomFish fish : map.values()){
            if(fish.getRarity() == CustomFish.FishRarity.Common){
                map2.put((double) (commonchance+fish.getChance()), fish);
                totaledited += commonchance+fish.getChance();
            }
            else if(fish.getRarity() == CustomFish.FishRarity.Rare){
                map2.put((double) (rarechance+fish.getChance()), fish);
                totaledited += rarechance+fish.getChance();
            }
            else{
                map2.put((double) (legendarychance+fish.getChance()), fish);
            }
        }
        ItemStack fish = this.getrandomfromlist(map2, (double) totaledited).clone();
        int mass       = Methods.getRandomMass(Methods.getFishType(fish));
        Methods.addMass(fish, mass);
        return fish;
    }
}
