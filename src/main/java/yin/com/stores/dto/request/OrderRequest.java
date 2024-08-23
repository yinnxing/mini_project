package yin.com.stores.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import yin.com.stores.dto.OrderProductDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    List<OrderProductDTO> orderProducts;
}
