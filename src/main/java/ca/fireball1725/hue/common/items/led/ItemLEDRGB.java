package ca.fireball1725.hue.common.items.led;

import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.item.Item;

public class ItemLEDRGB extends ItemLEDBase {
  public ItemLEDRGB() {
    super(new Item.Properties()
      .group(ItemGroup.HUE),
      -1);
    setRegistryName("ledrgb");
  }
}
