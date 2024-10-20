package org.example.controller;

import org.example.User;
import org.example.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //localhost:8080/users/new
    @PostMapping("/new")
    public User create(@RequestBody User user){
           return userRepository.create(user);
    }

    //localhost:8080/users/update
    @PatchMapping("/update")
    public void update(@RequestBody User user){
        userRepository.update(user);
    }

    @PostMapping("/get")
    public User getUserById(@RequestBody User user){
        return userRepository.getUserById(user.getId());
    }

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.getUserById(id);
    }

    @DeleteMapping ("/delete")
    public User delete(@RequestBody User user){
        userRepository.delete(user.getId());
        return user;
    }

    @GetMapping("/get/list")
    public List<User> getListUser() {
        return userRepository.getListUser();
    }

}
