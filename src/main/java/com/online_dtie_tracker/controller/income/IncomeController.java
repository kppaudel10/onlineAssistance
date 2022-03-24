package com.online_dtie_tracker.controller.income;

import com.online_dtie_tracker.Dto.IncomeDto;
import com.online_dtie_tracker.service.impl.IncomeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/income")
public class IncomeController {
    private final IncomeServiceImpl incomeService;

    public IncomeController(IncomeServiceImpl incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/home")
    public String getIncomeHomePage(Model model){
        model.addAttribute("incomeDto",incomeService.findAll());
        return "income/incomehomepage";
    }

    @GetMapping("/add")
    public String getIncomeAddPage(Model model){
        model.addAttribute("incomeDto",new IncomeDto());
        return "income/incomeaddpage";
    }

    @PostMapping("/add")
    public String addIncome(@Valid @ModelAttribute("incomeDto")IncomeDto incomeDto,
                            BindingResult bindingResult,Model model) throws IOException, ParseException {
        if (!bindingResult.hasErrors()){
            //save into database
           IncomeDto incomeDto1 = incomeService.save(incomeDto);
           //check saved or not
            if (incomeDto1 !=null){
                model.addAttribute("message","Income added successfully");
            }else {
                model.addAttribute("message","Failed to add income details");

            }
        }
        model.addAttribute("incomeDto",incomeDto);
        return "income/incomeaddpage";
    }

    @GetMapping("/view/{id}")
    public String getViewIncomePage(@PathVariable Integer id,Model model) throws IOException, ParseException {
        model.addAttribute("incomeDto",incomeService.findById(id));
        model.addAttribute("expensesList",incomeService.findById(id).getExpensesList());
        return "income/viewincomepage";
    }
}
