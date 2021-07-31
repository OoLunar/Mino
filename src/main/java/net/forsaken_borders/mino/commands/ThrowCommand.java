package net.forsaken_borders.mino.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.ChatColor;

@CommandAlias("throw")
@Description("Throws a player high into the sky.")
public class ThrowCommand extends BaseCommand {
    @Default
    @CommandCompletion("@players")
    @CommandPermission("mino.throw")
    public void onDefault(CommandSender sender, Player player) {
        Location location = player.getLocation();
        // Set the player's pitch to up, so that they get launched up into the sky, then restore it to its original value
        float playerPitch = location.getPitch();
        location.setPitch(-90);
        player.setVelocity(location.getDirection().multiply(new Vector(0, 7, 0)));
        location.setPitch(playerPitch);
        player.getWorld().strikeLightningEffect(location);
    }

    @HelpCommand
    @CatchUnknown
    public void onHelp(CommandSender sender) {
        sender.sendMessage(App.Prefix + "Usage: " + ChatColor.GRAY + "/throw <player>");
    }
}
