package com.online_dtie_tracker.service.impl;

import com.online_dtie_tracker.Dto.ToDoDto;
import com.online_dtie_tracker.service.todo.ToDoService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {
    @Override
    public ToDoDto save(ToDoDto toDoDto) throws IOException, ParseException {
        return null;
    }

    @Override
    public List<ToDoDto> findAll() {
        return null;
    }

    @Override
    public ToDoDto findById(Integer integer) throws IOException, ParseException {
        return null;
    }

    @Override
    public void deleteById(Integer integer) throws IOException {

    }

    @Override
    public void update(ToDoDto toDoDto) throws IOException, ParseException {

    }
}
