package ca.fireball1725.hue.common.items.bulb;

import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.item.Item;

public class ItemBulb extends ItemBulbBase {
  public ItemBulb() {
    super(new Item.Properties()
        .group(ItemGroup.HUE));
    setRegistryName("bulb");
  }
}
