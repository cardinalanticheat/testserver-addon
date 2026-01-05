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

import java.util.Optional;

public class PlayerJoinHandler implements EventListener {

    @EventManager.Target
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // We can access the player controller and be sure that it has been initialized
        PlayerController.of(player).setShowingFlags(true);

        // Teleport player to world spawn
        Bukkit.getScheduler().runTaskLater(Core.INSTANCE.plugin, () -> {
            player.teleport(
                    player.getWorld().getSpawnLocation().add(.5, 0, .5)
            );

            spawnFireworks(player.getLocation(), 5, 10);
        }, 30);

        // Send notice if present (Keep it simple)
        Optional.ofNullable(System.getenv("TESTSERVER_JOIN_NOTICE"))
                .ifPresent(player::sendMessage);
    }

    /**
     * Spawn fireforks around a circle location.
     * This method is subject to change as it will need to be adjusted with changes to the mincraft api.
     *
     * @param location center location
     * @param amount   amount of fireworks
     * @param diameter the diameter of the circle
     */
    private void spawnFireworks(Location location, int amount, int diameter) {
        for(int i = 0; i < amount; i++) {
            Location randomLocation = location.clone().add(new Vector(Math.random() - 0.5, 0, Math.random() - 0.5).multiply(diameter));

            Firework firework = (Firework) location.getWorld().spawnEntity(randomLocation, EntityType.valueOf("FIREWORK_ROCKET"));
            FireworkMeta meta = firework.getFireworkMeta();

            FireworkEffect effect = FireworkEffect.builder()
                    .withColor(Color.RED)
                    .with(FireworkEffect.Type.BURST)
                    .flicker(true)
                    .trail(true)
                    .build();

            // Apply effect and power
            meta.addEffect(effect);
            meta.setPower(4); // 1 = Short flight duration

            firework.setFireworkMeta(meta);
            firework.detonate();
        }
    }
}
