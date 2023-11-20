package exercise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import exercise.dto.CarCreateDTO;
import exercise.dto.CarUpdateDTO;
import exercise.dto.CarDTO;
import exercise.model.Car;

// BEGIN
@Mapper(
        uses = {JsonNullableMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CarMapper {
// Создайте маппер для преобразования объектов DTO в сущность и обратно.
// Подключите в него универсальный маппер JsonNullableMapper
    public abstract Car map(CarCreateDTO car);
    public abstract CarDTO map(Car car);
    public abstract void update(CarUpdateDTO dto, @MappingTarget Car model); //dto что меняет. model сама модель
}
// END
