package com.test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    @JsonIgnore
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "user first name should have at least 2 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "user last name should have at least 2 characters")
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @URL (message = "picture url must be a valid URL")
    private String url;
    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;
    @NotEmpty (message = "Status must be valid")
    private String status;
}