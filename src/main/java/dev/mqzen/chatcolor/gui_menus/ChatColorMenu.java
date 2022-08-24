package dev.mqzen.chatcolor.gui_menus;

import com.google.common.collect.Sets;
import dev.mqzen.chatcolor.MegaChatColor;
import dev.mqzen.chatcolor.menus.Menu;
import dev.mqzen.chatcolor.text.ChatColorManager;
import dev.mqzen.chatcolor.text.MessageColorApplier;
import dev.mqzen.chatcolor.utils.ItemBuilder;
import dev.mqzen.chatcolor.utils.Translator;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Properties;
import java.util.Set;

public final class ChatColorMenu extends Menu {

	private @Getter ColorSwitchTask task;

	public final static Set<Integer> BORDER_SLOTS =
					Sets.newHashSet(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
									18, 27, 36, 45,
									46, 47, 48, 49, 50, 51, 52, 53,
									17, 26, 35, 44);

	public final static ItemStack FILLER = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 3).setDisplay(" ").build();

	private @Getter int rainbowSlot;
	@Override
	protected int getRows() {
		return 6;
	}

	@Override
	protected void setItems(Player player) {

		for (int borderSlot : BORDER_SLOTS) {
			setItem(borderSlot, FILLER, (e) -> {
			});
		}

		int s = 0;
		for (ChatColor color : MegaChatColor.COLORS) {

			while (BORDER_SLOTS.contains(s)) s++;

			if (s >= 54) {
				break;
			}

			this.setItem(s, MessageColorApplier.toItem(player.getUniqueId(), color), (e) -> {


				if (!player.hasPermission("chatcolor.color." + color.name().toLowerCase()) && !player.hasPermission("chatcolor.color.*")) {
					player.sendMessage(Translator.color("&cYou do not have permission to use this color."));
					return;
				}

				ChatColorManager.getInstance().update(player.getUniqueId(), (h) -> h.setColor(color));
				e.getWhoClicked().closeInventory();
				e.getWhoClicked().sendMessage(Translator.color("&7You have selected " + color + ChatColor.BOLD + color.name() + "&7 as your ChatColor."));
			});

			s++;
		}


		rainbowSlot = s;
		this.startItemUpdateTask(player);
	}

	private void startItemUpdateTask(Player player) {
		task = new ColorSwitchTask(this, player);
		task.runTaskTimerAsynchronously(MegaChatColor.getInstance(), 2L, 5L);
	}

	public void onClose() {
		task.cancel();
	}


	@Override
	public String getUniqueName() {
		return "mega-chatcolor-gui";
	}

	@Override
	public String getTitle() {
		return MegaChatColor.getInstance().getConfig().getString("gui.title");
	}


}
