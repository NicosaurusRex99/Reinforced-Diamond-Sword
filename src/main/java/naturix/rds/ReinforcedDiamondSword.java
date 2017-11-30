package naturix.rds;
import naturix.rds.proxies.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ReinforcedDiamondSword.MODID, version = ReinforcedDiamondSword.VERSION, name = ReinforcedDiamondSword.MODNAME)
public class ReinforcedDiamondSword
{
    public static final String MODID = "rds";
    public static final String VERSION = "1.12.2.0";
    public static final String MODNAME = "Reinforced Diamond Sword";
    
    @SidedProxy(clientSide = "naturix.rds.proxies.ClientProxy", serverSide = "naturix.rds.proxies.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static ReinforcedDiamondSword instance;

    public static org.apache.logging.log4j.Logger logger;

    public static final Item.ToolMaterial ReinforcedWoodToolMaterial = EnumHelper.addToolMaterial("RWOOD", 2, 120, 6, 2, 14);
    public static final Item.ToolMaterial ReinforcedStoneToolMaterial = EnumHelper.addToolMaterial("RSTONE", 2, 264, 6, 3, 14);
    public static final Item.ToolMaterial ReinforcedGoldToolMaterial = EnumHelper.addToolMaterial("RGOLD", 2, 66, 6, 4, 14);
    public static final Item.ToolMaterial ReinforcedIronToolMaterial = EnumHelper.addToolMaterial("RIRON", 2, 502, 6, 5, 14);
    public static final Item.ToolMaterial ReinforcedDiamondToolMaterial = EnumHelper.addToolMaterial("RDIAMOND", 2, 3124, 6, 6, 14);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
    @Mod.EventBusSubscriber
	public static class RegistrationHandler {
    	@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
    		ModItems.register(event.getRegistry());
    		ModItems.registerModels();
		}
	}
    public static final CreativeTabs ReinforcedDiamondSword = new CreativeTabs("ReinforcedDiamondSword")
    {

        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.reinforcedDiamond);
        }
		
    };
}