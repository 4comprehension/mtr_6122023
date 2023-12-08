package com.pivovarit;

 import org.springframework.amqp.core.Binding;
 import org.springframework.amqp.core.BindingBuilder;
 import org.springframework.amqp.core.Queue;
 import org.springframework.amqp.core.TopicExchange;
 import org.springframework.amqp.rabbit.connection.ConnectionFactory;
 import org.springframework.amqp.rabbit.core.RabbitTemplate;
 import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
 import org.springframework.amqp.support.converter.MessageConverter;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 //@Configuration
 class ExampleRabbitMQConfiguration {

     @Bean
     public MessageConverter messageConverter() {
         return new Jackson2JsonMessageConverter();
     }

     @Bean
     public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
         RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
         rabbitTemplate.setMessageConverter(messageConverter);
         return rabbitTemplate;
     }

     // tylko na potrzeby debugowania
     @Bean
     Queue queue() {
         return new Queue("rentals", false);
     }

     @Bean
     TopicExchange exchange() {
         return new TopicExchange("rentals-topic");
     }

     @Bean
     Binding binding(Queue queue, TopicExchange exchange) {
         return BindingBuilder.bind(queue).to(exchange).with("TODO" + ".#");
     }

 }
