package com.example.demo.controller;

import java.util.List;

import com.example.demo.mapper.TodoMapper;
import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoMapper todoMapper;
    private final TodoService todoService;

    public TodoController(TodoMapper todoMapper, TodoService todoService) {
        this.todoMapper = todoMapper;
        this.todoService = todoService;
    }

    @GetMapping
    public String list(Model model) {
        List<Todo> todoList = todoMapper.findAll();
        model.addAttribute("todoList", todoList);
        return "todo/list";
    }

    @GetMapping("/new")
    public String newTodo() {
        return "todo/form";
    }

    @GetMapping("/form")
    public String form() {
        return "todo/form";
    }

    @PostMapping("/confirm")
    public String confirm(@RequestParam("title") String title, Model model) {
        model.addAttribute("title", title);
        return "todo/confirm";
    }

    @GetMapping("/complete")
    public String complete() {
        return "redirect:/todo/new";
    }

    @PostMapping("/complete")
    public String complete(@RequestParam("title") String title) {
        todoService.create(title);
        return "redirect:/todo";
    }
}
