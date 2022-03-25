package com.online_dtie_tracker.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialInformation {
    private final IncomeServiceImpl incomeService;
    private final ExpensesServiceImpl expensesService;

    public FinancialInformation(IncomeServiceImpl incomeService, ExpensesServiceImpl expensesService) {
        this.incomeService = incomeService;
        this.expensesService = expensesService;
    }

//      return the list of pending and done task details
    public List<List<Object>> getChartData() {
        return List.of(
                List.of("Income",incomeService.getTotalIncome()),
                List.of("Expenses",expensesService.getTotalExpenses())
        );
    }

    //get the current balance after reduce expenses
    public Double currentBalance(){
        return incomeService.getTotalIncome() - expensesService.getTotalExpenses();
    }
}
