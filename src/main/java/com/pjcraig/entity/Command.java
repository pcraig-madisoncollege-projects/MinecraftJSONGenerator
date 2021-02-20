package com.pjcraig.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Command, which represents one of the JSON-component commands that can be generated.
 *
 * @author pjcraig
 */
@Entity(name="Command")
@Table(name="command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String command;
    private int owner;
    private boolean shared;

    /**
     * Instantiates a new Command.
     */
    public Command() {
    }

    /**
     * Instantiates a new Command.
     *
     * @param command the command
     * @param owner   the owner
     */
    public Command(String command, int owner) {
        this(command, owner, false);
    }

    /**
     * Instantiates a new Command.
     *
     * @param command the command
     * @param owner   the owner
     * @param shared  the shared
     */
    public Command(String command, int owner, boolean shared) {
        this.command = command;
        this.owner = owner;
        this.shared = shared;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets command.
     *
     * @param command the command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public int getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(int owner) {
        this.owner = owner;
    }

    /**
     * Is shared boolean visibility state.
     *
     * @return the boolean
     */
    public boolean isShared() {
        return shared;
    }

    /**
     * Sets shared visibility state.
     *
     * @param shared the shared
     */
    public void setShared(boolean shared) {
        this.shared = shared;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return getId() == command.getId() && getOwner() == command.getOwner()
                && isShared() == command.isShared()
                && getCommand().equals(command.getCommand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCommand(), getOwner(), isShared());
    }
}
