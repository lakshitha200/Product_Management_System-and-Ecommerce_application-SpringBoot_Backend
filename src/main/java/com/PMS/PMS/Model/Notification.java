package com.PMS.PMS.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String createAt;
    private String message;
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "fk_customer")
    private Customer customer;

    private long productId;
}
