package naturix;

import java.util.function.Supplier;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.LazyValue;

public enum ModTier implements IItemTier {
    WOOD(0, 590, 2.0F, 2.0F, 15, () -> {
        return Ingredient.fromTag(ItemTags.PLANKS);
    }),
    STONE(1, 1310, 4.0F, 3.0F, 5, () -> {
        return Ingredient.fromItems(new IItemProvider[]{Blocks.COBBLESTONE});
    }),
    IRON(2, 2500, 6.0F, 4.0F, 14, () -> {
        return Ingredient.fromItems(new IItemProvider[]{Items.IRON_INGOT});
    }),
    DIAMOND(3, 15610, 8.0F, 5.0F, 10, () -> {
        return Ingredient.fromItems(new IItemProvider[]{Items.DIAMOND});
    }),
    GOLD(0, 320, 12.0F, 2.0F, 22, () -> {
        return Ingredient.fromItems(new IItemProvider[]{Items.GOLD_INGOT});
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    private ModTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue(repairMaterial);
    }

    public int getMaxUses() {
        return this.maxUses;
    }

    public float getEfficiency() {
        return this.efficiency;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairMaterial() {
        return (Ingredient)this.repairMaterial.getValue();
    }
}
