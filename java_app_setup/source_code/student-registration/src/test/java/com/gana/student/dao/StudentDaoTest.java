package com.gana.student.dao;

import com.gana.student.model.Student;
import com.gana.student.util.DbUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDaoTest {

    static MockedStatic<DbUtil> dbUtilMock;

    @BeforeAll
    static void setupDatabase() throws Exception {
        // create H2 in-memory database and initialize schema using a temporary connection
        Class.forName("org.h2.Driver");
        try (Connection initConn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1")) {
            try (Statement st = initConn.createStatement()) {
                st.execute("CREATE TABLE students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), course VARCHAR(255), registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
            }
        }

        // Mock DbUtil.getConnection() to return a new H2 connection for each call
        dbUtilMock = Mockito.mockStatic(DbUtil.class);
        dbUtilMock.when(() -> DbUtil.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"));
    }

    @AfterAll
    static void teardown() throws Exception {
        if (dbUtilMock != null) dbUtilMock.close();
    }

    @Test
    void insertAndListStudent() throws Exception {
        StudentDao dao = new StudentDao();

        Student s = new Student();
        s.setName("Alice");
        s.setEmail("alice@example.com");
        s.setCourse("Mathematics");

        // insert
        dao.insertStudent(s);

        // list and verify
        List<Student> list = dao.listAll();
        assertNotNull(list);
        assertEquals(1, list.size());

        Student got = list.get(0);
        assertEquals("Alice", got.getName());
        assertEquals("alice@example.com", got.getEmail());
        assertEquals("Mathematics", got.getCourse());
        assertTrue(got.getRegisteredAt() != null && !got.getRegisteredAt().isEmpty());
    }
}
