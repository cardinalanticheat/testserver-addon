package me.micartey.testserver.commands;

import me.clientastisch.cardinal.controller.PlayerController;
import me.clientastisch.cardinal.extension.impl.command.Command;
import me.micartey.testserver.inventories.AnticheatSelectInventory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.jonesdev.neutron.api.user.NeutronUserRegistry;

public class AnticheatCommand implements Command {

    @Override
    public boolean execute(CommandSender sender, String command, String[] args, String raw) {
        // Ignore all commands that are not give
        if (!command.equalsIgnoreCase("ac"))
            return false;

        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lServer§7: This command is only for players");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            player.openInventory(AnticheatSelectInventory.INVENTORY);
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage("§c§lServer§7: /ac <anticheat>");
            return true;
        }

        switch (args[1].toUpperCase()) {
            case "CARDINAL": // SHIELD
                PlayerController.of(player).setWhitelisted(false);
                NeutronUserRegistry.fromBukkitPlayer(player).setExempted(true);
                sender.sendMessage("§c§lServer§7: Selected §cCAC");
                break;

            case "NEUTRON": // END CRISTAL NETHER STAR
                PlayerController.of(player).setWhitelisted(true);
                NeutronUserRegistry.fromBukkitPlayer(player).setExempted(false);
                sender.sendMessage("§c§lServer§7: Selected §aNeutron");
        }

        return true;
    }
}