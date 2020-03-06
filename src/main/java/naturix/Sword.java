package naturix;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class Sword extends SwordItem {

    public Sword(String name, IItemTier tier)
    {
        super(tier, (int)tier.getAttackDamage(), tier.getEfficiency()/24, new Item.Properties().group(Main.setup.itemGroup));
        setRegistryName(name);
        ModItems.ITEMS.add(this);
    }
}