package ru.topjava.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.User;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserForResponse  {
//    @JsonSerialize(using= UUIDSerializer.class)
//    @JsonDeserialize(using= UUIDDeserializer.class)
    private UUID id;
    private String email;
    private String name;
    private Date registered;
    private String role;


    public UserForResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.registered = user.getRegistered();
        this.role=user.getRole();    }
}
