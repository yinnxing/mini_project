package yin.com.stores.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String orderId;
    @ManyToOne
    @JoinColumn(name = "storeId", insertable = false)
    Store store;
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false)
    User user;
    Double total_price;
    LocalDate order_date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    List<OrderProduct> orderProducts = new ArrayList<>();


}
