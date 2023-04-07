package com.test.mapper;


import com.test.dto.StatusDto;
import com.test.dto.UserDto;
import com.test.model.entity.Status;
import com.test.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
@Data
public class StatusMapper {

    private ModelMapper modelMapper;

    public Status toEntity(StatusDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Status.class);
    }

    public StatusDto toDto(Status entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, StatusDto.class);
    }

    public List<StatusDto> listToDto(List<Status> entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, new TypeToken<List<StatusDto>>() {
        }.getType());
    }
}
