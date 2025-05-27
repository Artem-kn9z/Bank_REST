package com.example.bankcards.controller.card.admin;

import com.example.bankcards.entity.user.User;
import com.example.bankcards.service.user.UserService;
import com.example.bankcards.exception.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/users")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(
            summary = "Getting all users",
            description = "Allows to get all users, which the bank has"
    )
    public ResponseEntity<List<User>> getAllUsers() throws EntityNotFoundException {
        List<User> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Getting user by id",
            description = "Allows to get card by user's id"
    )
    public ResponseEntity<User> getUserById(@PathVariable int id) throws EntityNotFoundException {
        User user = userService.findById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Create user",
            description = "Allows to create new user"
    )
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) throws EntityNotFoundException {
        userService.saveUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Updating user by id",
            description = "Allows to update user's data by book's id"
    )
    public ResponseEntity<HttpStatus> updateUserById(@PathVariable int id, @RequestBody User user) throws EntityNotFoundException {
        userService.updateUserById(id, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

/*    @GetMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable int id) throws EntityNotFoundException {
        //TO DO....
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}
