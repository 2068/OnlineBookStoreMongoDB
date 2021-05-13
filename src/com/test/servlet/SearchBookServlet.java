package com.test.servlet;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.test.model.Book;
import com.test.model.PageResult;
import com.test.service.BookServiceImpl;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        BookServiceImpl bookService = new BookServiceImpl();
        FindIterable<Document> findIterable = bookService.SearchBooks(name);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Book> books = new ArrayList<>();
        while (cursor.hasNext()) {
            Document next = cursor.next();
            String s = next.toJson();
            Gson gson = new Gson();
            Book book = gson.fromJson(s, Book.class);
            ObjectId id = (ObjectId) next.get("_id");
            book.setId(id);
            books.add(book);
        }
        PageResult<Book> pageResult = new PageResult<>();
        pageResult.setList(books);
        request.setAttribute("pageResult", pageResult);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
