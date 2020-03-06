package naturix;

import net.minecraft.item.*;

public class Setup {
    public ItemGroup itemGroup = new ItemGroup(Reference.MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.diamond);
        }
    };

    public void init() {

    }
}
