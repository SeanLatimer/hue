package ca.fireball1725.hue.common.items.bulb;

import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.item.Item;

public class ItemBulbLarge extends ItemBulbBase {
  public ItemBulbLarge() {
    super(new Item.Properties()
      .group(ItemGroup.HUE));
    setRegistryName("bulblarge");
  }
}
