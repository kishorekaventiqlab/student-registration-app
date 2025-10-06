package com.gana.student.model;

public class Student {
    private int id;
    private String name;
    private String email;
    private String course;
    private String registeredAt;

    public Student() {}

    public Student(int id, String name, String email, String course, String registeredAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.registeredAt = registeredAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(String registeredAt) { this.registeredAt = registeredAt; }
}
