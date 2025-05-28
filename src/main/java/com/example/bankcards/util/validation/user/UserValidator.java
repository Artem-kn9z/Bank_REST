package com.example.bankcards.util.validation.user;

import com.example.bankcards.entity.user.User;
import com.example.bankcards.exception.EntityNotFoundException;
import com.example.bankcards.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) { this.userService = userService; }

    @Override
    public boolean supports(Class<?> clazz) { return User.class.equals(clazz); }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        try{
            if(userService.findByEmail(user.getEmail()) != null){
                errors.rejectValue("email", null, "This email address is already in use");
            }
        } catch(EntityNotFoundException e){
            //TO DO: add log here
        }
    }
}
