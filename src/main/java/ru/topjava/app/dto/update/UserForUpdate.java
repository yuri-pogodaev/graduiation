package ru.topjava.app.dto.update;

import lombok.*;
import ru.topjava.app.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserForUpdate {
    @NotBlank
    @Size(max = 100)
    private String email;

    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;


    public UserForUpdate(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.password= user.getPassword();
    }
}