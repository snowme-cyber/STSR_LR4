package org.hibernate.xmlbased.model;

public class Developer {
    private int id;
    private String name;
    private String specialty;
    private int experience;
    public Developer() {
    }
    public Developer(String name, String specialty, int experience) {
        this.name = name;
        this.specialty = specialty;
        this.experience = experience;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    @Override
    public String toString() {
        return "Developer [id=" + id + ", name=" + name + ", specialty=" + specialty + ", experience=" + experience + "]";
    }
}
