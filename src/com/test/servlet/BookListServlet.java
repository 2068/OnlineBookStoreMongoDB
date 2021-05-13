package com.test.servlet;

import com.test.model.Book;
import com.test.model.PageResult;
import com.test.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        if(page==null||"".equals(page)){
            page="1";
        }
        BookServiceImpl bookService = new BookServiceImpl();
        PageResult<Book> pageResult = bookService.findBooksByPage(Integer.parseInt(page));
        request.setAttribute("pageResult", pageResult);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
