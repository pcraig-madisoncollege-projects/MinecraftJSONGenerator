package com.pjcraig.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
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

    private String name;
    private String group;
    @Column(name="date_modified")
    private LocalDate dateModified;
    private String command;
    private boolean shared;

    @ManyToOne
    private User owner;

    /**
     * Instantiates a new Command.
     */
    public Command() {
    }

    /**
     * Instantiates a new Command.
     *
     * @param owner   the owner
     * @param command the command
     */
    public Command(User owner, String command) {
        this(owner, "Untitled", "None", LocalDate.now(), false, command);
    }

    /**
     * Instantiates a new Command.
     *
     * @param owner        the owner
     * @param name         the name
     * @param group        the group
     * @param dateModified the date modified
     * @param shared       the shared
     * @param command      the command
     */
    public Command(User owner, String name, String group, LocalDate dateModified, boolean shared, String command) {
        this.name = name;
        this.group = group;
        this.dateModified = dateModified;
        this.command = command;
        this.shared = shared;
        this.owner = owner;
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
    public User getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(User owner) {
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


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets group.
     *
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets group.
     *
     * @param group the group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Gets date modified.
     *
     * @return the date modified
     */
    public LocalDate getDateModified() {
        return dateModified;
    }

    /**
     * Sets date modified.
     *
     * @param dateModified the date modified
     */
    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command1 = (Command) o;
        return getId() == command1.getId() && isShared() == command1.isShared() && getName().equals(command1.getName())
                && getGroup().equals(command1.getGroup()) && getDateModified().equals(command1.getDateModified())
                && getCommand().equals(command1.getCommand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGroup(), getDateModified(), getCommand(), isShared());
    }
}
