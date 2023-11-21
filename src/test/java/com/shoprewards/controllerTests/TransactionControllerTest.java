package com.shoprewards.controllerTests;

import com.shoprewards.controller.CustomerController;
import com.shoprewards.controller.TransactionController;
import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private TransactionController transactionController;
    @MockBean
    private TransactionService transactionService;

    @Test
    public void testPostTransaction() throws Exception{
        Transaction t = new Transaction();
        when(transactionService.save(t)).thenReturn(t);
        String body = """
                {"value": 100 }
                """;
        mvc.perform(MockMvcRequestBuilders.post("/transaction").contentType(MediaType.APPLICATION_JSON).content(body)).andDo(print());
    }
}
