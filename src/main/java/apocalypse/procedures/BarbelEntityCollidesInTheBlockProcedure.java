package apocalypse.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import apocalypse.ApocalypseModElements;

@ApocalypseModElements.ModElement.Tag
public class BarbelEntityCollidesInTheBlockProcedure extends ApocalypseModElements.ModElement {
	public BarbelEntityCollidesInTheBlockProcedure(ApocalypseModElements instance) {
		super(instance, 25);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BarbelEntityCollidesInTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 20, (int) 2));
		entity.attackEntityFrom(DamageSource.GENERIC, (float) 1);
	}
}
