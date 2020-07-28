package apocalypse.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import apocalypse.item.KitsoinItem;

import apocalypse.ApocalypseModElements;

@ApocalypseModElements.ModElement.Tag
public class KitsoinRightClickedInAirProcedure extends ApocalypseModElements.ModElement {
	public KitsoinRightClickedInAirProcedure(ApocalypseModElements instance) {
		super(instance, 12);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KitsoinRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).setHealth((float) 20);
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(KitsoinItem.block, (int) (1)).getItem() == p.getItem(), (int) 1);
	}
}
