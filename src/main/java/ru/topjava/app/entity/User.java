package ru.topjava.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "users_unique_email_idx", columnNames = "email")})
@Builder
@Access(AccessType.FIELD)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    @Size(max = 100)
    private String email;

    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    @Size(min = 5, max = 100)
    private String password;

    @CreationTimestamp
    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    private Date registered;

    @Column(name = "is_admin", nullable = false, columnDefinition = "bool default false")
    private Boolean isAdmin;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Vote> voteList;
}
