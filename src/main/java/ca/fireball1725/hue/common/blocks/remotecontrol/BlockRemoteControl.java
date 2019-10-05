package ca.fireball1725.hue.common.blocks.remotecontrol;

import ca.fireball1725.hue.common.blocks.BlockBase;
import ca.fireball1725.hue.common.items.ItemGroup;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockRemoteControl extends BlockBase {
  public BlockRemoteControl() {
    super(Properties.create(Material.IRON)
      .sound(SoundType.METAL)
      .hardnessAndResistance(2.0f)
    );
    setRegistryName("remotecontrol");
  }

  @Override
  public Item.Properties getItemProperties() {
    return new Item.Properties()
      .group(ItemGroup.HUE);
  }
}
