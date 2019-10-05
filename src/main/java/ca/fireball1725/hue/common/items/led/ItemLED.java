package ca.fireball1725.hue.common.items.led;

import ca.fireball1725.hue.common.items.ItemGroup;
import ca.fireball1725.hue.util.MinecraftColours;

public class ItemLED extends ItemLEDBase {
  public ItemLED(MinecraftColours colour) {
    super(new Properties()
      .group(ItemGroup.HUE),
      colour.getColour());
    setRegistryName("led" + colour.getColourName());
  }
}
