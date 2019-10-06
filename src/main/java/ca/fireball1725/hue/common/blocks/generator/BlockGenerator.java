package ca.fireball1725.hue.common.blocks.generator;

import ca.fireball1725.hue.common.blocks.BlockBase;
import ca.fireball1725.hue.common.items.ItemGroup;
import ca.fireball1725.hue.common.tileentities.generator.TileEntityGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;

public class BlockGenerator extends BlockBase {
  public BlockGenerator() {
    super(Properties.create(Material.IRON)
      .sound(SoundType.METAL)
      .hardnessAndResistance(2.0f)
            .lightValue(14)
    );
    setRegistryName("generator");
    setTileEntity(TileEntityGenerator::new);
  }

  @Override
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    super.fillStateContainer(builder);
    builder.add(BlockStateProperties.POWERED);
  }

  @Override
  public Item.Properties getItemProperties() {
    return new Item.Properties()
            .group(ItemGroup.HUE);
  }

  @Override
  public int getLightValue(BlockState state) {
    return state.get(BlockStateProperties.POWERED) ? super.getLightValue(state) : 0;
  }


}
