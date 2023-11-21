package com.shoprewards.controllerTests;

import com.shoprewards.controller.CustomerController;
import com.shoprewards.entity.Customer;
import com.shoprewards.entity.Transaction;
import com.shoprewards.service.CustomerService;
import com.shoprewards.tools.CreateStuff;
import com.shoprewards.utils.RewardsCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomerService cs;
    @Test
    public void testGetCustomerById() throws Exception{
        int[] values = {130, 60, 60};
        String[] dates = {"2023-11-12 12:12:01","2023-11-1 12:12:01", "2023-10-12 12:12:01"};
        Set<Transaction> transactions = new HashSet<>();
        for (int i = 0; i < values.length; i++) {
            Transaction t = new Transaction();
            t.setTime(Timestamp.valueOf(dates[i]));
            t.setValue(values[i]);
            t.setPoints(RewardsCalculator.calculateRewardsPerValue(values[i]));
            transactions.add(t);
        }
        when(cs.findById(1)).thenReturn(Optional.of(new Customer(1,"name","username", transactions)));
        mvc.perform(MockMvcRequestBuilders.get("/customer/1").accept(MediaType.APPLICATION_JSON)).andDo(print());
    }
    @Test
    public void testPostCustomer() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/customer").accept(MediaType.APPLICATION_JSON)).andDo(print());
    }

    @Test
    public void testDeleteById() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/customer/1")).andDo(print());
    }

    @Test
    public void testUpdateById() throws Exception{
        Customer c = new Customer("Hello", "world");
        String json = """
                {"name": "Hello",
                "username": "world"
                }
                """;
        when(cs.updateById(1, c)).thenReturn(c);
        mvc.perform(MockMvcRequestBuilders.patch("/customer/1").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print());
    }
}
