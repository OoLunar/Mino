package net.forsaken_borders.mino.event_listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.kyori.adventure.util.Ticks;

public class OnEat implements Listener {
    private static Random random = new Random();

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        if(event.getItem().getType() == Material.ROTTEN_FLESH) {
            Player player = event.getPlayer();
            if(player.hasPermission("mino.skills.monster_eyes")) {
                event.setCancelled(true);
                player.setFoodLevel(player.getFoodLevel() + 4);
                player.setSaturation(player.getSaturation() + 0.8F);
                // nextInt is exclusive. This means the random number is between 0 and 100
                if(random.nextInt(101) >= 40) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Ticks.TICKS_PER_SECOND * 30, 1, false, false, false));
                }
            }
        }
    }
}
