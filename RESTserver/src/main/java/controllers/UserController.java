package controllers;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @PostMapping("/saveUsers")
    public String saveUser(@RequestBody User user)
    {
        userRepository.save(user);
        return "user saved:" + user.getUsername();
    }
    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }
}
