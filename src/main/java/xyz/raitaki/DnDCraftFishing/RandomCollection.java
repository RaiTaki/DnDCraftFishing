package xyz.raitaki.DnDCraftFishing;


import org.bukkit.inventory.ItemStack;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<ItemStack> {

    //i took this class in this post
    //https://stackoverflow.com/questions/6409652/random-weighted-selection-in-java

    private final NavigableMap<Double, ItemStack> map = new TreeMap<Double, ItemStack>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection<ItemStack> add(double weight, ItemStack result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    public ItemStack next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }
}
