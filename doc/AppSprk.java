package doc;

import static spark.Spark.*;

public class AppSprk {
    public static void main(String[] args) {
        // 设置端口

        //javax.servlet.SessionCookieConfig

        System.out.println(org.eclipse.jetty.server.Handler.class);
        System.out.println(javax.servlet.Filter .class);
        port(4567);

        // GET 请求处理
        get("/", (req, res) -> {
            res.type("application/json");
            return "{\"message\": \"Hello, World!\"}";
        });

        // POST 请求处理
        post("/api/greet", (req, res) -> {
            String name = req.queryParams("name");
            res.type("application/json");
            return "{\"message\": \"Hello, " + name + "!\"}";
        });
    }
}
