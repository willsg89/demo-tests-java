package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CepAdapter;
import com.example.demo.service.UserService;
import lombok.val;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.util.StringUtil.isEmpty;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CepAdapter cepAdapter;

    public UserServiceImpl(UserRepository userRepository, CepAdapter cepAdapter) {
        this.userRepository = userRepository;
        this.cepAdapter = cepAdapter;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("not.found"));
    }

    @Override
    public Optional<CepVO> findCep(String cep) {
        return cepAdapter.findCep(cep);
    }

    @Override
    public User findByName(String name) {
        User user = User.builder().name(name).build();
        val s = Example.of(user);
        return userRepository.findOne(s).orElseThrow(() -> new RuntimeException("not.found"));
    }

    @Override
    public User insert(String name) {
        if (isEmpty(name)) {
            throw new RuntimeException("name.empty");
        }
        val entity = User.builder().name(name).build();
        return userRepository.save(entity);
    }


}
