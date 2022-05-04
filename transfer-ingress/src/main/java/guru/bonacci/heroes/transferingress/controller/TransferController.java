package guru.bonacci.heroes.transferingress.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.bonacci.heroes.domain.Transfer;
import guru.bonacci.heroes.transferingress.TransferService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("transfers")
@RequiredArgsConstructor
public class TransferController {

  private final TransferService service;
  

  @PostMapping
//  public String transfer(@Valid @RequestBody TransferDto dto) {
  public String transfer(@RequestBody TransferDto dto) {
    var transfer = toTf(dto);
    service.transfer(transfer);
    return transfer.getTransferId();
  }
  
  static Transfer toTf(TransferDto dto) {
    final String id = UUID.randomUUID().toString();
    return Transfer.builder()
            .transferId(id)
            .poolId(dto.getPoolId())
            .from(dto.getFrom())
            .to(dto.getTo())
            .amount(dto.getAmount())
            .when(System.currentTimeMillis())
            .build();
  }
}