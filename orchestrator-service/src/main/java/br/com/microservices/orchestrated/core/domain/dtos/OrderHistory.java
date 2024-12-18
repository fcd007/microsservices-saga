package br.com.microservices.orchestrated.core.domain.dtos;

import br.com.microservices.orchestrated.core.domain.enums.EventSourceEnum;
import br.com.microservices.orchestrated.core.domain.enums.SagaStatusEnum;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderHistory {

  private EventSourceEnum source;
  private SagaStatusEnum status;
  private String message;
  private LocalDateTime createdAt;
}
