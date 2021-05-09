package com.pjcraig.entity;

import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.*;

/**
 * The User entity.
 *
 * @author pjcraig
 */
@Entity(name="User")
@Table(name="user")
public class User {
    @Transient
    private final Logger logger = LogManager.getLogger();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Expose
    private int id;
    private String email;
    private String password;
    @Column(name="notification")
    private String message;
    @Expose
    private String nickname;
    @Expose
    @Column(name="mcprofile")
    private String minecraftProfile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Command> commands = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Instantiates a new User.
     *
     * @param email    the email
     * @param password the password
     * @param nickname the nickname
     */
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets nickname.
     *
     * @param nickname the nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Gets this user's saved commands.
     *
     * @return the commands
     */
    public Set<Command> getCommands() {
        return commands;
    }

    /**
     * Sets this user's saved commands.
     *
     * @param commands the commands
     */
    public void setCommands(Set<Command> commands) {
        this.commands = commands;
    }

    /**
     * Gets the user's roles.
     *
     * @return the list of roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the user's roles.
     *
     * @param roles the list of roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets minecraft profile.
     *
     * @return the minecraft profile
     */
    public String getMinecraftProfile() {
        return minecraftProfile;
    }

    /**
     * Sets minecraft profile.
     *
     * @param minecraftProfile the minecraft profile
     */
    public void setMinecraftProfile(String minecraftProfile) {
        this.minecraftProfile = minecraftProfile;
    }

    /**
     * Adds a command to the user.
     *
     * @param command the command
     */
    public void addCommand(Command command) {
        commands.add(command);
        command.setOwner(this);
    }

    /**
     * Removes a command from the user.
     *
     * @param command the command
     */
    public void removeCommand(Command command) {
        commands.remove(command);
        command.setOwner(null);
    }

    /**
     * Adds a role to the user.
     *
     * @param role the role
     */
    public void addRole(Role role) {
        roles.add(role);
        role.setUser(this);
    }

    /**
     * Removes a role from the user.
     *
     * @param role the command
     */
    public void removeRole(Role role) {
        roles.remove(role);
        role.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.getId() && email.equals(user.getEmail())
                && password.equals(user.getPassword())
                && nickname.equals(user.getNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, nickname);
    }
}
