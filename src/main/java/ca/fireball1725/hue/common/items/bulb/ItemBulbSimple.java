package ca.fireball1725.hue.common.items.bulb;

import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.item.Item;

public class ItemBulbSimple extends ItemBulbBase {
  public ItemBulbSimple() {
    super(new Item.Properties()
      .group(ItemGroup.HUE));
    setRegistryName("bulbsimple");
  }
}
