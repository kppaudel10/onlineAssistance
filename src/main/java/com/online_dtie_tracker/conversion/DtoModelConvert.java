package com.online_dtie_tracker.conversion;

import com.online_dtie_tracker.Dto.ExpensesDto;
import com.online_dtie_tracker.Dto.IncomeDto;
import com.online_dtie_tracker.Dto.ToDoDto;
import com.online_dtie_tracker.Dto.UserDto;
import com.online_dtie_tracker.model.Expenses;
import com.online_dtie_tracker.model.Income;
import com.online_dtie_tracker.model.ToDo;
import com.online_dtie_tracker.model.User;

public class DtoModelConvert {
    /**
     * @param userDto
     * @return User
     */
    //this method convert UserDto into User Model
    public User getUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .address(userDto.getAddress())
                .contact(userDto.getContact())
                .email(userDto.getEmail()).build();
    }

    /**
     * @param incomeDto
     * @return income
     */

    //This method convert IncomeDto into Income
    public Income getIncome(IncomeDto incomeDto) {
        return Income.builder()
                .id(incomeDto.getId())
                .amount(incomeDto.getAmount())
                .source(incomeDto.getSource())
                .user(incomeDto.getUser())
//                .expensesList(incomeDto.getExpensesDtoList())
                .build();
    }

    /**
     * @param expensesDto
     * @return expenses
     */
    //This method convert ExpensesDto into Expenses Model
    public Expenses getExpenses(ExpensesDto expensesDto) {
        return Expenses.builder()
                .id(expensesDto.getId())
                .expenses_source(expensesDto.getExpensesSource())
                .expensesAmount(expensesDto.getExpensesAmount())
                .paidDate(expensesDto.getPaidDate()).build();
    }

    /**
     * @param toDoDto
     * @return toDo
     */
    //This method convert ToDoDto into ToDo Model
    public ToDo getToDo(ToDoDto toDoDto) {
        return ToDo.builder()
                .id(toDoDto.getId())
                .title(toDoDto.getTitle())
                .toDoStatus(toDoDto.getToDoStatus())
                .toDoDate(toDoDto.getToDoDate()).build();
    }
}
