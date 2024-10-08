package yin.com.stores.model.enum_key;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreProductId implements Serializable {
    String storeId;
    String productId;


}
