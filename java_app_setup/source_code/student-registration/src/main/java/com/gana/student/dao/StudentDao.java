package com.gana.student.dao;

import com.gana.student.model.Student;
import com.gana.student.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public void insertStudent(Student s) throws Exception {
        String sql = "INSERT INTO students(name,email,course) VALUES (?,?,?)";
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getCourse());
            ps.executeUpdate();
        }
    }

    public List<Student> listAll() throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id,name,email,course,registered_at FROM students ORDER BY registered_at DESC";
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getTimestamp("registered_at").toString()
                );
                list.add(s);
            }
        }
        return list;
    }
}
