package ca.fireball1725.hue.common.items.battery;

import ca.fireball1725.hue.common.items.ItemBase;
import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.item.Item;

public class ItemBattery extends ItemBase {
  public ItemBattery() {
    super(new Item.Properties()
      .group(ItemGroup.HUE));
    setRegistryName("battery");
  }
}
