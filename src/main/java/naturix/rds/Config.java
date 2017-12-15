package naturix.rds;

import org.apache.logging.log4j.Level;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;
import naturix.rds.proxies.CommonProxy;

public class Config {

    private static final String CATEGORY_GENERAL = "general";
    public static int woodDamage;
    
    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            ReinforcedDiamondSword.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        woodDamage = cfg.getInt("Wood Damage", CATEGORY_GENERAL, 2, 1, 1000, "The amount of damage the sword does");
        
    }

}