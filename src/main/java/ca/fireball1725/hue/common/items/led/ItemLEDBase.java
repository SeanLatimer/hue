package ca.fireball1725.hue.common.items.led;

import ca.fireball1725.hue.common.items.ItemBase;

public abstract class ItemLEDBase extends ItemBase {
  private final int lightColour;

  public ItemLEDBase(Properties properties, int lightColour) {
    super(properties);
    this.lightColour = lightColour;
  }
}
