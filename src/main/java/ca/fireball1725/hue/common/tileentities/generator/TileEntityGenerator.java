package ca.fireball1725.hue.common.tileentities.generator;

import ca.fireball1725.hue.common.blocks.Blocks;
import ca.fireball1725.hue.common.tileentities.TileEntityBase;
import ca.fireball1725.hue.util.CustomEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class TileEntityGenerator extends TileEntityBase implements ITickableTileEntity, INamedContainerProvider {
  private int i = 0;

  private LazyOptional<ItemStackHandler> itemHandler = LazyOptional.of(this::createHandler);
  private LazyOptional<CustomEnergyStorage> energyHandler = LazyOptional.of(this::createEnergy);

  private int ticksToBurn = 0;

  public TileEntityGenerator() {
    super(Blocks.BLOCKGENERATOR.getBlock().getTileEntityType());
  }

  @Override
  public void tick() {
    if (!world.isRemote) {
      itemHandler.ifPresent(iHandler -> {
        energyHandler.ifPresent(eHandler -> {
          markDirtyQuick();
          distributePower(eHandler);

          if (ticksToBurn > 0) {
            ticksToBurn--;
            eHandler.addEnergy(40);
          } else {
            if (FurnaceTileEntity.isFuel(iHandler.getStackInSlot(0))) {
              ItemStack extracted = iHandler.extractItem(0, 1, false);
              ticksToBurn = ForgeHooks.getBurnTime(extracted);
            }
          }
        });
      });

      BlockState blockState = world.getBlockState(pos);
      if (blockState.get(BlockStateProperties.POWERED) != ticksToBurn > 0) {
        world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, ticksToBurn > 0), 3);
      }
    }
  }

  private void distributePower(CustomEnergyStorage storage) {
    AtomicInteger cap = new AtomicInteger(storage.getEnergyStored());

    for (Direction dir : Direction.values()) {
      BlockPos p = pos.offset(dir);
      TileEntity te = (world != null) ? world.getTileEntity(pos.offset(dir)) : null;
      if (te != null) {
        boolean cont = te.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite()).map(h -> {
          if (h.canReceive()) {
            int recv = h.receiveEnergy(Math.min(cap.get(), 160), false); // TODO: Make send rate per tick configurable
            cap.addAndGet(-recv);
            storage.consumeEnergy(recv);
            markDirty();
            return cap.get() > 0;
          } else {
            return true;
          }
        }).orElse(true);
        if (!cont) {
          return;
        }
      }

    }
  }

  @Override
  public CompoundNBT write(CompoundNBT tag) {
    itemHandler.ifPresent(h -> {
      CompoundNBT t = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
      tag.put("inv", t);
    });

    energyHandler.ifPresent(h -> {
      CompoundNBT t = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
      tag.put("energy", t);
    });

    tag.putInt("ticksToBurn", ticksToBurn);
    return super.write(tag);
  }

  @Override
  public void read(CompoundNBT tag) {

    CompoundNBT invTag = tag.getCompound("inv");
    itemHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));

    CompoundNBT energyTag = tag.getCompound("energy");
    energyHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));

    ticksToBurn = tag.getInt("ticksToBurn");
    super.read(tag);
  }

  private ItemStackHandler createHandler() {
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
        if (!FurnaceTileEntity.isFuel(stack)) {
          return stack;
        }
        return super.insertItem(slot, stack, simulate);
      }
    };
  }

  private CustomEnergyStorage createEnergy() {
    return new CustomEnergyStorage(100000, 40);
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
  public void remove() {
    super.remove();
    itemHandler.invalidate();
    energyHandler.invalidate();
  }

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      return itemHandler.cast();
    }
    if (cap == CapabilityEnergy.ENERGY) {
      return energyHandler.cast();
    }

    return super.getCapability(cap, side);
  }
}
