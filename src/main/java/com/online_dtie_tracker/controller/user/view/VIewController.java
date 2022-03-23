package com.online_dtie_tracker.controller.user.view;

import com.online_dtie_tracker.Dto.ToDoDto;
import com.online_dtie_tracker.authorizeduser.AuthorizedUser;
import com.online_dtie_tracker.authorizeduser.UserTask;
import com.online_dtie_tracker.service.impl.ToDoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class VIewController {
    //get todoService to get task condition
    private final ToDoServiceImpl toDoService;

    public VIewController(ToDoServiceImpl toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/view")
    public String getUserViewPage(Model model){
        model.addAttribute("user", AuthorizedUser.getUser());
        List<ToDoDto> toDoDtoList = toDoService.findAll();

        //set total toDo task
        UserTask.setTotalTask(toDoDtoList.size());

        //getPercentage of done task
        model.addAttribute("doneTaskPercentage",
                toDoService.getPercentageOfDoneTask());

        //getPercentage of pending task
        model.addAttribute("pendingTaskPercentage",
                100 - Double.valueOf(toDoService.getPercentageOfDoneTask()));
        return "user/userviewpage";
    }
}
