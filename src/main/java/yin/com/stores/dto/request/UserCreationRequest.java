package yin.com.stores.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)

public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID" )
    String username;
    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;
    String lastName;
    String firstName;
    boolean deleted;
    String role;
    Set<String> stores;




}