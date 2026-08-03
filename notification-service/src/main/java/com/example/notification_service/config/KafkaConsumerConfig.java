package com.example.notification_service.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.notification_service.events.PaymentCreatedEvent;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import org.springframework.kafka.listener.ContainerProperties;

@Configuration
public class KafkaConsumerConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	
	
	
	@Bean
	public ConsumerFactory<String,PaymentCreatedEvent> consumerFactory(){
		
		Map<String,Object> props = new HashMap<>();
		 props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		    JsonDeserializer<PaymentCreatedEvent> deserializer =
		            new JsonDeserializer<>(PaymentCreatedEvent.class);

		    deserializer.addTrustedPackages(
		            "com.example.payment_service.events",
		            "com.example.notification_service.events"
		    );

		    deserializer.setUseTypeHeaders(false);
		    	
		    
		    return new DefaultKafkaConsumerFactory<String, PaymentCreatedEvent>(props, new StringDeserializer(), deserializer);
		 
		 	
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, PaymentCreatedEvent> kafkaListenerContainerFactory( ConsumerFactory<String, PaymentCreatedEvent> consumerFactory )  {
		
		ConcurrentKafkaListenerContainerFactory<String, PaymentCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory();
		
		factory.setConsumerFactory(consumerFactory());
		DefaultErrorHandler errorHandler = new DefaultErrorHandler(new FixedBackOff(5000L,2));
		
		factory.setCommonErrorHandler(errorHandler);
	    factory.getContainerProperties()
        .setAckMode(ContainerProperties.AckMode.MANUAL);

		
		return factory;
	}

}
