package ca.fireball1725.hue.common.blocks.distributor;

import ca.fireball1725.hue.common.blocks.BlockBase;
import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockAdvancedPowerDistributor extends BlockBase {
  public BlockAdvancedPowerDistributor() {
    super(Properties.create(Material.IRON)
      .sound(SoundType.METAL)
      .hardnessAndResistance(2.0f)
    );
    setRegistryName("advancedpowerdistributor");
  }

  @Override
  public Item.Properties getItemProperties() {
    return new Item.Properties()
      .group(ItemGroup.HUE);
  }
}
