package com.gana.student.web;

import com.gana.student.dao.StudentDao;
import com.gana.student.model.Student;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationServletTest {

    @Test
    void doGetForwardsToIndex() throws ServletException, IOException {
        RegistrationServlet servlet = new RegistrationServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("/index.jsp")).thenReturn(rd);

        servlet.doGet(req, resp);

        verify(req).getRequestDispatcher("/index.jsp");
        verify(rd).forward(req, resp);
    }

    @Test
    void doPostWithInvalidInputForwardsWithError() throws ServletException, IOException {
        RegistrationServlet servlet = new RegistrationServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(req.getParameter("name")).thenReturn("   ");
        when(req.getParameter("email")).thenReturn("bademail");
        when(req.getParameter("course")).thenReturn("");
        when(req.getRequestDispatcher("/index.jsp")).thenReturn(rd);

        servlet.doPost(req, resp);

        verify(req).setAttribute(eq("error"), anyString());
        verify(rd).forward(req, resp);
        verify(resp, never()).sendRedirect(anyString());
    }

    @Test
    void doPostWithValidInputInsertsAndRedirects() throws Exception {
        RegistrationServlet servlet = new RegistrationServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("name")).thenReturn("Carol");
        when(req.getParameter("email")).thenReturn("carol@example.com");
        when(req.getParameter("course")).thenReturn("Chemistry");
        when(req.getContextPath()).thenReturn("");

        // Inject a mock StudentDao and verify insertStudent called
        StudentDao mockDao = mock(StudentDao.class);
        doNothing().when(mockDao).insertStudent(any(Student.class));
        servlet.setStudentDao(mockDao);

        servlet.doPost(req, resp);

        verify(mockDao).insertStudent(any(Student.class));
        verify(resp).sendRedirect("/list");
    }
}
