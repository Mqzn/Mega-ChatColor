package dev.mqzen.chatcolor.text;

import dev.mqzen.chatcolor.MegaChatColor;
import dev.mqzen.chatcolor.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class MessageColorApplier {

	private final String message;
	private String result;

	private MessageColorApplier(String message) {
		this.message = message;
	}

	public static MessageColorApplier of(String message) {
		return new MessageColorApplier(message);
	}

	public static String result(UUID uuid, String msg) {

		ChatColorHolder holder = ChatColorManager.getInstance().getChatColor(uuid);
		if (holder == null) {
			return msg;
		}
		MessageColorApplier applier = MessageColorApplier.of(msg);

		if(holder.isRainbow()) {
			return applyRainbow(msg);
		}
		if (holder.getStyle() != null) {
			applier.applyStyle(holder.getStyle());
		}
		if (holder.getColor() != null) {
			applier = applier.applyColor(holder.getColor());
		}
		return applier.result();
	}

	public static ItemStack toItem(UUID uuid, ChatColor color, boolean rainbow) {
		ItemBuilder builder = new ItemBuilder(Material.WOOL, 1, getDamage(color))
						.glow(true).setDisplay(color + color.name());
		ChatColorHolder colorHolder = ChatColorManager.getInstance().getChatColor(uuid);
		String msg = "&7&oClick to select the color";
		if (!rainbow && colorHolder != null && colorHolder.getColor() == color) {
			msg = "&a&lSelected";
		}

		return builder.setLore("", msg).build();
	}

	public static ItemStack toItem(UUID uuid, ChatColor color) {
		return toItem(uuid, color, false);
	}

	private static short getDamage(ChatColor color) {
		if (color == ChatColor.WHITE || color.isFormat()) return 0;
		switch (color) {

			case BLACK:
				return 15;

			case DARK_BLUE:
			case BLUE:
				return 11;

			case DARK_GREEN:
				return 13;
			case GREEN:
				return 5;

			case AQUA:
				return 3;
			case DARK_AQUA:
				return 9;

			case LIGHT_PURPLE:
				return 6;

			case DARK_PURPLE:
				return 10;

			case YELLOW:
				return 4;
			case GOLD:
				return 1;

			case RED:
				return 14;

			case DARK_RED:
				return 12;

			case DARK_GRAY:
				return 7;
			case GRAY:
				return 8;

			default:
				return 0;
		}


	}

	private static String applyRainbow(String message) {

		char[] chars = message.toCharArray();

		StringBuilder result = new StringBuilder();
		for(char c : chars) {
			int randomIndex = ThreadLocalRandom.current().nextInt(MegaChatColor.COLORS.length);
			ChatColor randomColor = MegaChatColor.COLORS[randomIndex];
			result.append(randomColor.toString()).append(c);
		}

		return result.toString();
	}


	public MessageColorApplier applyColor(ChatColor chatColor) {
		if (chatColor.isFormat()) {
			return applyStyle(TextStyle.from(chatColor));
		}
		result = chatColor + message;
		return this;
	}

	public MessageColorApplier applyStyle(TextStyle style) {
		result = style.getCode() + message;
		return this;
	}

	public String result() {
		return result;
	}
}
