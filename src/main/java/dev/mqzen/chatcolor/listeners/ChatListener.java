package dev.mqzen.chatcolor;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	private final MegaChatColor plugin;

	public ChatListener(MegaChatColor plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {

		String msg = e.getMessage();

		e.setMessage();

	}

}
