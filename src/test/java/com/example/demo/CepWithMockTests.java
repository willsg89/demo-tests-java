package com.example.demo;


import com.example.demo.service.UserService;
import com.example.demo.service.impl.CepVO;
import com.example.demo.service.impl.ViaCepImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CepWithMockTests {

    @Autowired
    private UserService userService;

    @MockBean
    ViaCepImpl cepAdapter;

    @Test
    void shouldBeReturnAValidCep() throws Exception {
        Mockito.when(cepAdapter.findCep(any())).thenReturn(
                Optional.of(CepVO.builder().city("poa").address("rua").build())
        );
        val cep = userService.findCep("90010001");
        assertTrue(cep.get().getCity().equalsIgnoreCase("poa"));
        assertTrue(cep.get().getAddress().equalsIgnoreCase("rua"));
    }

    @Test
    void shouldBeReturnANoneResponse() throws Exception {
        Mockito.when(cepAdapter.findCep(any())).thenReturn(
                Optional.empty()
        );
        val cep = userService.findCep("98745448");
        assertFalse(cep.isPresent());
    }

}