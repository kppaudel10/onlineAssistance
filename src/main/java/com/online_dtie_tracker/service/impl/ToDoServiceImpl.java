package com.online_dtie_tracker.service.impl;

import com.online_dtie_tracker.Dto.ToDoDto;
import com.online_dtie_tracker.conversion.DtoModelConvert;
import com.online_dtie_tracker.model.ToDo;
import com.online_dtie_tracker.repo.todo.ToDoRepo;
import com.online_dtie_tracker.service.todo.ToDoService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {
    //get toDo repo
    private final ToDoRepo toDoRepo;

    public ToDoServiceImpl(ToDoRepo toDoRepo) {
        this.toDoRepo = toDoRepo;
    }

    @Override
    public ToDoDto save(ToDoDto toDoDto) throws IOException, ParseException {
        //convert Dto into Model
        ToDo toDo = new DtoModelConvert().getToDo(toDoDto);

        //save into database
       ToDo toDo1 = toDoRepo.save(toDo);

       //return toDoDto with id
        return ToDoDto.builder().id(toDo.getId()).build();
    }

    @Override
    public List<ToDoDto> findAll() {
        return toDoRepo.findAll().stream().map(toDo -> {
            return ToDoDto.builder()
                    .id(toDo.getId())
                    .title(toDo.getTitle())
                    .toDoDate(toDo.getToDoDate())
                    .toDoStatus(toDo.getToDoStatus())
                    .user(toDo.getUser()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public ToDoDto findById(Integer integer) throws IOException, ParseException {
        //find by id using repo
      ToDo toDo =  toDoRepo.findById(integer).get();

      //return by making it's Dto
        return ToDoDto.builder()
                .id(toDo.getId())
                .title(toDo.getTitle())
                .toDoDate(toDo.getToDoDate())
                .toDoStatus(toDo.getToDoStatus())
                .user(toDo.getUser()).build();
    }

    @Override
    public void deleteById(Integer integer) throws IOException {

    }

    @Override
    public void update(ToDoDto toDoDto) throws IOException, ParseException {

    }
}
