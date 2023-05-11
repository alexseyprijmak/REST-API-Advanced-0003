package com.epam.esm.user;


import com.epam.esm.utils.abstractClasses.Identifiable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends Identifiable {

    @Column(name = "name")
    private String name;


    public User() {
    }
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
