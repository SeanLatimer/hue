package ca.fireball1725.hue;

import ca.fireball1725.hue.common.blocks.Blocks;
import ca.fireball1725.hue.common.items.Items;
import ca.fireball1725.hue.proxy.ClientProxy;
import ca.fireball1725.hue.proxy.IProxy;
import ca.fireball1725.hue.proxy.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("hue")
public class Hue {
  public static final Logger LOGGER = LogManager.getLogger();
  public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
      LOGGER.info("Registering Blocks");
      Blocks.registerBlocks(event);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
      LOGGER.info("Registering Items");
      Blocks.registerBlockItems(event);
      Items.registerItems(event);
    }

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
      Blocks.registerTileEntities(event);
    }
  }
}
