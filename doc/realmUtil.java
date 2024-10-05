import io.realm.Realm;
import io.realm.RealmConfiguration;

public class realmUtil {
    public static void main(String[] args) {
        // 初始化 Realm
        Realm.init();

        // 配置 Realm
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .schemaVersion(1) // schema version
                .build();

        // 设置默认配置
        Realm.setDefaultConfiguration(config);

        // 使用 Realm
        try (Realm realm = Realm.getDefaultInstance()) {
            // 数据库操作
        }
    }
}
