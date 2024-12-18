package br.com.microservices.orchestrated.core.domain.dtos;

import br.com.microservices.orchestrated.core.domain.enums.SagaStatusEnum;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Event {

  private String id;
  private String transactionId;
  private String orderId;
  private Order payload;
  private SagaStatusEnum status;
  private String source;
  private List<OrderHistory> eventHistory;
  private LocalDateTime createdAt;
}
