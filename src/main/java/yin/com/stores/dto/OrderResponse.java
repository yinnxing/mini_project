package yin.com.stores.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String orderId;
    String storeId;
    String userId;
    Double total_price;
    LocalDate order_date;
    List<OrderProductDTO> orderProducts;

}
