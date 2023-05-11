package com.epam.esm.order;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public Order addOrder(@RequestParam(name = "user_id") Long userId,
                          @RequestParam(name = "gift-certificate-id") Long giftCertificateId) {
        return orderService.addOrder(userId, giftCertificateId);
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

}
