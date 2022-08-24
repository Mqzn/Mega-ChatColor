package dev.mqzen.chatcolor.menus;

import dev.mqzen.chatcolor.gui_menus.ChatColorMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public final class MenuListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		if (!(e.getWhoClicked() instanceof Player)) return;
		Player player = (Player) e.getWhoClicked();

		MenuEntity opened = MenuEntity.getOpenEntity(player);
		if (opened != null) opened.parseOnClick(e);
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		MenuEntity entity = MenuEntity.getOpenEntity(player);
		if(entity instanceof ChatColorMenu) {
			ChatColorMenu menu = (ChatColorMenu) entity;
			menu.onClose();
		}
		MenuEntity.unregisterEntity(player);
	}

}
