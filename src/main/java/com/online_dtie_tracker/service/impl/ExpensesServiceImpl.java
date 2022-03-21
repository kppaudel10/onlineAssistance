package com.online_dtie_tracker.service.impl;

import com.online_dtie_tracker.Dto.ExpensesDto;
import com.online_dtie_tracker.conversion.DtoModelConvert;
import com.online_dtie_tracker.model.Expenses;
import com.online_dtie_tracker.repo.expenses.ExpensesRepo;
import com.online_dtie_tracker.service.expenses.ExpensesService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpensesServiceImpl implements ExpensesService {
    private final ExpensesRepo expensesRepo;

    public ExpensesServiceImpl(ExpensesRepo expensesRepo) {
        this.expensesRepo = expensesRepo;
    }

    @Override
    public ExpensesDto save(ExpensesDto expensesDto) throws IOException, ParseException {
        //convert dto into entity to store in database
        Expenses expenses = new DtoModelConvert().getExpenses(expensesDto);

        //now save into database using repo
       Expenses expenses1 = expensesRepo.save(expenses);

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
}
