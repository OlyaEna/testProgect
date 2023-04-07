package com.test.mapper;

import com.test.dto.UserDto;
import com.test.model.entity.User;
import com.test.model.repository.StatusRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
@Data
public class UserMapper {
    private ModelMapper modelMapper;
    private final StatusRepository statusRepository;

    public User toEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, User.class);
    }

    public UserDto toDto(User entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, UserDto.class);
    }

    public List<UserDto> listToDto(List<User> entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, new TypeToken<List<UserDto>>() {
        }.getType());
    }


    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setStatus)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(UserDto.class, User.class)
                .addMappings(m -> m.skip(User::setStatus)).setPostConverter(toEntityConverter());
    }

    public Converter<UserDto, User> toEntityConverter() {
        return context -> {
            UserDto source = context.getSource();
            User destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<User, UserDto> toDtoConverter() {
        return context -> {
            User source = context.getSource();
            UserDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }


    public void mapSpecificFields(User source, UserDto destination) {
        destination.setStatus(Objects.isNull(source) || Objects.isNull(source.getStatus()) ? null : source.getStatus().getName());
    }

    void mapSpecificFields(UserDto source, User destination) {
        destination.setStatus(statusRepository.findByName(source.getStatus()).orElse(null));
    }
}