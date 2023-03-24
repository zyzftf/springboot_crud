package com.example.school.Controller;

import com.example.school.Dto.UserQueryParams;
import com.example.school.Dto.UserRequest;
import com.example.school.Model.User;
import com.example.school.Service.UserService;
import com.example.school.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Validated
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String Home(){
        return "index";
    }

    @GetMapping("/Users")     //  取得所有使用者資料, 並分頁顯示
    public String getUsers(Model model, RedirectAttributes redirectAttributes,
                           @RequestParam(required = false) String search,
                           @RequestParam(defaultValue = "1") String page,
                           @RequestParam(defaultValue = "10") @Min(0) @Max(1000) Integer pageSize){

        System.out.println("search = "+search+", page = "+page+", pageSize = "+pageSize);
        try {
            //  當前頁碼
            int currentPage = Integer.parseInt(page);

            //  取得符合條件的用戶數量
            Integer totalUsers = userService.countUser(search);

            //  頁碼總數量
            int totalPages = (int) Math.ceil((double)totalUsers / pageSize);
            System.out.println("符合條件的用戶數量: "+totalUsers+", 頁碼總數量: "+totalPages+"當前頁碼: "+currentPage);

            //  如果頁碼不正確, 則進行重定向, 顯示正確的url路徑
            if (currentPage < 1 || currentPage > totalPages) {
                redirectAttributes.addAttribute("page", "1");
                redirectAttributes.addAttribute("pageSize", "10");
                redirectAttributes.addAttribute("search", search);
                return "redirect:/Users";
            }

            int offset = (currentPage-1) * pageSize;
            UserQueryParams userQueryParams = new UserQueryParams();
            userQueryParams.setLimit(pageSize);
            userQueryParams.setOffset(offset);
            userQueryParams.setSearch(search);

            //  取得當前頁用戶數據
            List<User> userList = userService.getUsers(userQueryParams);

            //  分頁
            Page<User> userPage = new Page<>();
            userPage.setResult(userList);

            List<Integer> options = new ArrayList<>();
            options.add(5);
            options.add(10);
            options.add(20);

            model.addAttribute("options", options);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalUsers", totalUsers);
            model.addAttribute("search", search);
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("userPage", userPage);
        }catch (NumberFormatException e){
            redirectAttributes.addAttribute("page", "1");
            return "redirect:/Users";
        }
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

    @GetMapping ("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Integer userId){     //  刪除用戶
        userService.deleteUserById(userId);
        System.out.println("delete!!!");
        return "redirect:/Users";
    }
}
