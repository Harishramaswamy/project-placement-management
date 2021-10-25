package Controller;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Entity.UserData;
import Service.UserAlreadyExistException;

@Controller
public class RegistrationController {
    private static final String REDIRECT = null;
	@Autowired
     private userService userservice;

    @GetMapping("/register")
    public String register(final Model model){
        model.addAttribute("userData", new UserData());
        return "account/register";
    }

    @PostMapping("/register")
    public String userRegistration(final @Validated  UserData userData, final BindingResult bindingResult, final Model model) throws UserAlreadyExistException{
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", userData);
            return "account/register";
        }
        userService.register(userData);
        String starter;
		return REDIRECT+" /starter";
    }

}
