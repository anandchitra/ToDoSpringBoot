package com.project.mytodos.Controller;

import com.project.mytodos.entity.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class homeController {
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello world";
    }
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        //throw new RuntimeException("Some Error has happened spring");
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        //throw new RuntimeException("Some Error has happened spring");
        return new HelloWorldBean(String.format("Hello World from path",name));
    }

}
