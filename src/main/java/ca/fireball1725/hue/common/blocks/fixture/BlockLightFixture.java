package ca.fireball1725.hue.common.blocks.fixture;

import ca.fireball1725.hue.common.blocks.BlockBase;
import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public class BlockLightFixture extends BlockBase {
  private int lightValue = 0;

  public BlockLightFixture() {
    super(Properties.create(Material.IRON)
      .sound(SoundType.METAL)
      .hardnessAndResistance(2.0f)
      .lightValue(15)
    );
    setRegistryName("fixture");
  }

  @Override
  public Item.Properties getItemProperties() {
    return new Item.Properties()
      .group(ItemGroup.HUE);
  }
}
