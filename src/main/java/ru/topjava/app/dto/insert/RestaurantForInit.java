package ru.topjava.app.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RestaurantForInit {
    private UUID id;

    @NotBlank
    @Size(min = 10, max = 100)
    private String name;

    @Column(name = "address")
    @NotBlank
    private String address;
}
