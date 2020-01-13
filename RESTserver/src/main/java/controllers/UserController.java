package controllers;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{username}/{pass}")
    public ResponseEntity<User> getUserByName(@PathVariable(value = "username") String username, @PathVariable(value = "pass") String pass)
    {
        User tempUser = null;
        User checkUser = userRepository.findByUsername(username);
        if(checkUser.getPassword().equals(pass))
        {
            tempUser = checkUser;
        }
        return ResponseEntity.ok(tempUser);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user)
    {
        try {
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    private boolean checkUserExists(String username)
    {
        boolean state;
        User checkUser = userRepository.findByUsername(username);
        if(checkUser.getUsername().equals(username))
        {
            state = true;
        }
        else{
            state = false;
        }
        return state;
    }

}
