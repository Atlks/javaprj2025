package lib;

import java.util.Map;

import org.fusesource.leveldbjni.JniDBFactory;
import org.rocksdb.*;

import java.io.File;

public class UtilKvdbLevDb {


    public static void main(String[] args) throws Exception {


        // Example Map to insert
        Map<String, Object> exampleMap = Map.of(
                "id", "John Doe",
                "age", 30,
                "city", "New York"
        );

        // Example dynamic database URL
        String dbUrl = "aaaa.sqlt.db";

        AddRow(exampleMap, dbUrl);


        System.out.println("Map inserted successfully!");

    }

    private static void AddRow(Map<String, Object> exampleMap, String dbUrl) throws RocksDBException {

        // 数据库路径
        String dbPath = "rockt24.db";


        // 创建 RocksDB 数据库
        Options options = new Options().setCreateIfMissing(true);
        RocksDB db = RocksDB.open(options, dbPath);


        // 写入数据
        db.put("key1".getBytes(), "value1".getBytes());
        db.put("key2".getBytes(), "value2".getBytes());


    }

//    private static void AddRow(Map<String, Object> exampleMap, String dbUrl) {
//
//
//        import org.fusesource.leveldbjni.DB;
//        // 数据库路径
//        File dbFile = new File("path/to/your/db");
//
//        // 打开数据库
//        try (DB db = JniDBFactory.open(dbFile)) {
//            // 写入数据
//            db.put("key1".getBytes(), "value1".getBytes());
//            db.put("key2".getBytes(), "value2".getBytes());
//        }
//
//
//    }


}
