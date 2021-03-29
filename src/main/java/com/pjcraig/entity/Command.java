package com.pjcraig.entity;

import com.google.gson.annotations.Expose;
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
    @Expose
    private int id;

    @Expose
    private String name;
    @Column(name="folder")
    private String group;
    @Column(name="date_modified")
    private LocalDate dateModified;
    @Column(name="command")
    @Expose
    private String value;
    private boolean shared;

    @ManyToOne
    @Expose
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
     * @param value the command
     */
    public Command(User owner, String value) {
        this(owner, "Untitled", "None", LocalDate.now(), false, value);
    }

    /**
     * Instantiates a new Command.
     *
     * @param owner        the owner
     * @param name         the name
     * @param group        the group
     * @param dateModified the date modified
     * @param shared       the shared
     * @param value      the command
     */
    public Command(User owner, String name, String group, LocalDate dateModified, boolean shared, String value) {
        this.name = name;
        this.group = group;
        this.dateModified = dateModified;
        this.value = value;
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
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
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
        Command command = (Command) o;
        return getId() == command.getId() && isShared() == command.isShared() && getName().equals(command.getName())
                && getGroup().equals(command.getGroup()) && getDateModified().equals(command.getDateModified())
                && getValue().equals(command.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGroup(), getDateModified(), getValue(), isShared());
    }
}
