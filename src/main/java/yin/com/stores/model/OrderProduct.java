package yin.com.stores.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import yin.com.stores.model.enum_key.OrderProductId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "order_product")
@IdClass(OrderProductId.class)

public class OrderProduct {
    @Id
    String orderId;
    @Id
    String productId;
    Long quantity;
    Double price;
    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    Order order;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    Product product;


}
