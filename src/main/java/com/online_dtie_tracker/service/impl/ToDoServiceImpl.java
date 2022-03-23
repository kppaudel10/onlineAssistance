package com.online_dtie_tracker.service.impl;

import com.online_dtie_tracker.Dto.ToDoDto;
import com.online_dtie_tracker.authorizeduser.AuthorizedUser;
import com.online_dtie_tracker.authorizeduser.UserTask;
import com.online_dtie_tracker.conversion.DtoModelConvert;
import com.online_dtie_tracker.model.ToDo;
import com.online_dtie_tracker.repo.todo.ToDoRepo;
import com.online_dtie_tracker.service.todo.ToDoService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {
    private static final DecimalFormat df = new DecimalFormat("0.00");
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
        List<ToDoDto> toDoDtoList = new ArrayList<>();

        //get all task of authorized user
        List<ToDo> toDoList = toDoRepo.findAllTaskOfAuthorizedUser(AuthorizedUser.getUser().getId());
        for (ToDo toDo : toDoList) {
            //add into list
            toDoDtoList.add(ToDoDto.builder().id(toDo.getId())
                    .title(toDo.getTitle())
                    .toDoDate(toDo.getToDoDate())
                    .toDoStatus(toDo.getToDoStatus())
                    .user(toDo.getUser()).build());
        }
        return toDoDtoList;
    }

    @Override
    public ToDoDto findById(Integer integer) throws IOException, ParseException {
        //find by id using repo
        ToDo toDo = toDoRepo.findById(integer).get();

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

    //this method return all today task
    public List<ToDo> findAllTodayTask() {
        List<ToDo> toDoList = toDoRepo.getTaskByDate(LocalDate.now(), AuthorizedUser.getUser().getId());

        //find if there any previous pending task
        List<ToDo> previousPendingTask = toDoRepo.getPreviousPendingTask
                (LocalDate.now(), AuthorizedUser.getUser().getId());

        if (!previousPendingTask.isEmpty()) {
            for (Integer i = 0; i < previousPendingTask.size(); i++) {
                //add into today task
                toDoList.add(i, previousPendingTask.get(i));
            }
        }
        return toDoList;
    }

    //find yesterday task

    public List<ToDo> findAllYesterdayTask() {
        //get today date
        LocalDate localDate = LocalDate.now();

        //get yesterday
        LocalDate previousDate = localDate.minusDays(1);

        return toDoRepo.getTaskByDate(previousDate, AuthorizedUser.getUser().getId());
    }

    //this return how many task has been complete
    public String getPercentageOfDoneTask() {
        Double doneTask = Double.valueOf(toDoRepo.getDoneTask(AuthorizedUser.getUser().getId()).size());

        //find how much task complete
        Double doneTaskPer = Double.valueOf((doneTask * 100) / UserTask.getTotalTask());

        return df.format(doneTaskPer);
    }
}
