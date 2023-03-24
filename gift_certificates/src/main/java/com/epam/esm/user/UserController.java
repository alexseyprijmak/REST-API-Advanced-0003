package com.epam.esm.user;

import com.epam.esm.order.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable("userId") Long tagId) {
        return userService.findById(tagId);
    }

    @GetMapping("/userOrders/{userId}")
    public List<Order> getUserOrders(@PathVariable("userId") Long userId) {
        return userService.getUserOrders(userId);
    }

    @GetMapping("/userPriceAndPurchaseTime/{userId}")
    public Map<User, Map<BigDecimal, LocalDateTime>> getUserOrderInformationWithPriceAndPurchaseTime(@PathVariable("userId") Long userId) {
        return userService.getUserOrderInformationWithPriceAndPurchaseTime(userId);
    }

    @PostMapping("/add/{name}")
    public User addUser(@PathVariable("name") String name) {
        return userService.addUser(name);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
