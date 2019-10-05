package ca.fireball1725.hue.common.items;

import ca.fireball1725.hue.common.items.battery.ItemBattery;
import ca.fireball1725.hue.common.items.bulb.ItemBulb;
import ca.fireball1725.hue.common.items.bulb.ItemBulbAdvanced;
import ca.fireball1725.hue.common.items.bulb.ItemBulbLarge;
import ca.fireball1725.hue.common.items.bulb.ItemBulbSimple;
import ca.fireball1725.hue.common.items.card.ItemRF;
import ca.fireball1725.hue.common.items.card.ItemScene;
import ca.fireball1725.hue.common.items.led.*;
import ca.fireball1725.hue.common.items.tool.ToolWrench;
import ca.fireball1725.hue.util.MinecraftColours;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

import java.util.function.Supplier;

public enum Items {
  // Batteries
  BATTERY(ItemBattery::new),

  // Bulbs
  BULBSIMPLE(ItemBulbSimple::new),
  BULB(ItemBulb::new),
  BULBLARGE(ItemBulbLarge::new),
  BULBADVANCED(ItemBulbAdvanced::new),

  // Cards
  CARDRF(ItemRF::new),
  CARDSCENE(ItemScene::new),

  // LEDs
  LEDWHITE(() -> {return new ItemLED(MinecraftColours.WHITE);}),
  LEDORANGE(() -> {return new ItemLED(MinecraftColours.ORANGE);}),
  LEDMAGENTA(() -> {return new ItemLED(MinecraftColours.MAGENTA);}),
  LEDLIGHTBLUE(() -> {return new ItemLED(MinecraftColours.LIGHTBLUE);}),
  LEDYELLOW(() -> {return new ItemLED(MinecraftColours.YELLOW);}),
  LEDLIME(() -> {return new ItemLED(MinecraftColours.LIME);}),
  LEDPINK(() -> {return new ItemLED(MinecraftColours.PINK);}),
  LEDGREY(() -> {return new ItemLED(MinecraftColours.GREY);}),
  LEDLIGHTGREY(() -> {return new ItemLED(MinecraftColours.LIGHTGREY);}),
  LEDCYAN(() -> {return new ItemLED(MinecraftColours.CYAN);}),
  LEDPURPLE(() -> {return new ItemLED(MinecraftColours.PURPLE);}),
  LEDBLUE(() -> {return new ItemLED(MinecraftColours.BLUE);}),
  LEDBROWN(() -> {return new ItemLED(MinecraftColours.BROWN);}),
  LEDGREEN(() -> {return new ItemLED(MinecraftColours.GREEN);}),
  LEDRED(() -> {return new ItemLED(MinecraftColours.RED);}),
  LEDBLACK(() -> {return new ItemLED(MinecraftColours.BLACK);}),

  // Special LEDs
  LEDANTISPAWN(ItemLEDAntiSpawn::new),
  LEDBRIGHT(ItemLEDBright::new),
  LEDGROW(ItemLEDGrow::new),
  LEDIR(ItemLEDIR::new),
  LEDRGB(ItemLEDRGB::new),
  LEDUV(ItemLEDUV::new),

  // Tool
  WRENCH(ToolWrench::new),
  ;

  private final Item item;

  Items(Supplier<Item> itemSupplier) {
    item = itemSupplier.get();
  }

  public static void registerItems(RegistryEvent.Register<Item> event) {
    for (Items item : Items.values()) {
      item.registerItem(event);
    }
  }

  public void registerItem(RegistryEvent.Register<Item> event) {
    event.getRegistry().register(item);
  }
}
