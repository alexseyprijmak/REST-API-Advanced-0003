package com.epam.esm.order;


import com.epam.esm.giftCertificate.GiftCertificateRepository;
import com.epam.esm.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final GiftCertificateRepository giftCertificateRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, GiftCertificateRepository giftCertificateRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.giftCertificateRepository = giftCertificateRepository;
        this.userRepository = userRepository;
    }

    public Order addOrder(Long userId, Long giftCertificateId) {

        // TODO chek user and certificate if valid

            Order order = new Order();
            order.setGiftCertificate(giftCertificateRepository.getById(giftCertificateId));
            order.setUser(userRepository.getById(userId));
            order.setPurchaseTime(LocalDateTime.now());
            order.setPrice(giftCertificateRepository.getById(giftCertificateId).getPrice());

            return orderRepository.save(order);

    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }



}
