package exercise.mapper;

import exercise.dto.*;
import exercise.model.Task;
import org.mapstruct.*;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {
    // BEGIN
    @Mapping(target = "assignee.id", source = "assigneeId")
    public abstract Task map(TaskCreateDTO taskCreateDTO);

    @Mapping(target = "assigneeId", source = "assignee.id")
    public abstract TaskDTO map(Task task);
//    @Mapping(target = "assignee.id", source = "assigneeId")
    public abstract void update(TaskUpdateDTO dto, @MappingTarget Task task);
    // END

}
