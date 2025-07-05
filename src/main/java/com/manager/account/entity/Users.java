package com.manager.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class Users extends Base {
    @Id
    @Column(name = "username")
    String username;

    @Column(name = "password")
    @JsonIgnore
    String password;

    @Column(name = "full_name")
    String full_name;

    @Column(name = "phone_number")
    String phone_number;

    @Column(name = "email")
    String email;

    @Column(name = "number_following")
    Long number_following = 0L;

    @Column(name = "number_follower")
    Long number_follower = 0L;

    @Column(name = "number_likes")
    Long number_likes = 0L;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "birthday")
    String birthday;

    @Column(name = "role")
    String role;

    @Column(name = "reason")
    String reason;

    @Column(name = "account_status")
    String accountStatus;

    @Column(name = "created_date")
    Date createdDate;

    public Users() {
    }

    public Users(String email, String phone_number, String full_name, String password, String username, String birthday, String role, String accountStatus) {
        this.email = email;
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.password = password;
        this.username = username;
        this.birthday = birthday;
        this.role = role;
        this.accountStatus = accountStatus;
        this.createdDate = new Date();
    }

    public Users(String email, String phone_number, String full_name, String password, String username, String birthday, String role, String reason, String accountStatus) {
        this.email = email;
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.password = password;
        this.username = username;
        this.birthday = birthday;
        this.role = role;
        this.reason = reason;
        this.accountStatus = accountStatus;
    }
}
