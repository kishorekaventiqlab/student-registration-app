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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListServletTest {

    @Test
    void doGetRetrievesStudentsAndForwards() throws Exception {
        ListServlet servlet = new ListServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("/list.jsp")).thenReturn(rd);

        // Prepare fake students list
        Student s1 = new Student();
        s1.setName("A");
        Student s2 = new Student();
        s2.setName("B");
        List<Student> fakeList = Arrays.asList(s1, s2);

        // Inject a mock StudentDao that returns the fake list
        StudentDao mockDao = mock(StudentDao.class);
        when(mockDao.listAll()).thenReturn(fakeList);
        servlet.setStudentDao(mockDao);

        servlet.doGet(req, resp);

        verify(mockDao).listAll();
        verify(req).setAttribute("students", fakeList);
        verify(rd).forward(req, resp);
    }
}
