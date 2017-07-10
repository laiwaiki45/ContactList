package com.lwk.contactlist;

import java.io.Serializable;

/**
 * Created by laiwa1 on 10.07.2017.
 */

public class Person implements Serializable {

    private String name;
    private boolean male;
    private String address;
    private int phone;
    private String nickname;
    private String email;

    public Person(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public Person(String name) {
        this.name = name;

    }

    public Person(String name, boolean male, int phone) {
        this.name = name;
        this.male = male;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }


    public boolean isMale() {
        return this.male;
    }

    public String getAddress() {
        return this.address;
    }

    public int getPhone() {
        return this.phone;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

