package br.com.microservices.orchestrated.core.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderProducts {

  private Product product;
  private int quantity;
}
