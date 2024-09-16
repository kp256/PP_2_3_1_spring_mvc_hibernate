package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToUsers() {
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "/users";
    }

    @GetMapping(value = "/users", params = "count")
    public String index(@RequestParam(value = "count", required = false) Integer count, Model model) {
        model.addAttribute("users", userService.getUsersByCount(count));
        model.addAttribute("count", count);
        return "/users";
    }

    @GetMapping(value = "/users/show", params = "id")
    public String showUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/show";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/new";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/new";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/users/edit", params = "id")
    public String editUser(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit";
    }

    @PostMapping(value = "/users/update", params = "id")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult result, @RequestParam("id") int id) {
        if (result.hasErrors()) {
            return "/edit";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @PostMapping(value = "/users/delete", params = "id")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}