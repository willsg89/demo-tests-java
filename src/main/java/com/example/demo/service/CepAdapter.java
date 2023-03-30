package com.example.demo.service;

import com.example.demo.service.impl.CepVO;

import java.util.Optional;

public interface CepAdapter {
    Optional<CepVO> findCep(String cepToFind);
}
