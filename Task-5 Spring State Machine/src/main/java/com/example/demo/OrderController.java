package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private StateMachine<OrderStates, OrderEvents> stateMachine;

    @GetMapping("/start")
    public String startOrder() {
        stateMachine.start();
        return "Order started. Current state: " + stateMachine.getState().getId();
    }

    @GetMapping("/pay")
    public String payOrder() {
        stateMachine.sendEvent(OrderEvents.PAY);
        return "Payment made. Current state: " + stateMachine.getState().getId();
    }

    @GetMapping("/ship")
    public String shipOrder() {
        stateMachine.sendEvent(OrderEvents.SHIP);
        return "Order shipped. Current state: " + stateMachine.getState().getId();
    }

    @GetMapping("/deliver")
    public String deliverOrder() {
        stateMachine.sendEvent(OrderEvents.DELIVER);
        return "Order delivered. Current state: " + stateMachine.getState().getId();
    }
}
