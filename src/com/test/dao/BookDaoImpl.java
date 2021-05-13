package com.test.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.test.model.Book;
import com.test.model.PageResult;
import com.test.util.MongoDBUtil;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl {

    MongoDatabase mongoDatabase = MongoDBUtil.getConnect();
    MongoCollection<Document> collection = mongoDatabase.getCollection("books");

    public FindIterable<Document> findAllBooks() {
        FindIterable<Document> findIterable = collection.find();
        return findIterable;
    }

    public void addBook(String name, String price, String pnum, String category) {
        Document document = new Document();
        document.append("name", name);
        document.append("price", price);
        document.append("pnum", pnum);
        document.append("category", category);
        collection.insertOne(document);
    }

    public void deleteBook(String id) {
        ObjectId objectId = new ObjectId(id);
        BasicDBObject query = new BasicDBObject("_id", objectId);
        collection.deleteOne(query);
    }

    public FindIterable<Document> findBookById(String id) {
        ObjectId objectId = new ObjectId(id);
        BasicDBObject query = new BasicDBObject("_id", objectId);
        FindIterable<Document> findIterable = collection.find(query);
        return findIterable;
    }

    public void updateBook(String id, String name, String price, String pnum, String category) {
        ObjectId objectId = new ObjectId(id);
        BasicDBObject query = new BasicDBObject("_id", objectId);
        Document update = new Document();
        update.append("name", name);
        update.append("price", price);
        update.append("pnum", pnum);
        update.append("category", category);
        Document document = new Document("$set", update);
        collection.updateOne(query, document);
    }

    public FindIterable<Document> SearchBooks(String name) {
        Bson filter = Filters.eq("name", name);
        FindIterable<Document> findIterable = collection.find(filter);
        return findIterable;
    }

    public PageResult<Book> findBooksByPage(int page) {
        PageResult<Book> pr = new PageResult<>();
        long totalCount = collection.count();
        System.out.println(totalCount);
        pr.setTotalCount(totalCount);
        int totalPage = (int) (totalCount % pr.getPageCount() == 0 ? totalCount / pr.getPageCount() : totalCount / pr.getPageCount() + 1);
        System.out.println(totalPage);
        pr.setTotalPage(totalPage);
        pr.setCurrentPage(page);
        int start = (page - 1) * pr.getPageCount();
        FindIterable<Document> skipBooks = collection.find().limit(pr.getPageCount()).skip(start);
        MongoCursor<Document> cursor = skipBooks.iterator();
        List<Book> books = new ArrayList<>();
        while (cursor.hasNext()) {
            Document next = cursor.next();
            String s = next.toJson();
            Gson gson = new Gson();
            Book book = gson.fromJson(s, Book.class);
            ObjectId id1 = (ObjectId) next.get("_id");
            book.setId(id1);
            books.add(book);
        }
        pr.setList(books);
        return pr;
    }

}
