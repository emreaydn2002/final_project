package com.evam.Service;


import java.text.ParseException;

public interface HelperService<T> {
    T Create(T model) throws ParseException;
    String Update(Long id, T model);
    String Delete(Long id);

}
