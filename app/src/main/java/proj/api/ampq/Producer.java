package proj.api.ampq;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Producer {

    private final RabbitTemplate rabbitTemplate;

    private final Exchange exchange;

    public Producer(RabbitTemplate rabbitTemplate, Exchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, message);
    }
}
