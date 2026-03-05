package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String list(Model model) {
        List<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
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

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Todo todo = todoService.findById(id);
        if (todo == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "\u30c7\u30fc\u30bf\u304c\u5b58\u5728\u3057\u307e\u305b\u3093");
            return "redirect:/todo";
        }
        model.addAttribute("todo", todo);
        return "todo/edit";
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

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            boolean deleted = todoService.deleteById(id);
            if (deleted) {
                redirectAttributes.addFlashAttribute("successMessage", "\u0054\u006f\u0044\u006f\u3092\u524a\u9664\u3057\u307e\u3057\u305f");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "\u524a\u9664\u306b\u5931\u6557\u3057\u307e\u3057\u305f");
            }
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "\u524a\u9664\u306b\u5931\u6557\u3057\u307e\u3057\u305f");
        }
        return "redirect:/todo";
    }
}
