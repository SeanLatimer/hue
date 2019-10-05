package ca.fireball1725.hue.common.items.led;

import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.item.Item;

public class ItemLEDIR extends ItemLEDBase {
  public ItemLEDIR() {
    super(new Item.Properties()
      .group(ItemGroup.HUE),
      -1);
    setRegistryName("ledir");
  }
}
