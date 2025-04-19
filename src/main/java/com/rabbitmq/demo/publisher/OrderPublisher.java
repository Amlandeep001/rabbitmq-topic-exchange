package com.rabbitmq.demo.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.demo.config.MessagingConfig;
import com.rabbitmq.demo.dto.Order;
import com.rabbitmq.demo.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher
{
	private final RabbitTemplate template;

	public OrderPublisher(RabbitTemplate template)
	{
		this.template = template;
	}

	@PostMapping("/{restaurantName}")
	public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName)
	{
		order.setOrderId(UUID.randomUUID().toString());
		// restaurantservice
		// payment service
		OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed succesfully in " + restaurantName);
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
		return "Success !!";
	}
}
