package ca.fireball1725.hue.common.items.led;

import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.item.Item;

public class ItemLEDBright extends ItemLEDBase {
  public ItemLEDBright() {
    super(new Item.Properties()
      .group(ItemGroup.HUE),
      0xFFFFFF);
    setRegistryName("ledbright");
  }
}
