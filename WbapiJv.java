
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class WbapiJv {


    public static void main(String[] args) throws IOException {


        // 创建 HTTP 服务器，绑定到 8080 端口
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);

        // 设置上下文和处理器
        server.createContext("/", new MyHandler());

        // 启动服务器
        server.setExecutor(null); // 使用默认的线程池
        server.start();

        System.out.println("Server is listening on port 8080...");

    }


    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello, World!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
