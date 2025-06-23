/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author USER
 */
public class MongoLogger {

    private static final String URI = "mongodb://localhost:27017"; // sesuaikan kalau pakai MongoDB Atlas
    private static final String DB_NAME = "bukutamu_logs";
    private static final String COLLECTION_NAME = "logs";

    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;

    public MongoLogger() {
        mongoClient = MongoClients.create(URI); // Gunakan MongoClients (bukan MongoClient)
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        collection = database.getCollection(COLLECTION_NAME);
    }

    public void logAsync(String nama, String aktivitas) {
        new Thread(() -> log(nama, aktivitas)).start(); // Multi-threaded logging
    }

    public void log(String nama, String aktivitas) {
        Document doc = new Document("nama", nama)
                .append("aktivitas", aktivitas)
                .append("waktu", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        collection.insertOne(doc);
        System.out.println("Log berhasil disimpan ke MongoDB untuk: " + nama);
    }

    public void close() {
        mongoClient.close();
    }
}
