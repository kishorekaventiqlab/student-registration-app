package com.gana.student.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void gettersAndSetters() {
        Student s = new Student();
        s.setId(10);
        s.setName("Bob");
        s.setEmail("bob@example.com");
        s.setCourse("Physics");
        s.setRegisteredAt("2025-10-08 12:00:00");

        assertEquals(10, s.getId());
        assertEquals("Bob", s.getName());
        assertEquals("bob@example.com", s.getEmail());
        assertEquals("Physics", s.getCourse());
        assertEquals("2025-10-08 12:00:00", s.getRegisteredAt());
    }
}
