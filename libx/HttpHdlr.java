package libx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


 //  /api/hello
@RestController
@RequestMapping("/api")
public class HttpHdlr {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
