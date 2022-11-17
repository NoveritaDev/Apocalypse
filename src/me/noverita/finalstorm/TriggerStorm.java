package me.noverita.finalstorm;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class TriggerStorm implements CommandExecutor, TabCompleter, Listener {
    private final Map<String, Integer> runningRoutines;
    private final JavaPlugin plugin;
    private final Random rng;

    public TriggerStorm(JavaPlugin javaPlugin, String commandName) {
        plugin = javaPlugin;
        runningRoutines = new HashMap<>();
        rng = new Random();

        javaPlugin.getCommand(commandName).setTabCompleter(this);
        javaPlugin.getCommand(commandName).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        World world = ((Player) commandSender).getWorld();

        world.setWeatherDuration(Integer.MAX_VALUE);
        world.setStorm(true);

        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            while (true) {
                int x = rng.nextInt(400) - 200;
                int z = rng.nextInt(400) - 200;
                int y = world.getHighestBlockYAt(x, z);
                Location loc = new Location(world, x, y, z);
                Collection<Entity> entities = world.getNearbyEntities(loc, 2,2,2);

                boolean flag = true;
                for (Entity e: entities) {
                    if (e.getType() == EntityType.PLAYER && e instanceof LivingEntity && ((LivingEntity) e).getHealth() < 10) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    world.spawnEntity(loc, EntityType.LIGHTNING);
                    if (rng.nextDouble() < 0.2) {
                        world.spawnEntity(loc, EntityType.WITHER_SKELETON);
                    } else if (rng.nextDouble() < 0.3) {
                        world.createExplosion(loc, 0.5f);
                    }
                    commandSender.sendMessage(loc.toString());
                    break;
                }
            }
        }, 0, 2);
        runningRoutines.put("lightning", id);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>();
    }

    @EventHandler
    private void lightningStrike() {

    }
}
