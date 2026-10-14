package nicusha.rds.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import nicusha.rds.registry.ItemRegistry;

import java.util.concurrent.CompletableFuture;

import static nicusha.rds.ReinforcedDiamondSword.MODID;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.SWORDS).add(ItemRegistry.copper_sword.key(), ItemRegistry.diamond_sword.key(), ItemRegistry.golden_sword.key(), ItemRegistry.iron_sword.key(), ItemRegistry.netherite_sword.key(), ItemRegistry.stone_sword.key(), ItemRegistry.wood_sword.key());
        tag(ItemTags.MELEE_WEAPON_ENCHANTABLE).add(ItemRegistry.copper_sword.key(), ItemRegistry.diamond_sword.key(), ItemRegistry.golden_sword.key(), ItemRegistry.iron_sword.key(), ItemRegistry.netherite_sword.key(), ItemRegistry.stone_sword.key(), ItemRegistry.wood_sword.key());
        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE).add(ItemRegistry.copper_sword.key(), ItemRegistry.diamond_sword.key(), ItemRegistry.golden_sword.key(), ItemRegistry.iron_sword.key(), ItemRegistry.netherite_sword.key(), ItemRegistry.stone_sword.key(), ItemRegistry.wood_sword.key());
        tag(ItemTags.WEAPON_ENCHANTABLE).add(ItemRegistry.copper_sword.key(), ItemRegistry.diamond_sword.key(), ItemRegistry.golden_sword.key(), ItemRegistry.iron_sword.key(), ItemRegistry.netherite_sword.key(), ItemRegistry.stone_sword.key(), ItemRegistry.wood_sword.key());

    }

    private TagKey<Item> commonTag(String name){
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", name));
    }
    private TagKey<Item> minecraftTag(String name){
        return TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace(name));
    }
    private TagKey<Item> customTag(String name){
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, name));
    }

}
