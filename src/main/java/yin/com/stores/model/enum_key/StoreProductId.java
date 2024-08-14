package yin.com.stores.model.enum_key;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class StoreProductId implements Serializable {
    private String storeId;
    private String productId;


}
