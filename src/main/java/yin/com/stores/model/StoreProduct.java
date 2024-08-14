package yin.com.stores.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import yin.com.stores.model.enum_key.StoreProductId;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "store_product")
@IdClass(StoreProductId.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreProduct {
    @Id
    String storeId;
    @Id
    String productId;

    Long stock_quantity;


    @ManyToOne
    @JoinColumn(name = "storeId", insertable = false, updatable = false)
    Store store;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    Product product;

}
