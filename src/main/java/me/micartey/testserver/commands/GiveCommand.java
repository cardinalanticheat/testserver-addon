package me.micartey.testserver.commands;

import me.clientastisch.cardinal.extension.impl.command.Command;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GiveCommand implements Command {

    private static final List<String> BLOCKED_ITEMS = Arrays.asList(
            "TNT", "BUCKET", "EXPLOSIVE", "SAND", "FLINT", "ARMOR_STAND", "MINECART",
            "COMMAND"
    );

    @Override
    public boolean execute(CommandSender sender, String command, String[] args, String raw) {
        // Ignore all commands that are not give
        if (!command.equalsIgnoreCase("give"))
            return false;

        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lServer§7: This command is only for players");
            return true;
        }

        if (args.length != 3) {
            sender.sendMessage("§c§lServer§7: /give <material> <amount>");
            return true;
        }

        Optional.ofNullable(Material.getMaterial(args[1].toUpperCase())).ifPresent(material -> {
            if (BLOCKED_ITEMS.stream().anyMatch(blocked -> material.name().contains(blocked))) {
                sender.sendMessage("§c§lServer§7: This item is blocked!");
                return;
            }

            Player player = (Player) sender;

            try {
                player.getInventory().addItem(new ItemStack(material, Integer.parseInt(args[2])));
                player.sendMessage("§c§lServer§7: Gave you " + args[2] + " of " + args[1]);
            } catch (NumberFormatException exception) {
                sender.sendMessage("§c§lServer§7: " + args[2] + " is not a valid number");
            }
        });

        return true;
    }
}