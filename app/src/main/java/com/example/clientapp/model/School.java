package com.example.clientapp.model;

import java.util.List;

public class School {
    private int id;
    private String name;
    private String identifier;
    private List<Smember> members;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<Smember> getMembers() {
        return members;
    }

    public void setMembers(List<Smember> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
