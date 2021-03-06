package guru.bonacci.heroes.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import guru.bonacci.heroes.domain.Account;
import guru.bonacci.heroes.service.AccService;
 
@WebMvcTest(value = AccController.class)
public class AccControllerTest {
 
    @MockBean
    private AccService service;

    @Autowired
    private MockMvc mvc; 

    
    @Test
    public void showAccount() throws Exception {
      var acc = new Account("foo", "heroes"); 

      when(service.showMeTheAccount(eq("foo"), eq("heroes"))).thenReturn(Optional.of(acc));
      
      mvc.perform(get("/pools/{poolId}/accounts/{accountId}", "heroes", "foo"))
              .andExpect(status().isOk());
    }
    
    @Test
    public void showNonExistingAccount() throws Exception {
      when(service.showMeTheAccount(eq("foo"), eq("heroes"))).thenReturn(Optional.empty());
      
      mvc.perform(get("/pools/{poolId}/accounts/{accountId}", "heroes", "foo"))
              .andExpect(status().is4xxClientError());
    }
    
    @Test
    public void showAccountBalance() throws Exception {
      when(service.showMeTheBalance(eq("foo"), eq("heroes"))).thenReturn(Optional.of(BigDecimal.TEN));
      
      mvc.perform(get("/pools/{poolId}/accounts/{accountId}/balance", "heroes", "foo"))
              .andExpect(status().isOk());
    }
    
    @Test
    public void showNonExistingAccountBalance() throws Exception {
      when(service.showMeTheAccount(eq("foo"), eq("heroes"))).thenReturn(Optional.empty());
      
      mvc.perform(get("/pools/{poolId}/accounts/{accountId}/balance", "heroes", "foo"))
              .andExpect(status().is4xxClientError());
    }
   
}