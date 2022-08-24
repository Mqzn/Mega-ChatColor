package dev.mqzen.chatcolor.text;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public final class ChatColorManager {

	private static ChatColorManager instance;
	private final Map<UUID, ChatColorHolder> chatColors = new HashMap<>();

	private final @Getter String rainbowPrefix = "&b&lR&9&lA&a&lI&e&lN&6&lB&c&lO&4&lW";

	public static ChatColorManager getInstance() {
		if (instance == null) {
			instance = new ChatColorManager();
		}
		return instance;
	}

	public ChatColorHolder getChatColor(UUID uuid) {
		return chatColors.get(uuid);
	}

	public void setChatColor(UUID uuid, ChatColorHolder chatColor) {
		chatColors.put(uuid, chatColor);
	}

	public void update(UUID uuid, Consumer<ChatColorHolder> update) {
		chatColors.compute(uuid,
						(viewerId, entity) -> {
							if (entity == null) {
								entity = ChatColorHolder.of(uuid);
							}
							update.accept(entity);
							return entity;
						});
	}

}
