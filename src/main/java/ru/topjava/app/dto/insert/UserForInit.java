package ru.topjava.app.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserForInit {
    private UUID id;

    @NotBlank
    @Size(max = 100)
    private String email;

    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @NotNull
    private Date registered;

    @NotNull
    private String role;

}
