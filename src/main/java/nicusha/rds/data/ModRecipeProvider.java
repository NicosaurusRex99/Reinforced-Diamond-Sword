package nicusha.rds.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import nicusha.rds.registry.ItemRegistry;

import static nicusha.rds.ReinforcedDiamondSword.MODID;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    protected void buildRecipes() {
        shaped(ItemRegistry.wood_sword.get(), 1, new String[]{" WO", "WOW", "SW "}, "W", ItemTags.PLANKS, "O", Blocks.OBSIDIAN, "S", Items.STICK);
        shaped(ItemRegistry.golden_sword.get(), 1, new String[]{" WO", "WOW", "SW "}, "W", ItemTags.create(Identifier.fromNamespaceAndPath("c", "ingots/gold")), "O", Blocks.OBSIDIAN, "S", Items.STICK);
        shaped(ItemRegistry.stone_sword.get(), 1, new String[]{" WO", "WOW", "SW "}, "W", ItemTags.STONE_CRAFTING_MATERIALS, "O", Blocks.OBSIDIAN, "S", Items.STICK);
        shaped(ItemRegistry.iron_sword.get(), 1, new String[]{" WO", "WOW", "SW "}, "W", ItemTags.create(Identifier.fromNamespaceAndPath("c", "ingots/iron")), "O", Blocks.OBSIDIAN, "S", Items.STICK);
        shaped(ItemRegistry.diamond_sword.get(), 1, new String[]{" WO", "WOW", "SW "}, "W", ItemTags.create(Identifier.fromNamespaceAndPath("c", "gems/diamond")), "O", Blocks.OBSIDIAN, "S", Items.STICK);
        shaped(ItemRegistry.netherite_sword.get(), 1, new String[]{" WO", "WOW", "SW "}, "W", ItemTags.create(Identifier.fromNamespaceAndPath("c", "ingots/netherite")), "O", Blocks.OBSIDIAN, "S", Items.NETHERITE_SWORD);
        shaped(ItemRegistry.copper_sword.get(), 1, new String[]{" WO", "WOW", "SW "}, "W", ItemTags.create(Identifier.fromNamespaceAndPath("c", "ingots/copper")), "O", Blocks.OBSIDIAN, "S", Items.STICK);
    }

    private void shaped(ItemLike result, int count, String[] pattern, Object... keys) {
        shaped(result, count, "", pattern, keys);
    }

    private void shaped(ItemLike result, int count, String pathSuffix, String[] pattern, Object... keys) {
        String defaultPath = getItemName(result) + (count > 1 ? "_" + count : "");
        String path = defaultPath + (pathSuffix.isEmpty() ? "" : "_" + pathSuffix);

        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, result, count);
        for (String row : pattern) {
            builder.pattern(row);
        }

        ItemLike firstUnlockItem = null;
        for (int i = 0; i < keys.length; i += 2) {
            char c;
            Object keyObj = keys[i];
            if (keyObj instanceof Character character) {
                c = character;
            } else if (keyObj instanceof String s && !s.isEmpty()) {
                c = s.charAt(0);
            } else {
                throw new IllegalArgumentException("Expected Character or String for recipe key at index " + i);
            }

            Object obj = keys[i + 1];
            if (firstUnlockItem == null && obj instanceof ItemLike il) {
                firstUnlockItem = il;
            }

            if (obj instanceof ItemLike il) {
                builder.define(c, il);
            } else if (obj instanceof TagKey<?> tag) {
                @SuppressWarnings("unchecked")
                TagKey<Item> itemTag = (TagKey<Item>) tag;
                builder.define(c, itemTag);
            }
        }

        if (firstUnlockItem != null) {
            builder.unlockedBy(getHasName(firstUnlockItem), has(firstUnlockItem));
        } else {
            builder.unlockedBy("has_item", has(result));
        }

        if (path.equals(defaultPath)) {
            builder.save(output);
        } else {
            builder.save(output, String.valueOf(Identifier.fromNamespaceAndPath(MODID, path)));
        }
    }
}