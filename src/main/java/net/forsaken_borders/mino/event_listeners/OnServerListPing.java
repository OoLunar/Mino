package net.forsaken_borders.mino.event_listeners;

import java.util.ArrayList;
import java.util.List;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import com.destroystokyo.paper.profile.PlayerProfile;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.ChatColor;

public class OnServerListPing implements Listener {
    @EventHandler
    public void onServerListPingEvent(PaperServerListPingEvent event) {
        List<PlayerProfile> playerProfiles = new ArrayList<PlayerProfile>();
        for (Player onlinePlayer : App.Essentials.getServer().getOnlinePlayers()) {
            playerProfiles.add(Bukkit.createProfile(onlinePlayer.displayName().toString()));
        }

        // TODO: Send player names, or "Nobody is online!" if nobody is online.
        playerProfiles.add(Bukkit.createProfile(ChatColor.of("#7b84d1") + "Mino"));

        List<PlayerProfile> playerSamples = event.getPlayerSample();
        playerSamples.clear();
        playerSamples.addAll(playerProfiles);
    }
}
