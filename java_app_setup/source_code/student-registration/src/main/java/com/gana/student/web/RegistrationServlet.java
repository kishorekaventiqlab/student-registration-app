package com.gana.student.web;

import com.gana.student.dao.StudentDao;
import com.gana.student.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        String email = req.getParameter("email").trim();
        String course = req.getParameter("course").trim();

        String error = null;
        if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
            error = "All fields are required.";
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            error = "Invalid email address.";
        }

        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        try {
            Student s = new Student();
            s.setName(name);
            s.setEmail(email);
            s.setCourse(course);
            new StudentDao().insertStudent(s);
            resp.sendRedirect(req.getContextPath() + "/list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
