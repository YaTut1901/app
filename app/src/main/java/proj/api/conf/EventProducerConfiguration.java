package proj.api.conf;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proj.api.ampq.Producer;

@Configuration
public class EventProducerConfiguration {
    @Bean
    public Exchange eventExchange() {
        return new TopicExchange("eventExchange");
    }

    @Bean
    public Producer getProducer(RabbitTemplate rabbitTemplate, Exchange eventExchange) {
        return new Producer(rabbitTemplate, eventExchange);
    }
}
