package nmt.minecraft.Regicide.Game.Player;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class RPlayer {
	
	private Player player;
	
	private LivingEntity villager;
	
	public RPlayer(UUID Player) {
		
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	private void spawnVillager(){
		World world = player.getWorld();
		Location location = player.getLocation();
		villager = (LivingEntity) world.spawnEntity(location,EntityType.VILLAGER);
		//TODO: Find some way to make the villager invisible to the player
	}
	
	public void syncVillager(){
		villager.teleport(player.getLocation());
	}
	
}

