package ca.fireball1725.hue.common.tileentities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;

import javax.annotation.Nullable;

public class TileEntityInventoryBase extends TileEntityBase implements ISidedInventory {
  public TileEntityInventoryBase(TileEntityType<?> tileEntityTypeIn) {
    super(tileEntityTypeIn);
  }

  @Override
  public int[] getSlotsForFace(Direction side) {
    return new int[0];
  }

  @Override
  public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
    return false;
  }

  @Override
  public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
    return false;
  }

  @Override
  public int getSizeInventory() {
    return 0;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public ItemStack getStackInSlot(int index) {
    return null;
  }

  @Override
  public ItemStack decrStackSize(int index, int count) {
    return null;
  }

  @Override
  public ItemStack removeStackFromSlot(int index) {
    return null;
  }

  @Override
  public void setInventorySlotContents(int index, ItemStack stack) {

  }

  @Override
  public boolean isUsableByPlayer(PlayerEntity player) {
    return false;
  }

  @Override
  public void clear() {

  }
}
