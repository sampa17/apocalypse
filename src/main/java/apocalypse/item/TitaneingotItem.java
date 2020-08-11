
package apocalypse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import apocalypse.itemgroup.ApocItemGroup;

import apocalypse.ApocalypseModElements;

@ApocalypseModElements.ModElement.Tag
public class TitaneingotItem extends ApocalypseModElements.ModElement {
	@ObjectHolder("apocalypse:titaneingot")
	public static final Item block = null;
	public TitaneingotItem(ApocalypseModElements instance) {
		super(instance, 27);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ApocItemGroup.tab).maxStackSize(64));
			setRegistryName("titaneingot");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
