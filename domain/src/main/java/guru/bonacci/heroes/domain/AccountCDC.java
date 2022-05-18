package guru.bonacci.heroes.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCDC {

  private String accountId; //required
  private String poolId; //required
  private BigDecimal startAmount; //required

  private String accountName;
  // and some more fields...

  public String identifier() {
    return this.poolId + "." + this.accountId;
  }
}