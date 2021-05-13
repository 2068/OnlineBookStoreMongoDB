package com.test.servlet;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.test.model.Book;
import com.test.service.BookServiceImpl;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FindBookByIdServlet")
public class FindBookByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        BookServiceImpl bookService = new BookServiceImpl();
        FindIterable<Document> findIterable = bookService.findBookById(id);
        MongoCursor<Document> iterator = findIterable.iterator();
        Document next = iterator.next();
        String s = next.toJson();
        Gson gson = new Gson();
        Book book = gson.fromJson(s, Book.class);
        ObjectId objectId = (ObjectId) next.get("_id");
        book.setId(objectId);
        request.setAttribute("book", book);
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }
}
