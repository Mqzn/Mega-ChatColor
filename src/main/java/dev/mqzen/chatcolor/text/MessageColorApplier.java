package dev.mqzen.chatcolor;

import org.bukkit.ChatColor;

public final class MessageColorApplier {

	private final String message;
	private String result;

	private MessageColorApplier(String message) {
		this.message = message;
	}

	public static MessageColorApplier of(String message) {
		return new MessageColorApplier(message);
	}

	public MessageColorApplier applyColor(ChatColor chatColor) {
		if(chatColor.isFormat()){
			return applyStyle(TextStyle.from(chatColor));
		}
		result = chatColor+message;
		return this;
	}

	public MessageColorApplier applyStyle(TextStyle style) {
		result = style.getCode()+message;
		return this;
	}

	public String result() {
		return result;
	}

}
