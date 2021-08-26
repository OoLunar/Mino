package net.forsaken_borders.mino.commands;

import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;

import org.bukkit.World.Environment;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.ChatColor;

@CommandAlias("build")
@Description("Warps you to the build world!")
@CommandPermission("mino.commands.build")
public class BuildCommand extends BaseCommand {
    @Default
    public void onDefault() {
        CommandIssuer commandIssuer = getCurrentCommandIssuer();
        if(!commandIssuer.isPlayer()) {
            commandIssuer.sendMessage(App.Prefix + ChatColor.RED + "Error: Only players can use this command!");
            return;
        }

        // TODO: Make world name changable from config
        MVWorldManager mvWorldManager = App.MultiverseCore.getMVWorldManager();
        MultiverseWorld buildWorld = mvWorldManager.getMVWorld("build");
        if(buildWorld == null) {
            commandIssuer.sendMessage(App.Prefix + ChatColor.GRAY + "The build world was not found, attemtping to create a new flat world...");
            if(!mvWorldManager.addWorld("build", Environment.NORMAL, null, WorldType.FLAT, false, null)) {
                commandIssuer.sendMessage(App.Prefix + ChatColor.RED + "Error: Failed to get the build world, and failed to create a build world.");
                return;
            }

            mvWorldManager.getMVWorld("build");
        }

        Player player = (Player) commandIssuer;
        // TODO: Config option to teleport the player to their bed spawn instead of the world spawn.
        player.teleport(buildWorld.getSpawnLocation());
    }
}
