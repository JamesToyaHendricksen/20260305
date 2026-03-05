package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @GetMapping
    public String list(Model model) {
        List<TodoView> todoList = List.of(
                new TodoView(1L, "Learn Spring Boot", "TODO"),
                new TodoView(2L, "Create todo list page", "DOING"),
                new TodoView(3L, "Tune screen style", "DONE"));

        model.addAttribute("todoList", todoList);
        return "todo/list";
    }

    @GetMapping("/new")
    public String newTodo() {
        return "todo/new";
    }

    @GetMapping("/confirm")
    public String confirm() {
        return "todo/confirm";
    }

    @GetMapping("/complete")
    public String complete() {
        return "todo/complete";
    }

    private record TodoView(Long id, String title, String status) {
    }
}