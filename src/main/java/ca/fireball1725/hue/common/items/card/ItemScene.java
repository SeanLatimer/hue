package ca.fireball1725.hue.common.items.card;

import ca.fireball1725.hue.common.items.ItemBase;
import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.item.Item;

public class ItemScene extends ItemBase {
  public ItemScene() {
    super(new Item.Properties()
      .group(ItemGroup.HUE));
    setRegistryName("scene");
  }
}
