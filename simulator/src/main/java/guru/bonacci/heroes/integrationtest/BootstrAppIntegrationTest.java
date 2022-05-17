package guru.bonacci.heroes.integrationtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import guru.bonacci.heroes.domain.Transfer;
import guru.bonacci.heroes.domain.dto.TransferDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class BootstrAppIntegrationTest {

  private final MockOrStub mockOrStub;

  @Value("${transfer.url}")
  private String clientHostAndPort;
  
  
  public static void main(String[] args) {
		SpringApplication.run(BootstrAppIntegrationTest.class, args);
	}
	

	@Scheduled(fixedRateString =  "${fixed.rate}")
	public void showMeTheMoney() {
	  RestTemplate restTemplate = new RestTemplate();

	  var pool = MockOrStub.ONLY_BUT_NOT_LONELY_TEST_POOL;
    var from = mockOrStub.getRandomAccount();
    var to = mockOrStub.getRandomAccount();
    var amount = mockOrStub.getRandomAmount(100);

    var transfer = new TransferDto(pool, from, to, amount);

	  HttpEntity<TransferDto> request = new HttpEntity<>(transfer);
    log.info("hitting {}", clientHostAndPort + "/transfers");
    var response = restTemplate.postForEntity(clientHostAndPort + "/transfers", request, Transfer.class);

    if (!response.getStatusCode().is2xxSuccessful()) {
      log.error(response.toString());
    } else {
      log.info("transfer id {}", response.getBody().getTransferId());
    }
	}    
}
