package com.Spring.stud.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@NamedEntityGraph(
        name = "orders.for-front",
        attributeNodes = {
                @NamedAttributeNode(value = "orderItemList", subgraph = "orderItemList-products")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "orderItemList-products",
                        attributeNodes = {
                                @NamedAttributeNode("product")
                        }
                )
        }
)
public class Order {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_price")
    private int orderPrice;

    @Column(name = "tel_number")
    private String telNumber;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL})
    private List<OrderItem> orderItemList;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
