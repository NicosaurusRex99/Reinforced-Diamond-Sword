package naturix.rds.tools;

import naturix.rds.ReinforcedDiamondSword;
import naturix.rds.proxies.ClientProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemSword extends net.minecraft.item.ItemSword {

	private String name;

	public ItemSword(ToolMaterial material, String name) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.name = name;
	}
	
	public void registerItemModel(Item item) {
		ClientProxy.registerItemRenderer(this, 0, name);
	}
	@Override
	public ItemSword setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(ReinforcedDiamondSword.ReinforcedDiamondSword);
		return this;
	}


}