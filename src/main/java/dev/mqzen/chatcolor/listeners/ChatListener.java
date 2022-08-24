package dev.mqzen.chatcolor.listeners;

import dev.mqzen.chatcolor.text.MessageColorApplier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {

		Player player = e.getPlayer();
		String msg = e.getMessage();

		e.setMessage(MessageColorApplier.result(player.getUniqueId(), msg));
	}

}
