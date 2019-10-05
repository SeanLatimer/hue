package ca.fireball1725.hue.common.items;

import ca.fireball1725.hue.common.blocks.Blocks;
import net.minecraft.item.ItemStack;

public class ItemGroup {
  public static final net.minecraft.item.ItemGroup HUE = new net.minecraft.item.ItemGroup("hue") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(Blocks.BLOCKLIGHTFIXTURE.getBlock());
    }
  };
}
