package com.epam.esm.user;

import com.epam.esm.exeptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(String name) {
        List<User> tags = userRepository.findAll().stream()
                .filter(userInDB -> name.equals(userInDB.getName())).toList();

        if (tags.size() > 0) {
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

    public User findById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException(String.format("User with id : %d wasn't found", userId));
        }

    }

    public void deleteUser(Integer userId) {
        userRepository.delete(userRepository.getById(userId));
    }

}


