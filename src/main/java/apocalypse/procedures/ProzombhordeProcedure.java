package apocalypse.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import apocalypse.ApocalypseModElements;

@ApocalypseModElements.ModElement.Tag
public class ProzombhordeProcedure extends ApocalypseModElements.ModElement {
	public ProzombhordeProcedure(ApocalypseModElements instance) {
		super(instance, 7);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Prozombhorde!");
			return;
		}
		World world = (World) dependencies.get("world");
		if (!world.isRemote && world.getServer() != null) {
			world.getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vec3d(1, 1, 1), Vec2f.ZERO,
					(ServerWorld) world, 4, "", new StringTextComponent(""), world.getServer(), null).withFeedbackDisabled(),
					"summon Zombie ~15 ~ ~");
		}
	}

	@SubscribeEvent
	public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
		PlayerEntity entity = event.getPlayer();
		int i = event.getPos().getX();
		int j = event.getPos().getY();
		int k = event.getPos().getZ();
		World world = event.getWorld();
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
