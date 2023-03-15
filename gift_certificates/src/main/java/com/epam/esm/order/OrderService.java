package com.epam.esm.order;


import com.epam.esm.exeptions.NoSuchEntityException;
import com.epam.esm.giftCertificate.GiftCertificate;
import com.epam.esm.giftCertificate.GiftCertificateRepository;
import com.epam.esm.tag.Tag;
import com.epam.esm.tag.TagRepository;
import com.epam.esm.user.User;
import com.epam.esm.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;
import java.util.Date;
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

    public Order addOrder(Integer userId, Integer giftCertificateId) {

        // TODO chek user and certificate if valid

            Order order = new Order();
            order.setGiftCertificate(giftCertificateRepository.getById(giftCertificateId));
            order.setUser(userRepository.getById(userId));
            order.setPurchaseTime(new Date());
            order.setPrice(giftCertificateRepository.getById(giftCertificateId).getPrice());

            return orderRepository.save(order);

    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }



}
