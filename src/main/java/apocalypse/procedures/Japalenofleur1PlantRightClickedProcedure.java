package apocalypse.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.Blocks;

import apocalypse.item.JapalenoItem;

import apocalypse.block.Japalenofleur2Block;

import apocalypse.ApocalypseModElements;

@ApocalypseModElements.ModElement.Tag
public class Japalenofleur1PlantRightClickedProcedure extends ApocalypseModElements.ModElement {
	public Japalenofleur1PlantRightClickedProcedure(ApocalypseModElements instance) {
		super(instance, 11);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Japalenofleur1PlantRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Japalenofleur1PlantRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Japalenofleur1PlantRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Japalenofleur1PlantRightClicked!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (!world.isRemote) {
			ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(JapalenoItem.block, (int) (1)));
			entityToSpawn.setPickupDelay(10);
			world.addEntity(entityToSpawn);
		}
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Japalenofleur2Block.block.getDefaultState(), 3);
	}
}
