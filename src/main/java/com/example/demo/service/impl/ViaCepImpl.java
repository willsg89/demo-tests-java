package com.example.demo.service.impl;

import com.example.demo.service.CepAdapter;
import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.util.StringUtil.isEmpty;

@Service
public class ViaCepImpl implements CepAdapter {

    @Override
    public Optional<CepVO> findCep(String cepToFind) {
        try {
            Cep cep = ViaCepClient.findCep(cepToFind);
            if (!isEmpty(cep.getLogradouro()) && !isEmpty(cep.getLocalidade())) {
                val cepVO = CepVO.builder()
                        .address(cep.getLogradouro())
                        .city(cep.getLocalidade())
                        .build();
                    return Optional.ofNullable(cepVO);
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
