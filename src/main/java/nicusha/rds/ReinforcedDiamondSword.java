package nicusha.rds;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nicusha.rds.data.DataGen;
import nicusha.rds.registry.ItemRegistry;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(ReinforcedDiamondSword.MODID)
public class ReinforcedDiamondSword {
    public static final String MODID = "rds";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup." + MODID)).withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> ItemRegistry.diamond_sword.get().getDefaultInstance()).build());

    public ReinforcedDiamondSword(IEventBus bus, ModContainer container) {
        bus.addListener(this::creativeTab);
        ItemRegistry.ITEMS.register(bus);
        CREATIVE_MODE_TABS.register(bus);
        bus.addListener(DataGen::genData);
    }

    public void creativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == TAB.get()) {
            for (var regObj : ItemRegistry.ITEMS.getEntries()) {
                event.accept(regObj.get());
            }
        }
    }
}
