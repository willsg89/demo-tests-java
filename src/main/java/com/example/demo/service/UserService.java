package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.service.impl.CepVO;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    List<User> findAll();

    User findById(Long id);

    Optional<CepVO> findCep(String cep);

    User findByName(String name);

    User insert(String name);
}
