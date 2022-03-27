package com.online_dtie_tracker.controller.todo;

import com.online_dtie_tracker.Dto.ExpensesDto;
import com.online_dtie_tracker.Dto.SearchDto;
import com.online_dtie_tracker.Dto.ToDoDto;
import com.online_dtie_tracker.authorizeduser.UserTask;
import com.online_dtie_tracker.excelgenerate.expenses.ExpensesDataExcelExport;
import com.online_dtie_tracker.excelgenerate.todo.ToDoDataExcelExport;
import com.online_dtie_tracker.service.impl.ToDoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class ToDoController {
    //get double value formatter
    private static final DecimalFormat df = new DecimalFormat("0.00");
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
            //go to same page with same dto
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
        model.addAttribute("searchDto",new SearchDto());
        return "todo/viewprevioustodo";
    }

    //for views overAll todo details and report
    @GetMapping("/report")
    public String getReportPage(Model model){
        List<ToDoDto> toDoDtoList = toDoService.findAll();
        model.addAttribute("todo",toDoDtoList);

        //set total toDo task
        UserTask.setTotalTask(toDoDtoList.size());

        model.addAttribute("chartData", toDoService.getChartData());

        //add todo details
        model.addAttribute("todo",toDoService.findAll());
        return "todo/piechart";
    }


    @PostMapping("/search")
    public String getSearchPage(@ModelAttribute("searchDto")SearchDto searchDto, Model model) throws ParseException {
       //find task by date and go to view page
        model.addAttribute("todo",toDoService.findToDoListByDate(searchDto.getDate()));

        return "todo/viewprevioustodo";
    }

    @GetMapping("/excel/export")
    public ModelAndView exportIncomeDataIntoExcel(){
        ModelAndView modelAndView = new ModelAndView();
        //set view
        modelAndView.setView(new ToDoDataExcelExport());

        //read the data from database
        List<ToDoDto> toDoDtoList = toDoService.findAll();

        //send that list to excelImpl class
        modelAndView.addObject("list",toDoDtoList);

        return modelAndView;
    }

}
