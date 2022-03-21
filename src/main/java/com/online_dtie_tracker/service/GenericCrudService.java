package com.online_dtie_tracker.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface GenericCrudService<D, ID>{
    D save(D d) throws IOException, ParseException;
    List<D> findAll();
    D findById(ID id) throws IOException, ParseException;
    void deleteById(ID id) throws IOException;

    void update(D d) throws IOException, ParseException;
}