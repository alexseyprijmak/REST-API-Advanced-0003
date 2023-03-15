package com.epam.esm.order;

import com.epam.esm.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/add")
    public Order addOrder(@RequestParam(name = "user_id") Integer userId,
                        @RequestParam(name = "gift-certificate-id") Integer giftCertificateId) {
        return orderService.addOrder(userId, giftCertificateId);
    }

    @GetMapping
    public List<Order> findAll () {return orderService.findAll();}

}
