package br.com.microservices.orchestrated.config.kafka;

import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.BASE_ORCHESTRATOR;
import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.FINISH_FAIL;
import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.FINISH_SUCCESS;
import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.INVENTORY_FAIL;
import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.INVENTORY_SUCCESS;
import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.NOTIFY_ENDING;
import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.PAYMENT_FAIL;
import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.PAYMENT_SUCCESS;
import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.PRODUCT_VALIDATION_FAIL;
import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.PRODUCT_VALIDATION_SUCCESS;
import static br.com.microservices.orchestrated.core.enuns.TopicsEnum.START_SAGA;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

  private static final Integer PARTITION_COUNT = 1;
  private static final Integer REPLICA_COUNT = 1;

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${spring.kafka.consumer.group-id}")
  private String groupId;

  @Value("${spring.kafka.consumer.auto-offset-reset}")
  private String autoOffsetReset;

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerProperties());
  }

  private Map<String, Object> consumerProperties() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

    return props;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerProperties());
  }

  private Map<String, Object> producerProperties() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return props;
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

  private NewTopic buildTopic(String name) {
    return TopicBuilder
        .name(name)
        .replicas(PARTITION_COUNT)
        .partitions(REPLICA_COUNT)
        .build();
  }

  @Bean
  public NewTopic startSagaTopic() {
    return buildTopic(START_SAGA.getTopic());
  }


  @Bean
  public NewTopic topicFinishSuccess() {
    return buildTopic(FINISH_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic topicFinisFail() {
    return buildTopic(FINISH_FAIL.getTopic());
  }

  @Bean
  public NewTopic topicOrchestrator() {
    return buildTopic(BASE_ORCHESTRATOR.getTopic());
  }

  @Bean
  public NewTopic topicProductValidationSuccess() {
    return buildTopic(PRODUCT_VALIDATION_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic topicProductValidationFail() {
    return buildTopic(PRODUCT_VALIDATION_FAIL.getTopic());
  }

  @Bean
  public NewTopic topicPaymentSuccess() {
    return buildTopic(PAYMENT_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic topicPaymentFail() {
    return buildTopic(PAYMENT_FAIL.getTopic());
  }

  @Bean
  public NewTopic notifyEndingTopic() {
    return buildTopic(NOTIFY_ENDING.getTopic());
  }

  @Bean
  public NewTopic topicInventorySuccess() {
    return buildTopic(INVENTORY_SUCCESS.getTopic());
  }

  @Bean
  public NewTopic topicInventoryFail() {
    return buildTopic(INVENTORY_FAIL.getTopic());
  }

}
