package apocalypse.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import apocalypse.ApocalypseModElements;

@ApocalypseModElements.ModElement.Tag
public class ProdeuxProcedure extends ApocalypseModElements.ModElement {
	public ProdeuxProcedure(ApocalypseModElements instance) {
		super(instance, 6);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure Prodeux!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure Prodeux!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure Prodeux!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Prodeux!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (!world.isRemote) {
			Entity entityToSpawn = new ZombieEntity(EntityType.ZOMBIE, world);
			entityToSpawn.setLocationAndAngles((x - 5), (y - 9), z, world.rand.nextFloat() * 360F, 0);
			world.addEntity(entityToSpawn);
		}
		if (!world.isRemote) {
			Entity entityToSpawn = new ZombieEntity(EntityType.ZOMBIE, world);
			entityToSpawn.setLocationAndAngles((x + 5), (y - 9), z, world.rand.nextFloat() * 360F, 0);
			world.addEntity(entityToSpawn);
		}
		if ((((world.isDaytime()) == (false)) || ((world.isThundering()) == (true)))) {
			if (!world.isRemote) {
				Entity entityToSpawn = new ZombieEntity(EntityType.ZOMBIE, world);
				entityToSpawn.setLocationAndAngles((x + 6), (y - 9), z, world.rand.nextFloat() * 360F, 0);
				world.addEntity(entityToSpawn);
			}
			if (!world.isRemote) {
				Entity entityToSpawn = new ZombieEntity(EntityType.ZOMBIE, world);
				entityToSpawn.setLocationAndAngles((x - 6), (y - 9), z, world.rand.nextFloat() * 360F, 0);
				world.addEntity(entityToSpawn);
			}
			world.playSound((PlayerEntity) null, x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.infect")),
					SoundCategory.NEUTRAL, (float) 5, (float) 1);
			if (((new Object() {
				public boolean getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "Already_Boosted")) == (false))) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("Already_Boosted", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote) {
					Entity entityToSpawn = new ZombieEntity(EntityType.ZOMBIE, world);
					entityToSpawn.setLocationAndAngles((x + 7), (y - 9), z, world.rand.nextFloat() * 360F, 0);
					world.addEntity(entityToSpawn);
				}
				if (!world.isRemote) {
					Entity entityToSpawn = new ZombieEntity(EntityType.ZOMBIE, world);
					entityToSpawn.setLocationAndAngles((x - 7), (y - 9), z, world.rand.nextFloat() * 360F, 0);
					world.addEntity(entityToSpawn);
				}
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent("Attention aux zombies !"));
				}
			}
		} else {
			if (!world.isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putBoolean("Already_Boosted", (false));
				world.notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		Entity entity = event.getPlayer();
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("x", (int) entity.getPosX());
		dependencies.put("y", (int) entity.getPosY());
		dependencies.put("z", (int) entity.getPosZ());
		dependencies.put("world", entity.world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
