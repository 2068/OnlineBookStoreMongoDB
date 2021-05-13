package com.test.service;

import com.mongodb.client.FindIterable;
import com.test.dao.BookDaoImpl;
import com.test.model.Book;
import com.test.model.PageResult;
import org.bson.Document;

public class BookServiceImpl {
    BookDaoImpl bookDao = new BookDaoImpl();

    public FindIterable<Document> findAllBooks() {
        return bookDao.findAllBooks();
    }

    public void addBook(String name, String price, String pnum, String category) {
        bookDao.addBook(name, price, pnum, category);
    }

    public void deleteBook(String id) {
        bookDao.deleteBook(id);
    }

    public FindIterable<Document> findBookById(String id) {
        return bookDao.findBookById(id);
    }

    public void updateBook(String id, String name, String price, String pnum, String category) {
        bookDao.updateBook(id, name, price, pnum, category);
    }

    public FindIterable<Document> SearchBooks(String name) {
        return bookDao.SearchBooks(name);
    }

    public PageResult<Book> findBooksByPage(int page) {
        return bookDao.findBooksByPage(page);
    }
}
