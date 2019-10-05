package ca.fireball1725.hue.common.tileentities.generator;

import ca.fireball1725.hue.Hue;
import ca.fireball1725.hue.common.blocks.Blocks;
import ca.fireball1725.hue.common.tileentities.TileEntityBase;
import ca.fireball1725.hue.common.tileentities.TileEntityInventoryBase;
import ca.fireball1725.hue.util.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class TileEntityGenerator extends TileEntityInventoryBase implements ITickableTileEntity, INamedContainerProvider {
  private int i = 0;

  private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
  private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);

  public TileEntityGenerator() {
    super(Blocks.BLOCKGENERATOR.getBlock().getTileEntityType());
  }

  @Override
  public void tick() {
    if (i >= 20) {
      i = 0;
      Hue.LOGGER.info(">>> Tick...");
    }

    i++;
  }

  private IItemHandler createHandler() {
    return new ItemStackHandler(1) {
      @Override
      protected void onContentsChanged(int slot) {
        markDirty();
      }

      @Override
      public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return FurnaceTileEntity.isFuel(stack);
      }

      @Nonnull
      @Override
      public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (FurnaceTileEntity.isFuel(stack)) {
          return stack;
        }
        return super.insertItem(slot, stack, simulate);
      }
    };
  }

  private IEnergyStorage createEnergy() {
    return new CustomEnergyStorage(100000, 0);
  }

  @Override
  public ITextComponent getDisplayName() {
    return new StringTextComponent(Objects.requireNonNull(getType().getRegistryName()).getPath());
  }

  @Nullable
  @Override
  public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
    return null;
  }


  @Override
  public int[] getSlotsForFace(Direction side) {
    return new int[0];
  }

  @Override
  public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
    return true;
  }

  @Override
  public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
    return true;
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
