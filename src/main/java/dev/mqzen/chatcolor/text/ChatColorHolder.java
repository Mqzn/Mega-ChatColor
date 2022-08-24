package dev.mqzen.chatcolor.text;

import lombok.*;
import org.bukkit.ChatColor;

import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class ChatColorHolder {

	private final @Getter UUID uuid;
	private @Getter ChatColor color;
	private @Getter TextStyle style;

	private @Getter @Setter boolean rainbow;


	public void setColor(ChatColor color) {
		this.color = color;
		rainbow = false;
	}

	public void setStyle(TextStyle style) {
		this.style = style;
		rainbow = false;
	}

	public static ChatColorHolder of(UUID uuid) {
		return new ChatColorHolder(uuid);
	}


}
