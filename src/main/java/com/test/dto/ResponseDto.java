package com.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {
    private Long id;
    @JsonProperty("old_status")
    private String oldStatus;
    @JsonProperty("new_status")
    private String newStatus;
}
