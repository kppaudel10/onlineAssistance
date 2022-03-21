package com.online_dtie_tracker.service.impl;

import com.online_dtie_tracker.Dto.ExpensesDto;
import com.online_dtie_tracker.service.expenses.ExpensesService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class ExpensesServiceImpl implements ExpensesService {
    @Override
    public ExpensesDto save(ExpensesDto expensesDto) throws IOException, ParseException {
        return null;
    }

    @Override
    public List<ExpensesDto> findAll() {
        return null;
    }

    @Override
    public ExpensesDto findById(Integer integer) throws IOException, ParseException {
        return null;
    }

    @Override
    public void deleteById(Integer integer) throws IOException {

    }

    @Override
    public void update(ExpensesDto expensesDto) throws IOException, ParseException {

    }
}
