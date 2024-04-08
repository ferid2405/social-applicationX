package com.social.socialapplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long userId;
    @NotBlank(message = "username must not be empty")
    @Size(min = 3,max=25 ,message = "required username must be min 2,max 25 character")
    @Column(unique = true)
    String username;
    @NotBlank(message = "name must be not empty")
    @Size(min = 3,max = 30,message = "required name must be min 3,max 30 character")
    @Column(unique = true)
    String name;
    @NotBlank(message = "surname must be not empty")
    @Size(min = 3,max = 30,message = "required surname must be min 3,max 30 character")
    @Column(unique = true)
    String Surname;
    @NotBlank(message = "email must be not empty")
    @Size(min = 10,max = 50,message = "required email must be min 3,max 30 character")
    @Column(unique = true)
    @Email
    String email;
    @NotBlank(message = "password must be not empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "password must contain at least 1 uppercase letter, 1 lowercase letter,and 1 digit,with" +
                    "a minumum length of 8 characters")
    String password;
    @NotNull(message = "birthdate must be not empty")
    @Column(columnDefinition = "DATE")
    LocalDate birthdate;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Post>posts;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Comment>comments;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Like>likes;
}
