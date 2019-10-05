package ca.fireball1725.hue.common.blocks;

import ca.fireball1725.hue.common.blocks.controller.BlockAdvancedLightController;
import ca.fireball1725.hue.common.blocks.controller.BlockLightController;
import ca.fireball1725.hue.common.blocks.controller.BlockSimpleLightController;
import ca.fireball1725.hue.common.blocks.distributor.BlockAdvancedPowerDistributor;
import ca.fireball1725.hue.common.blocks.distributor.BlockPowerDistributor;
import ca.fireball1725.hue.common.blocks.distributor.BlockSimplePowerDistributor;
import ca.fireball1725.hue.common.blocks.fixture.BlockLightFixture;
import ca.fireball1725.hue.common.blocks.generator.BlockGenerator;
import ca.fireball1725.hue.common.blocks.remotecontrol.BlockRemoteControl;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;

import java.util.Objects;
import java.util.function.Supplier;

public enum Blocks {
  // Controller
  BLOCKSIMPLELIGHTCONTROLLER(BlockSimpleLightController::new),
  BLOCKLIGHTCONTROLLER(BlockLightController::new),
  BLOCKADVANCEDLIGHTCONTROLLER(BlockAdvancedLightController::new),

  // Distributor
  BLOCKSIMPLEPOWERDISTRIBUTOR(BlockSimplePowerDistributor::new),
  BLOCKPOWERDISTRIBUTOR(BlockPowerDistributor::new),
  BLOCKADVANCEDDISTRIBUTOR(BlockAdvancedPowerDistributor::new),

  // Fixture
  BLOCKLIGHTFIXTURE(BlockLightFixture::new),

  // Generator
  BLOCKGENERATOR(BlockGenerator::new),

  // Remote Control
  BLOCKREMOTECONTROL(BlockRemoteControl::new),
  ;

  private final BlockBase block;
  private final BlockItem blockItem;

  Blocks(Supplier<BlockBase> blockSupplier, Supplier<BlockItem> blockItemSupplier) {
    this.block = blockSupplier.get();
    if (blockItemSupplier != null) {
      blockItem = blockItemSupplier.get();
    } else {
      blockItem = new BlockItem(block, block.getItemProperties());
    }
  }

  Blocks(Supplier<BlockBase> blockSupplier) {
    this(blockSupplier, null);
  }

  public static void registerBlocks(RegistryEvent.Register<Block> event) {
    for (Blocks block : Blocks.values()) {
      block.registerBlock(event);
    }
  }

  public static void registerBlockItems(RegistryEvent.Register<Item> event) {
    for (Blocks block : Blocks.values()) {
      block.registerBlockItem(event);
    }
  }

  public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
    for (Blocks block : Blocks.values()) {
      block.registerTileEntity(event);
    }
  }

  public void registerBlock(RegistryEvent.Register<Block> event) {
    event.getRegistry().register(block);
  }

  public void registerBlockItem(RegistryEvent.Register<Item> event) {
    if (block == null) {
      // todo: log error
      return;
    }

    blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    event.getRegistry().register(blockItem);
  }

  public void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event) {
    if (block != null && block.hasTileEntity(null)) {
      event.getRegistry().register(block.getTileEntityType());
    }
  }

  public BlockBase getBlock() {
    return this.block;
  }

  public BlockItem getBlockItem() {
    return this.blockItem;
  }
}
