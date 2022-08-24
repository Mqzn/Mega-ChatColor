package dev.mqzen.chatcolor;

import dev.mqzen.chatcolor.listeners.ChatListener;
import dev.mqzen.chatcolor.menus.MenuListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Arrays;

public final class MegaChatColor extends JavaPlugin {

	private static @Getter MegaChatColor instance;
	public static ChatColor[] COLORS;
	@Override
	public void onEnable() {

		instance = this;

		registerListener(new MenuListener());
		registerListener(new ChatListener());

		this.getCommand("chatcolor").setExecutor(new ChatColorCommand());

		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);

		COLORS = Arrays.stream(ChatColor.values()).filter(ChatColor::isColor).filter((c)-> {
			 if(getConfig().getBoolean("allow-black")) {
				 return true;
			 }
			 return c != ChatColor.BLACK;
		}).toArray(ChatColor[]::new);
	}

	private void registerListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, this);
	}

}
