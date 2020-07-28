
package apocalypse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.BlockState;

import apocalypse.procedures.KitsoinRightClickedInAirProcedure;

import apocalypse.itemgroup.ApocItemGroup;

import apocalypse.ApocalypseModElements;

@ApocalypseModElements.ModElement.Tag
public class KitsoinItem extends ApocalypseModElements.ModElement {
	@ObjectHolder("apocalypse:kitsoin")
	public static final Item block = null;
	public KitsoinItem(ApocalypseModElements instance) {
		super(instance, 12);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ApocItemGroup.tab).maxStackSize(1));
			setRegistryName("kitsoin");
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

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
			ItemStack itemstack = ar.getResult();
			int x = (int) entity.getPosX();
			int y = (int) entity.getPosY();
			int z = (int) entity.getPosZ();
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				KitsoinRightClickedInAirProcedure.executeProcedure($_dependencies);
			}
			return ar;
		}
	}
}
