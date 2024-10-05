import com.couchbase.lite.*;

import java.util.HashMap;
import java.util.Map;

public class CouchbaseLiteExample {
    public static void main(String[] args) throws CouchbaseLiteException {
        // 初始化 Couchbase Lite
        CouchbaseLite.init();



        setLogLevel(LogLevel.VERBOSE);
        Logger.setLogFile("couchbase.log"); // 设置日志文件的路径


        // 设置日志记录级别
//        CouchbaseLite.setLogLevel(LogDomain.REPLICATION, LogLevel.VERBOSE);
//        CouchbaseLite.setLogLevel(LogDomain.DATABASE, LogLevel.VERBOSE);
//        CouchbaseLite.setLogLevel(LogDomain.QUERY, LogLevel.VERBOSE);
//
//        // 设置日志记录级别
//        CouchbaseLite.setLogLevel(LogDomain.ALL, LogLevel.DEBUG);

        // 创建数据库
        DatabaseConfiguration config = new DatabaseConfiguration();
        Database database;
        try {
            database = new Database("example_db", config);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
            return;
        }

        // 创建一个文档
        Map<String, Object> data = new HashMap<>();
        data.put("type", "example");
        data.put("name", "Couchbase Lite");
        data.put("version", "3.x.x");

        // 插入文档
        Document doc = new MutableDocument("doc1", data);
        try {
            database.save((MutableDocument) doc);
            System.out.println("Document saved: " + doc.getId());
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        // 查询文档
        Document retrievedDoc = database.getDocument("doc1");
        if (retrievedDoc != null) {
            System.out.println("Retrieved document: " + retrievedDoc.getString("name"));
        } else {
            System.out.println("Document not found.");
        }

        // 关闭数据库
        database.close();
    }
}
