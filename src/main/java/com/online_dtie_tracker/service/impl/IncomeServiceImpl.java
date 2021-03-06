package com.online_dtie_tracker.service.impl;

import com.online_dtie_tracker.Dto.IncomeDto;
import com.online_dtie_tracker.authorizeduser.AuthorizedUser;
import com.online_dtie_tracker.conversion.DtoModelConvert;
import com.online_dtie_tracker.model.Income;
import com.online_dtie_tracker.repo.income.IncomeRepo;
import com.online_dtie_tracker.service.income.IncomeService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {
    //get double value formatter
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private final IncomeRepo incomeRepo;

    public IncomeServiceImpl(IncomeRepo incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    @Override
    public IncomeDto save(IncomeDto incomeDto) throws IOException, ParseException {
        //first convert IncomeDto into Income Entity
        Income income = new DtoModelConvert().getIncome(incomeDto);
        income.setFixedAmount(incomeDto.getAmount());
        //save into database
      Income income1 =  incomeRepo.save(income);

      //return IncomeDto with id
        return IncomeDto.builder().id(income1.getId()).build();
    }

    @Override
    public List<IncomeDto> findAll() {
        List<IncomeDto> incomeDtoList =new ArrayList<>();
        List<Income> incomeList =incomeRepo.getAllIncomeList(AuthorizedUser.getUser().getId());
        for (Income income:incomeList){
            incomeDtoList.add(
                    IncomeDto.builder()
                            .id(income.getId())
                    .source(income.getSource()).fixedAmount(income.getFixedAmount())
                    .amount(income.getAmount())
                    .incomeDate(income.getIncomeDate())
                    .expensesList(income.getExpensesList()).build()
            );
        }
        return incomeDtoList;
    }

    @Override
    public IncomeDto findById(Integer integer) throws IOException, ParseException {
        //find by id
        Income income = incomeRepo.findById(integer).get();

        //return IncomeDto by making it
        return IncomeDto.builder()
                .id(income.getId())
                .source(income.getSource())
                .amount(income.getAmount())
                .fixedAmount(income.getFixedAmount())
                .incomeDate(income.getIncomeDate())
                .expensesList(income.getExpensesList()).build();
    }

    @Override
    public void deleteById(Integer integer) throws IOException {

    }

    @Override
    public void update(IncomeDto incomeDto) throws IOException, ParseException {

    }

    //this method return if income amount is more than 0
    public List<Income> getAllIncomeListMoreThenZeroAmount(){
        return incomeRepo.getAllIncomeListAmountMoreThanZero(AuthorizedUser.getUser().getId());
    }


    public void updateAmount(Double amount ,Integer id){
        incomeRepo.updateAmount(amount,id);
    }

    public Double getTotalIncome(){
        List<Income> incomeList = incomeRepo.getAllIncomeListAmountMoreThanZero(AuthorizedUser.getUser().getId());

        Double incomeAmount = 0D;
        //add each income details
        for (Income income: incomeList){
            incomeAmount += income.getFixedAmount();
        }
        return Double.valueOf(df.format(incomeAmount));
    }

}
