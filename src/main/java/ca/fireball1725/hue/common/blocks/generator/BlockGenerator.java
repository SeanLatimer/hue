package ca.fireball1725.hue.common.blocks.generator;

import ca.fireball1725.hue.common.blocks.BlockBase;
import ca.fireball1725.hue.common.items.ItemGroup;
import ca.fireball1725.hue.common.tileentities.generator.TileEntityGenerator;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockGenerator extends BlockBase {
  public BlockGenerator() {
    super(Properties.create(Material.IRON)
      .sound(SoundType.METAL)
      .hardnessAndResistance(2.0f)
    );
    setRegistryName("generator");
    setTileEntity(TileEntityGenerator::new);
    setCanRotate(true);
  }

  @Override
  public Item.Properties getItemProperties() {
    return new Item.Properties()
      .group(ItemGroup.HUE);
  }
}
