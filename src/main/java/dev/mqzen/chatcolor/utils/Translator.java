package dev.mqzen.chatcolor.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public final class Translator {

	private Translator() {
	}

	public static String color(String msg) {
		if (msg == null) return "NULL";
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public static List<String> colorList(List<String> list) {
		return list.stream().map(Translator::color)
						.collect(Collectors.toList());
	}


}
