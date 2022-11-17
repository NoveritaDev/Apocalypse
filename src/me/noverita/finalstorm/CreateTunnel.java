package me.noverita.finalstorm;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Candle;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class CreateTunnel implements CommandExecutor {
    private Set<Location> markedPoints;

    public CreateTunnel() {
        markedPoints = new HashSet<>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player sender = (Player) commandSender;
            if (command.getName().equals("finalemarktunnel")) {
                markedPoints.add(sender.getLocation());
                return true;
            } else if (command.getName().equals("finaleopen")) {
                World w = sender.getWorld();
                for (Location loc: markedPoints) {
                    int x = loc.getBlockX();
                    int y = -30;
                    int z = loc.getBlockZ();

                    int xDir;
                    int yDelta;

                    xDir = (x < 0) ? 1 : -1;

                    w.spawnParticle(Particle.EXPLOSION_LARGE, loc, 1);
                    w.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);

                    for (int tempX = x; tempX != 0; tempX += xDir) {
                        w.getBlockAt(tempX, y + 1, z + 1).setType(Material.AIR);
                        w.getBlockAt(tempX, y + 1, z).setType(Material.LIGHT);
                        w.getBlockAt(tempX, y + 1, z - 1).setType(Material.AIR);
                        w.getBlockAt(tempX, y, z + 1).setType(Material.AIR);
                        w.getBlockAt(tempX, y, z).setType(Material.AIR);
                        w.getBlockAt(tempX, y, z - 1).setType(Material.AIR);
                        w.getBlockAt(tempX, y - 1, z + 1).setType(Material.AIR);
                        w.getBlockAt(tempX, y - 1, z).setType(Material.AIR);
                        w.getBlockAt(tempX, y - 1, z - 1).setType(Material.AIR);
                        w.getBlockAt(tempX, y - 2, z + 1).setType(Material.END_STONE_BRICKS);
                        w.getBlockAt(tempX, y - 2, z).setType(Material.END_STONE_BRICKS);
                        w.getBlockAt(tempX, y - 2, z - 1).setType(Material.END_STONE_BRICKS);
                        w.getBlockAt(tempX, y + 2, z + 1).setType(Material.END_STONE_BRICKS);
                        w.getBlockAt(tempX, y + 2, z).setType(Material.END_STONE_BRICKS);
                        w.getBlockAt(tempX, y + 2, z - 1).setType(Material.END_STONE_BRICKS);

                        if (tempX % 4 == 0) {
                            w.getBlockAt(tempX, y + 1, z + 2).setType(Material.PURPUR_PILLAR);
                            w.getBlockAt(tempX, y, z + 2).setType(Material.PURPUR_PILLAR);
                            w.getBlockAt(tempX, y - 1, z + 2).setType(Material.PURPUR_PILLAR);
                            w.getBlockAt(tempX, y + 1, z - 2).setType(Material.PURPUR_PILLAR);
                            w.getBlockAt(tempX, y, z - 2).setType(Material.PURPUR_PILLAR);
                            w.getBlockAt(tempX, y - 1, z - 2).setType(Material.PURPUR_PILLAR);
                        } else {
                            w.getBlockAt(tempX, y + 1, z + 3).setType(Material.END_STONE);
                            w.getBlockAt(tempX, y - 1, z + 3).setType(Material.END_STONE);
                            w.getBlockAt(tempX, y, z + 3).setType(Material.PURPUR_BLOCK);
                            w.getBlockAt(tempX, y + 2, z + 2).setType(Material.END_STONE_BRICKS);
                            w.getBlockAt(tempX, y - 2, z + 2).setType(Material.END_STONE_BRICKS);
                            w.getBlockAt(tempX, y + 1, z + 2).setType(Material.AIR);
                            w.getBlockAt(tempX, y - 1, z + 2).setType(Material.AIR);
                            w.getBlockAt(tempX, y, z + 2).setType(Material.AIR);

                            w.getBlockAt(tempX, y + 1, z - 3).setType(Material.END_STONE);
                            w.getBlockAt(tempX, y - 1, z - 3).setType(Material.END_STONE);
                            w.getBlockAt(tempX, y, z - 3).setType(Material.PURPUR_BLOCK);
                            w.getBlockAt(tempX, y + 2, z - 2).setType(Material.END_STONE_BRICKS);
                            w.getBlockAt(tempX, y - 2, z - 2).setType(Material.END_STONE_BRICKS);
                            w.getBlockAt(tempX, y + 1, z - 2).setType(Material.AIR);
                            w.getBlockAt(tempX, y - 1, z - 2).setType(Material.AIR);
                            w.getBlockAt(tempX, y, z - 2).setType(Material.AIR);
                        }
                    }

                    for (int tempY = loc.getBlockY(); tempY != y; --tempY) {
                        w.getBlockAt(x + 1,tempY,z + 1).setType(Material.AIR);
                        w.getBlockAt(x + 1,tempY,z).setType(Material.AIR);
                        w.getBlockAt(x + 1,tempY,z - 1).setType(Material.AIR);
                        w.getBlockAt(x,tempY,z + 1).setType(Material.AIR);
                        w.getBlockAt(x,tempY,z).setType(Material.AIR);
                        w.getBlockAt(x,tempY,z - 1).setType(Material.AIR);
                        w.getBlockAt(x - 1,tempY,z + 1).setType(Material.AIR);
                        w.getBlockAt(x - 1,tempY,z).setType(Material.AIR);
                        w.getBlockAt(x - 1,tempY,z - 1).setType(Material.AIR);
                    }
                }
                markedPoints.clear();
                return true;
            }
        }
        return false;
    }
}
