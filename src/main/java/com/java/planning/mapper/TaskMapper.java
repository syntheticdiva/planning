package com.java.planning.mapper;

import com.java.planning.dto.CreateTaskRequest;
import com.java.planning.dto.TaskDto;
import com.java.planning.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    TaskDto toDto(Task task);
    @Mapping(target = "id", ignore = true)
    Task toEntity(CreateTaskRequest request);

    void updateEntity(@MappingTarget Task task, TaskDto dto);
}