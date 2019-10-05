package ca.fireball1725.hue.common.items.tool;

import ca.fireball1725.hue.common.items.ItemBase;
import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToolWrench extends ItemBase {
  public ToolWrench() {
    super(new Item.Properties()
      .maxStackSize(1)
      .group(ItemGroup.HUE));
    setRegistryName("wrench");
  }

  @Override
  public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
    BlockPos blockPos = context.getPos();
    World world = context.getWorld();

    world.getBlockState(blockPos).getBlock().rotate(world.getBlockState(blockPos), world, blockPos, Rotation.CLOCKWISE_90);

    return !world.isRemote ? ActionResultType.FAIL : ActionResultType.PASS;
  }
}
