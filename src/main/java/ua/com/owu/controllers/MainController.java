package ua.com.owu.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import ua.com.owu.entity.User;

@Controller
public class MainController {

    private CustomValidator customValidator;

    @GetMapping("/")
    public String home(@RequestBody @Validated User user, Errors errors, Model model) {
//        model.addAttribute("msg", "okten");
        System.out.println(errors.getAllErrors());
        return "index";
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(customValidator);
    }

}


@Component
class CustomValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (user.getId() < 0) {
            errors.rejectValue("id", "negative.id");
            throw new RuntimeException();
        }
    }
}

