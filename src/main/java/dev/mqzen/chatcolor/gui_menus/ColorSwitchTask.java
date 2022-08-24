package dev.mqzen.chatcolor.gui_menus;

import dev.mqzen.chatcolor.MegaChatColor;
import dev.mqzen.chatcolor.text.ChatColorManager;
import dev.mqzen.chatcolor.text.MessageColorApplier;
import dev.mqzen.chatcolor.utils.ItemBuilder;
import dev.mqzen.chatcolor.utils.Translator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public final class ColorSwitchTask extends BukkitRunnable {

	private final ChatColorMenu menu;
	private final Player player;

	private int switchIndex = 0;

	public ColorSwitchTask(ChatColorMenu menu, Player player) {
		this.menu = menu;
		this.player = player;
	}

	@Override
	public void run() {

		if(switchIndex >= MegaChatColor.COLORS.length) {
			switchIndex = 0;
		}

		if(player == null || !player.isOnline()) {
			cancel();
			return;
		}
		ItemStack item = MessageColorApplier.toItem(player.getUniqueId(), MegaChatColor.COLORS[switchIndex], true);

		menu.setItem(menu.getRainbowSlot(), new ItemBuilder(item)
						.setDisplay(ChatColorManager.getInstance().getRainbowPrefix()).build(), (e)-> {


			if (!player.hasPermission("chatcolor.color.rainbow") && !player.hasPermission("chatcolor.color.*")) {
				player.sendMessage(Translator.color("&cYou do not have permission to use this color."));
				return;
			}

			ChatColorManager.getInstance().update(player.getUniqueId(), (h) -> h.setRainbow(true));
			e.getWhoClicked().closeInventory();
			e.getWhoClicked().sendMessage(Translator.color("&7You have selected " + ChatColorManager.getInstance().getRainbowPrefix() + "&7 as your ChatColor."));

		});

		switchIndex++;
	}


}
