package com.epam.esm.user;


import com.epam.esm.exeptions.NoSuchEntityException;
import com.epam.esm.order.OrderRepository;

import com.epam.esm.order.Order;

import com.epam.esm.tag.Tag;
import com.epam.esm.tag.TagRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;



    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;

    }

    public User addUser(String name) {
        List<User> userList = userRepository.findAll().stream()
                .filter(userInDB -> name.equals(userInDB.getName())).toList();

        if (userList.size() > 0) {
            throw new NoSuchEntityException(String.format("Tag have by name: %s already exists", name));
        } else {
            User user = new User();
            user.setName(name);
            return userRepository.save(user);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException(String.format("User with id : %d wasn't found", userId));
        }

    }

    public Map<User, Map<BigDecimal, LocalDateTime>> getUserOrderInformationWithPriceAndPurchaseTime(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {

            List<Order> userOrders = orderRepository.findAll().stream().
                    filter(order -> order.getUser().getId() == userId).toList();

            List<BigDecimal> userPrice = userOrders.stream()
                    .map(Order::getPrice).toList();

            List<LocalDateTime> userPurchaseTime = userOrders.stream().
                    map(Order::getPurchaseTime).toList();

            Map<BigDecimal, LocalDateTime> userPriceAndPurchaseTime = IntStream.range(0, userPrice.size()).boxed()
                    .collect(Collectors.toMap(userPrice::get, userPurchaseTime::get));

            return new HashMap<>() {{
                put(user.get(), userPriceAndPurchaseTime);
            }};
        } else {
            throw new RuntimeException(String.format("User with id : %d wasn't found", userId));
        }
    }


    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findAll().stream().filter(order -> order.getUser().getId() == userId).toList();

    }

    public void deleteUser(Long userId) {
        userRepository.delete(userRepository.getById(userId));
    }

    public Tag findMostPopularTagOfUserWithHighestCostOfAllOrders() {

        return tagRepository.getById(tagRepository.mostPopularTagOfUserWithHighestCostOfAllOrders());
    }
}


