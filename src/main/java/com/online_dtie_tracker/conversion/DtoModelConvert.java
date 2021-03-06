package com.online_dtie_tracker.conversion;

import com.online_dtie_tracker.Dto.ExpensesDto;
import com.online_dtie_tracker.Dto.IncomeDto;
import com.online_dtie_tracker.Dto.ToDoDto;
import com.online_dtie_tracker.Dto.UserDto;
import com.online_dtie_tracker.authorizeduser.AuthorizedUser;
import com.online_dtie_tracker.model.Expenses;
import com.online_dtie_tracker.model.Income;
import com.online_dtie_tracker.model.ToDo;
import com.online_dtie_tracker.model.User;

import java.util.Date;

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
                .password(userDto.getPassword())
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
                .fixedAmount(incomeDto.getFixedAmount())
                .amount(incomeDto.getAmount())
                .source(incomeDto.getSource())
                .incomeDate(new Date())
                .user(AuthorizedUser.getUser())
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
                .incomeList(expensesDto.getIncomeList())
                .user(AuthorizedUser.getUser())
                .paidDate(new Date()).build();
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
                .toDoDate(new Date())
                .user(AuthorizedUser.getUser()).build();
    }

}
