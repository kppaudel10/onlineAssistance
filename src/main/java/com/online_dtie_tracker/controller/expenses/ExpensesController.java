package com.online_dtie_tracker.controller.expenses;

import com.online_dtie_tracker.Dto.ExpensesDto;
import com.online_dtie_tracker.service.impl.ExpensesServiceImpl;
import com.online_dtie_tracker.service.impl.IncomeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/expenses")
public class ExpensesController {
    private final IncomeServiceImpl incomeService;
    private final ExpensesServiceImpl expensesService;

    public ExpensesController(IncomeServiceImpl incomeService, ExpensesServiceImpl expensesService) {
        this.incomeService = incomeService;
        this.expensesService = expensesService;
    }

    //this method access home page of expenses
    @GetMapping("/home")
    public String getExpensesHomePage(Model model){
        model.addAttribute("expensesList",expensesService.findAll());
        return "expenses/expenseshomepage";
    }

    @GetMapping("/add")
    public String getExpensesAddPage(Model model){
        model.addAttribute("incomeList",incomeService.getAllIncomeListMoreThenZeroAmount());
        model.addAttribute("expensesDto",new ExpensesDto());
        return "expenses/expensesaddpage";
    }

    @PostMapping("/add")
    public String getAddExpenses(@Valid @ModelAttribute("expensesDto")ExpensesDto expensesDto,
                                 BindingResult bindingResult,Model model) throws IOException, ParseException {
        if (!bindingResult.hasErrors()){
            //check can paid or not
            Boolean canPaid = expensesService.canPaidThatExpenses(expensesDto);

          if (canPaid){
              //if user not select unnecessary income source
             if (! expensesService.isSelectedUnnecessarySource(expensesDto)){
                 //save into database
                 ExpensesDto expensesDto1 =  expensesService.save(expensesDto);
                 if (expensesDto1 !=null){
                     model.addAttribute("message","Expenses added successfully");
                 }else {

                     model.addAttribute("message","Unable to add expenses details");
                 }
             }else {
                 model.addAttribute("message","you are selected unnecessary income source");
             }
          }
          else {
              model.addAttribute("message","Insufficient income source");
          }
        }
        //go to the same page
        model.addAttribute("incomeList",incomeService.getAllIncomeListMoreThenZeroAmount());
        model.addAttribute("expensesDto",expensesDto);
        return "expenses/expensesaddpage";
    }

    @GetMapping("/view/{id}")
    public String getViewPage(@PathVariable("id")Integer integer,Model model) throws IOException, ParseException {
        //details about particular expenses
        ExpensesDto expensesDto = expensesService.findById(integer);

        model.addAttribute("expensesDto",expensesDto);

        //get details of income of particular expenses
        model.addAttribute("incomeList",expensesDto.getIncomeList());
        return "expenses/expensesviewpage";
    }
}
