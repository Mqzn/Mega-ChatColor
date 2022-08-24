package dev.mqzen.chatcolor;

import dev.mqzen.chatcolor.gui_menus.ChatColorMenu;
import dev.mqzen.chatcolor.utils.Translator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class ChatColorCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender commandSender,
	                         Command command,
	                         String label,
	                         String[] args) {


		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage(Translator.color("&cYou are not a player!"));
			return true;
		}

		if (args.length > 0) {
			commandSender.sendMessage(Translator.color("&cUsage: /chatcolor &eOR &c/cc"));
			return true;
		}

		Player sender = (Player) commandSender;

		if (sender.hasPermission("chatcolor.gui"))
			new ChatColorMenu().open(sender);
		else
			sender.sendMessage(Translator.color("&cYou do not have permission to use this command!"));


		return true;
	}
}
