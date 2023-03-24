package com.epam.esm.user;

import com.epam.esm.order.Order;
import com.epam.esm.utils.abstractClasses.Identifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Identifiable {

    public User() {
    }


    @Column(name = "name")
    private String name;
//
//    @OneToMany(mappedBy = "user")
//    private List<Order> orders = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
//                ", orders=" + orders +
                '}';
    }
}
