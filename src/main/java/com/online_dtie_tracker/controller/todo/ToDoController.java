package com.online_dtie_tracker.controller.todo;

import com.online_dtie_tracker.Dto.ToDoDto;
import com.online_dtie_tracker.authorizeduser.UserTask;
import com.online_dtie_tracker.service.impl.ToDoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoServiceImpl toDoService;

    public ToDoController(ToDoServiceImpl toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/home")
    public String getToDoHomePage(Model model){
        model.addAttribute("todo",toDoService.findAllTodayTask());
        return "todo/todohomepage";
    }

    @GetMapping("/add")
    public String getToDoAddPage(Model model){
        model.addAttribute("toDoDto",new ToDoDto());
        return "todo/todoaddpage";
    }

    @PostMapping("/add")
    public String getAddToDo(@Valid @ModelAttribute("toDoDto") ToDoDto toDoDto,
                             BindingResult bindingResult,Model model) throws IOException, ParseException {
        if (!bindingResult.hasErrors()){
            //save into database
           ToDoDto toDoDto1 = toDoService.save(toDoDto);
           if (toDoDto1 !=null){
               model.addAttribute("message",
                       "Task Added successfully");
           }
        }else {
            model.addAttribute("toDoDto",toDoDto);
            return "todo/todoaddpage";
        }
        return "redirect:/todo/home";
    }


    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable Integer id,Model model) throws IOException, ParseException {
        model.addAttribute("toDoDto",toDoService.findById(id));
        return "todo/todoaddpage";
    }

    // for view yesterday todo details
    @GetMapping("/previous")
    public String getPreviousTask(Model model){
        model.addAttribute("todo",toDoService.findAllYesterdayTask());
        return "todo/viewprevioustodo";
    }

    //for views overAll todo details and report
    @GetMapping("/report")
    public String getReportPage(Model model){
        List<ToDoDto> toDoDtoList = toDoService.findAll();
        model.addAttribute("todo",toDoDtoList);

        //set total toDo task
        UserTask.setTotalTask(toDoDtoList.size());

        //getPercentage of done task
        model.addAttribute("doneTaskPercentage",
                toDoService.getPercentageOfDoneTask());

        //getPercentage of pending task
        model.addAttribute("pendingTaskPercentage",
               100 - Double.valueOf(toDoService.getPercentageOfDoneTask()));
        return "todo/todoreport";
    }
}
