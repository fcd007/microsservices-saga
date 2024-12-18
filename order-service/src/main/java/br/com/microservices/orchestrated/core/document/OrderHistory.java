package br.com.microservices.orchestrated.core.document;

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

  private String source;
  private String status;
  private String message;
  private LocalDateTime createdAt;
}
