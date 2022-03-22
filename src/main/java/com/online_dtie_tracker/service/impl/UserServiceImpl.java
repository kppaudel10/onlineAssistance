package com.online_dtie_tracker.service.impl;

import com.online_dtie_tracker.Dto.UserDto;
import com.online_dtie_tracker.conversion.DtoModelConvert;
import com.online_dtie_tracker.model.User;
import com.online_dtie_tracker.repo.user.UserRepo;
import com.online_dtie_tracker.service.user.UserService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    //first get user repository
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto save(UserDto userDto) throws IOException, ParseException {
        //first convert Dto into Entity
        User user = new DtoModelConvert().getUser(userDto);

        //save into database
       User user1 = userRepo.save(user);

        //return userDto with id
        return UserDto.builder().id(user1.getId()).build();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepo.findAll().stream().map(user -> {
            return UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .address(user.getAddress())
                    .contact(user.getContact())
                    .email(user.getEmail()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer integer) throws IOException, ParseException {
        //first find entity by id using repository
      User user =  userRepo.findById(integer).get();

      //return by making bto
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .contact(user.getContact())
                .email(user.getEmail()).build();
    }

    @Override
    public void deleteById(Integer integer) throws IOException {

    }

    @Override
    public void update(UserDto userDto) throws IOException, ParseException {

    }

    public User getUserByUserName(String userName){
       return userRepo.getUserByUserName(userName);
    }
}
