package me.noverita.finalstorm;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        TriggerStorm storm = new TriggerStorm(this, "finalestorm");
        CreateTunnel tunnel = new CreateTunnel();

        getCommand("finalemarktunnel").setExecutor(tunnel);
        getCommand("finaleopen").setExecutor(tunnel);
    }
}
