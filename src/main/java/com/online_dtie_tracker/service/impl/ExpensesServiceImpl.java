package com.online_dtie_tracker.service.impl;

import com.online_dtie_tracker.Dto.ExpensesDto;
import com.online_dtie_tracker.authorizeduser.AuthorizedUser;
import com.online_dtie_tracker.conversion.DtoModelConvert;
import com.online_dtie_tracker.model.Expenses;
import com.online_dtie_tracker.model.Income;
import com.online_dtie_tracker.repo.expenses.ExpensesRepo;
import com.online_dtie_tracker.service.expenses.ExpensesService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpensesServiceImpl implements ExpensesService {
    //get double value formatter
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private final IncomeServiceImpl incomeService;
    private final ExpensesRepo expensesRepo;

    public ExpensesServiceImpl(IncomeServiceImpl incomeService, ExpensesRepo expensesRepo) {
        this.incomeService = incomeService;
        this.expensesRepo = expensesRepo;
    }

    @Override
    public ExpensesDto save(ExpensesDto expensesDto) throws IOException, ParseException {
        //convert dto into entity to store in database
        Expenses expenses = new DtoModelConvert().getExpenses(expensesDto);

        //now save into database using repo
       Expenses expenses1 = expensesRepo.save(expenses);
        //amount need to be paid
        Double amountToBePaid = expensesDto.getExpensesAmount();

        for (Integer i =0;i<expensesDto.getIncomeList().size();i++){

            Double incomeAmount = expensesDto.getIncomeList().get(i).getAmount();

            Integer incomeId = expensesDto.getIncomeList().get(i).getId();

            //paid amount is less that selected income source then
            if (amountToBePaid < incomeAmount){
                //update income current Amount
                incomeService.updateAmount(incomeAmount - amountToBePaid,incomeId);
                amountToBePaid = 0D;
                break;
            }else if(amountToBePaid > incomeAmount){
                //update income current Amount
                incomeService.updateAmount(0.0,incomeId);

                //decrease amount of paid
                amountToBePaid = amountToBePaid - incomeAmount;

            }
        }

       //return expensesDto with id
        return ExpensesDto.builder().id(expenses1.getId()).build();
    }

    @Override
    public List<ExpensesDto> findAll() {
        //return list of ExpensesDto form database
        return expensesRepo.findAll().stream().map(expenses -> {
            return ExpensesDto.builder()
                    .id(expenses.getId())
                    .expensesSource(expenses.getExpenses_source())
                    .expensesAmount(expenses.getExpensesAmount())
                    .paidDate(expenses.getPaidDate())
                    .incomeList(expenses.getIncomeList()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public ExpensesDto findById(Integer integer) throws IOException, ParseException {
        //find Expenses by id
      Expenses expenses =  expensesRepo.findById(integer).get();
        return ExpensesDto.builder()
                .id(expenses.getId())
                .expensesSource(expenses.getExpenses_source())
                .expensesAmount(expenses.getExpensesAmount())
                .paidDate(expenses.getPaidDate())
                .incomeList(expenses.getIncomeList()).build();
    }

    @Override
    public void deleteById(Integer integer) throws IOException {

    }

    @Override
    public void update(ExpensesDto expensesDto) throws IOException, ParseException {

    }

    public Double getTotalExpenses(){
        //total of current user
       List<Expenses> expensesList = expensesRepo.getAllExpensesList(AuthorizedUser.getUser().getId());
        //add all expenses amount
        Double expensesAmount = 0D;
        for (Expenses expenses:expensesList){
            //add all expenses amount
            expensesAmount += expenses.getExpensesAmount();
        }
        return Double.valueOf(df.format(expensesAmount));
    }
}
