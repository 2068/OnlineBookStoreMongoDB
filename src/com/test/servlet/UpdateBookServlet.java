package com.test.servlet;

import com.test.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String pnum = request.getParameter("pnum");
        String category = request.getParameter("category");
        BookServiceImpl bookService = new BookServiceImpl();
        bookService.updateBook(id, name, price, pnum, category);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/BookListServlet");
    }
}
