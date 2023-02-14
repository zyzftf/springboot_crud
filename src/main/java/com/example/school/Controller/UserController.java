package com.example.school.Controller;

import com.example.school.Dto.UserRequest;
import com.example.school.Model.User;
import com.example.school.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String Home(){
        return "index";
    }

    @GetMapping("/Users")
    public String getUsers(Model model){         //  取得所有使用者資料

        //  取得使用者數據
        List<User> userList = userService.getUsers();

        model.addAttribute("list", userList);

        return "users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model){        //    添加新用戶
        UserRequest userRequest = new UserRequest();
        model.addAttribute("user", userRequest);

        System.out.println("add User");
        return "add";
    }

    @PostMapping("/createUser")
    public String createUser(@Valid @ModelAttribute("user") UserRequest userRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("add error: " + userRequest.toString());
            return "add";
        }

        Integer userId = userService.createUser(userRequest);
        System.out.println("create User");
        return "redirect:/Users";
    }

    @GetMapping("/editUser/{userId}")
    public ModelAndView editUser(@PathVariable Integer userId){      //  修改用戶
        User user = userService.getUserById(userId);
        System.out.println(user.toString());

        ModelAndView mav = new ModelAndView("EditUser");
        mav.addObject( "editUser", user);

        return mav;
    }

    @PostMapping("/updateUser")   //  當使用者在前端按下確認修改按鈕, 就會進入到這個方法裡面
    public String updateUser(@Valid @ModelAttribute("editUser") User user, BindingResult bindingResult){

        //  使用 BindingResult 來捕捉前端表單的錯誤
        if(bindingResult.hasErrors()){
            System.out.println("update error: "+user.toString());
            return "EditUser";
        }

        System.out.println(user.toString());
        userService.updateUser(user);
        return "redirect:/Users";
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Integer userId){     //  刪除用戶
        userService.deleteUserById(userId);
        System.out.println("delete!!!");
        return "redirect:/Users";
    }
}
