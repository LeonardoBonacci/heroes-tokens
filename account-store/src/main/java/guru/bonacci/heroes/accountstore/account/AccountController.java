package guru.bonacci.heroes.accountstore.account;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import guru.bonacci.heroes.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;


  @GetMapping("/pools/{poolId}/accounts/{accountId}")
  public ResponseEntity<Account> showAccount( @PathVariable @NotBlank String poolId, 
                                              @PathVariable @NotBlank String accountId) {
    StopWatch watch = new StopWatch();
    watch.start();
  
    var accOpt = accountService.getAccount(poolId, accountId);
    
    watch.stop();
    log.info("Processing Time : {}", watch.getTime()); 

    return accOpt.map(acc -> ResponseEntity.ok().body(acc))
          .orElse(ResponseEntity.notFound().build());
  }
  
  @GetMapping("/pools/{poolId}/accounts/{accountId}/balance")
  public ResponseEntity<BigDecimal> getBalance( @PathVariable @NotBlank String poolId, 
                                                @PathVariable @NotBlank String accountId) {
      var balanceOpt = accountService.getBalance(poolId, accountId);
      return balanceOpt.map(bal -> ResponseEntity.ok().body(bal))
          .orElse(ResponseEntity.notFound().build());
  }
}