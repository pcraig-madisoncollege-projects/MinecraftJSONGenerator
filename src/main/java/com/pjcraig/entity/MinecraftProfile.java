package com.pjcraig.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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
	 * Constructs an empty Minecraft profile.
	 */
	public MinecraftProfile() {

	}

	/**
	 * Constructs Minecraft profile consisting of a name and uuid.
	 * @param name The name of the profile.
	 * @param id The String uuid of the profile.
	 */
	public MinecraftProfile(String name, String id) {
		this.name = name;
		this.id = id;
	}

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

	/**
	 * Sets the unique player name as seen in-game.
	 * @param name The String name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the uuid of the player profile.
	 * @param id The String uuid.
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MinecraftProfile profile = (MinecraftProfile) o;
		return Objects.equals(getName(), profile.getName()) && getId().equals(profile.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getId());
	}
}