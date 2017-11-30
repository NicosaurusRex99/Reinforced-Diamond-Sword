package naturix.rds;

import org.apache.logging.log4j.core.Logger;

import naturix.rds.proxies.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
}