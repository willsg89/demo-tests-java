package com.example.demo;


import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserRequest;
import com.example.demo.service.CepAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class CepTests {

    @Autowired
    private CepAdapter cepAdapter;

    @Test
    void shouldBeReturnAValidCep() throws Exception {
        val cep = cepAdapter.findCep("90010001");
        assertTrue(cep.get().getCity().equalsIgnoreCase("porto alegre"));
        assertTrue(cep.get().getAddress().equalsIgnoreCase("rua siqueira campos"));
    }

    @Test
    void shouldBeReturnANoneResponse() throws Exception {
        val cep = cepAdapter.findCep("98745448");
        assertFalse(cep.isPresent());
    }

}