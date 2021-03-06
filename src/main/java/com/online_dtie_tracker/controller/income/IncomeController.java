package com.online_dtie_tracker.controller.income;

import com.online_dtie_tracker.Dto.IncomeDto;
import com.online_dtie_tracker.excelgenerate.income.IncomeDataExcelExport;
import com.online_dtie_tracker.service.impl.ExpensesServiceImpl;
import com.online_dtie_tracker.service.impl.FinancialInformation;
import com.online_dtie_tracker.service.impl.IncomeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/income")
public class IncomeController {
    private final IncomeServiceImpl incomeService;
    private final FinancialInformation financialInformation;
    private final ExpensesServiceImpl expensesService;

    public IncomeController(IncomeServiceImpl incomeService, FinancialInformation financialInformation, ExpensesServiceImpl expensesService) {
        this.incomeService = incomeService;
        this.financialInformation = financialInformation;
        this.expensesService = expensesService;
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
       //get details of particular income
        model.addAttribute("incomeDto",incomeService.findById(id));

        //get all expenses list that is paid by that income source
        model.addAttribute("expensesList",incomeService.findById(id).getExpensesList());

        return "income/viewincomepage";
    }

    @GetMapping("/report")
    public String getReportPage(Model model){
        //data for financial information
        model.addAttribute("chartData", financialInformation.getChartData());

        //for display current balance
        model.addAttribute("currentBalance",financialInformation.currentBalance());

        //add total income list
        model.addAttribute("incomeList",incomeService.findAll());

        //add all expenses list
        model.addAttribute("expensesList",expensesService.findAll());

        return "income/incomereport";
    }

    @GetMapping("/excel/export")
    public ModelAndView exportIncomeDataIntoExcel(){
        ModelAndView modelAndView = new ModelAndView();
        //set view
        modelAndView.setView(new IncomeDataExcelExport());

        //read the data from database
        List<IncomeDto> incomeDtoList = incomeService.findAll();

        //send that list to excelImpl class
        modelAndView.addObject("list",incomeDtoList);

        return modelAndView;
    }
}
