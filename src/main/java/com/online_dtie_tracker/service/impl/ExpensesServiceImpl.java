package com.online_dtie_tracker.service.impl;

import com.online_dtie_tracker.Dto.ExpensesDto;
import com.online_dtie_tracker.Dto.IncomeDto;
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
import java.util.ArrayList;
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

        for (Integer i = 0; i < expensesDto.getIncomeList().size(); i++) {
            Integer incomeId = expensesDto.getIncomeList().get(i).getId();

            IncomeDto incomeDto = incomeService.findById(incomeId);

            Double incomeAmount = expensesDto.getIncomeList().get(i).getAmount();


            //paid amount is less that selected income source then
            if (amountToBePaid < incomeAmount) {
                //update income current Amount
                incomeDto.setAmount(incomeAmount - amountToBePaid);
                amountToBePaid = 0D;

            } else if (amountToBePaid > incomeAmount) {
                //update income current Amount
                incomeDto.setAmount(0D);

                //decrease amount of paid
                amountToBePaid = amountToBePaid - incomeAmount;

            } else if (amountToBePaid.equals(incomeAmount)) {
                incomeDto.setAmount(0D);
                amountToBePaid = 0D;
            }
            incomeService.save(incomeDto);
        }

        //return expensesDto with id
        return ExpensesDto.builder().id(expenses1.getId()).build();
    }

    @Override
    public List<ExpensesDto> findAll() {
        //return list of ExpensesDto form database
        List<ExpensesDto> expensesDtoList = new ArrayList<>();
        //get expenses list of authorized user
        List<Expenses> expensesList = expensesRepo.getAllExpensesList(AuthorizedUser.getUser().getId());

        for (Expenses expenses : expensesList){
            expensesDtoList.add(ExpensesDto.builder()
                    .id(expenses.getId())
                    .expensesSource(expenses.getExpenses_source())
                    .expensesAmount(expenses.getExpensesAmount())
                    .paidDate(expenses.getPaidDate())
                    .incomeList(expenses.getIncomeList()).build());
        }
        return expensesDtoList;
    }

    @Override
    public ExpensesDto findById(Integer integer) throws IOException, ParseException {
        //find Expenses by id
        Expenses expenses = expensesRepo.findById(integer).get();
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

    public Double getTotalExpenses() {
        //total of current user
        List<Expenses> expensesList = expensesRepo.getAllExpensesList(AuthorizedUser.getUser().getId());
        //add all expenses amount
        Double expensesAmount = 0D;
        for (Expenses expenses : expensesList) {
            //add all expenses amount
            expensesAmount += expenses.getExpensesAmount();
        }
        return Double.valueOf(df.format(expensesAmount));
    }


    //check this expenses can paid by the selected list of income
    public Boolean canPaidThatExpenses(ExpensesDto expensesDto) {
        //expenses amount
        Double amountToBePaid = expensesDto.getExpensesAmount();

        //total income amount
        Double incomeAmount = 0D;
        //calculate the total money of selected income lsit
        for (Income income : expensesDto.getIncomeList()) {
            //add income current amount
            incomeAmount += income.getAmount();
        }
        if (amountToBePaid > incomeAmount) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean isSelectedUnnecessarySource(ExpensesDto expensesDto){
        Boolean isSelected = false;

        //expenses amount
        Double amountToBePaid = expensesDto.getExpensesAmount();
        //total income amount
        Double incomeAmount = 0D;
        if (expensesDto.getIncomeList().size() > 1){
            Integer index = 0;
            for (Integer i =0;i<expensesDto.getIncomeList().size();i++) {
                incomeAmount += expensesDto.getIncomeList().get(i).getAmount();

                /*
                if user select multiple income source but
                 that expenses can be paid firs income source
                 // if 2 income list
                      expenes > incomeOne && expneses <incomeOne+incomeTwo
                 */
              if (i+1 !=expensesDto.getIncomeList().size()){
                  if (!(amountToBePaid > incomeAmount && amountToBePaid <
                          (incomeAmount + expensesDto.getIncomeList().get(i+1).getAmount()))){

                      isSelected = true;
                  }

                  }
              }
        }
        return isSelected;
    }

}

