package com.pjcraig.entity;

import com.google.gson.annotations.SerializedName;

/**
 * This class stores a Minecraft player profile, consisting of a unique name and a universal unique id (uuid).
 * @author pjcraig
 */
public class MinecraftProfile {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	/**
	 * Gets the unique player name as seen in-game.
	 * @return The String name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the uuid of the player profile.
	 * @return The String uuid.
	 */
	public String getId() {
		return id;
	}
}