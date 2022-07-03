package com.twhupup.controller;

import com.twhupup.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class HelloController {
    @Value("${person.name}")
    private String name2;

    @Autowired
    private Environment env;

    @Autowired
    private Person person;

    @GetMapping("/hello")
    public String hello(){
        // System.out.println(name1);
        // System.out.println(name2);
        // System.out.println();
        // System.out.println(env.getProperty("person.age"));
        System.out.println(person);
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(Arrays.toString(person.getAddress()));
        return "success";
    }
}
