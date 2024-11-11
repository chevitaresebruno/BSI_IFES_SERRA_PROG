package adiantando_codigo.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller
// @ResponseBody
@RestController
public class HelloController {

    @RequestMapping("/")
    public static String hello() {
        return "Hello World, Spring Boot!";
    }

}