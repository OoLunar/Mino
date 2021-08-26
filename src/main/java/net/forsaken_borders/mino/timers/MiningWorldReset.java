package net.forsaken_borders.mino.timers;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;

import org.bukkit.Server;
import org.bukkit.World.Environment;
import org.bukkit.WorldType;

import net.forsaken_borders.mino.App;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

public class MiningWorldReset extends TimerTask {
    private Server server;

    public MiningWorldReset(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        MVWorldManager worldManager = App.MultiverseCore.getMVWorldManager();
        MultiverseWorld miningWorld = worldManager.getMVWorld("mining");
        server.broadcast(Component.text(App.Prefix + ChatColor.YELLOW + "Warning: The mining world will reset in 5 minutes!"));
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch(final InterruptedException exception)
        {
            Thread.currentThread().interrupt();
        }

        server.broadcast(Component.text(App.Prefix + ChatColor.RED + "The mining world is resetting!"));
        if(miningWorld == null) {
            worldManager.addWorld("mining", Environment.NORMAL, null, WorldType.AMPLIFIED, true, null);
        } else {
            miningWorld.getCBWorld().getPlayers().forEach(player -> {
                player.sendMessage(Component.text(App.Prefix + "The mining world is resetting! For your safety, you've been teleported to your home."));
                player.performCommand("/home");
            });
            worldManager.regenWorld("mining", true, true, null);
        }
    }
}
