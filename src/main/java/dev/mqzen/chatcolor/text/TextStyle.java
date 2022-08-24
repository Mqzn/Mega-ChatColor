package dev.mqzen.chatcolor.text;

import org.bukkit.ChatColor;

public enum TextStyle {


	BOLD('l'),
	ITALIC('o'),
	UNDERLINE('n'),
	STRIKETHROUGH('m'),
	MAGIC('k'),
	RESET('r');

	private final String code;

	TextStyle(char charCode) {
		this.code = "&" + charCode;
	}

	public static TextStyle from(ChatColor chatColor) {
		if (chatColor == null) return null;
		if (!chatColor.isFormat()) return null;
		return TextStyle.valueOf(chatColor.name());
	}

	public String getCode() {
		return code;
	}

}
