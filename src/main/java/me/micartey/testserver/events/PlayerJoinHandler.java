package me.micartey.testserver.events;

import me.clientastisch.cardinal.controller.PlayerController;
import me.clientastisch.cardinal.core.Core;
import me.clientastisch.cardinal.events.EventManager;
import me.clientastisch.cardinal.events.event.impl.player.others.PlayerJoinEvent;
import me.clientastisch.cardinal.extension.impl.event.EventListener;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;
import xyz.jonesdev.neutron.NeutronBukkitPlugin;
import xyz.jonesdev.neutron.api.user.NeutronUserRegistry;

import java.util.Optional;

public class PlayerJoinHandler implements EventListener {

    @EventManager.Target
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // We can access the player controller and be sure that it has been initialized
        NeutronUserRegistry.fromBukkitPlayer(player).setDevelopmentMode(true);
        NeutronUserRegistry.fromBukkitPlayer(player).setExempted(true);

        PlayerController.of(player).setShowingFlags(true);

        // Teleport player to world spawn
        Bukkit.getScheduler().runTask(Core.INSTANCE.plugin, () -> {
            player.teleport(
                    player.getWorld().getSpawnLocation().add(.5, 0, .5)
            );

            spawnFireworks(player.getLocation(), 5, 10);
        });

        // Send notice if present (Keep it simple)
        Optional.ofNullable(System.getenv("TESTSERVER_JOIN_NOTICE"))
                .ifPresent(player::sendMessage);
    }

    private void spawnFireworks(Location location, int amount, int diameter) {
        World world = location.getWorld();

        for (int i = 0; i < amount; i++) {
            Location randomLocation = location.clone().add(new Vector(Math.random() - 0.5, 0, Math.random() - 0.5).multiply(diameter));
            Firework firework = (Firework) world.spawnEntity(randomLocation, EntityType.FIREWORK);
            FireworkMeta fireworkMeta = firework.getFireworkMeta();

            fireworkMeta.setPower(i);
            fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());

            firework.setFireworkMeta(fireworkMeta);
            firework.detonate();

            firework.setFireworkMeta(fireworkMeta);
        }
    }
}
