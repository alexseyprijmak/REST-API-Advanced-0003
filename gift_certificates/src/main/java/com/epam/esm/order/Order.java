package com.epam.esm.order;

import com.epam.esm.giftCertificate.GiftCertificate;
import com.epam.esm.user.User;
import com.epam.esm.utils.abstractClasses.Identifiable;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends Identifiable {

    public Order() {}

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "purchase_time")
    private LocalDateTime purchaseTime;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne


    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private GiftCertificate giftCertificate;

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "purchaseTime=" + purchaseTime +
                ", price=" + price +
                ", user=" + user +
                ", giftCertificate=" + giftCertificate +
                '}';
    }
}
