package yin.com.stores.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import yin.com.stores.model.Store;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String firstName;
    String lastName;
    String role;
    boolean deleted;
    Set<Store> stores;

}
