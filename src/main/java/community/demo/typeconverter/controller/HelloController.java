package community.demo.typeconverter.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data");
        Integer intValue = Integer.valueOf(data);
        System.out.println("intValue = " + intValue);
        return "ok";
    }

    // 파라미터로 들어온 데이터 -> String
    // 근데 RequestParam Integer 타입으로 받았기 때문에 자동으로 컨버팅됨
    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        System.out.println("data = " + data);
        return "ok";
    }

    @GetMapping("/hello-v3")
    public String helloV3(@ModelAttribute UserData data) {
        System.out.println("data = " + data);
        return "ok";
    }

    class UserData {
        Integer data;
    }

    @GetMapping("/hello-v4/{id}")
    public String helloV4(@PathVariable Integer id) {
        System.out.println("id = " + id);
        return "ok";
    }


}
