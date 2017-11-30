package naturix.rds;

import naturix.rds.tools.ItemSword;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

		public static ItemSword reinforcedWood = new ItemSword(ReinforcedDiamondSword.ReinforcedWoodToolMaterial, "ReinforcedWoodSword");
		public static ItemSword reinforcedStone = new ItemSword(ReinforcedDiamondSword.ReinforcedStoneToolMaterial, "ReinforcedStoneSword");
		public static ItemSword reinforcedGold = new ItemSword(ReinforcedDiamondSword.ReinforcedGoldToolMaterial, "ReinforcedGoldSword");
		public static ItemSword reinforcedIron = new ItemSword(ReinforcedDiamondSword.ReinforcedIronToolMaterial, "ReinforcedIronSword");
		public static ItemSword reinforcedDiamond = new ItemSword(ReinforcedDiamondSword.ReinforcedDiamondToolMaterial, "ReinforcedDiamondSword");
		
		public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
					reinforcedWood,
					reinforcedStone,
					reinforcedGold,
					reinforcedIron,
					reinforcedDiamond
			);
		}

		public static void registerModels() {

			reinforcedWood.registerItemModel(reinforcedWood);
			reinforcedStone.registerItemModel(reinforcedStone);
			reinforcedGold.registerItemModel(reinforcedGold);
			reinforcedIron.registerItemModel(reinforcedIron);
			reinforcedDiamond.registerItemModel(reinforcedDiamond);
		}

	}
