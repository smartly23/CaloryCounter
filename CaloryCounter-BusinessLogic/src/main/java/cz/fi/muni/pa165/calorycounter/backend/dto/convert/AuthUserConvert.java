package cz.fi.muni.pa165.calorycounter.backend.dto.convert;

import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import org.springframework.stereotype.Component;

/**
 * Conversion between AuthUser DTO and entity back and forth.
 *
 * @author Zdenek Lastuvka
 */
@Component
public class AuthUserConvert {

    public static AuthUser fromDtoToEntity(AuthUserDto dto) {
        if (dto == null) {
            return null;
        }
        AuthUser authUser = new AuthUser();
        authUser.setId(dto.getUserId());
        authUser.setAge(dto.getAge());
        authUser.setName(dto.getName());
        authUser.setGender(dto.getSex());//proc ty renamy?
        authUser.setWeightCat(dto.getWeightCategory());
        authUser.setUsername(dto.getUsername());
        authUser.setPassword(dto.getPassword());
        return authUser;
    }

    public static AuthUserDto fromEntityToDto(AuthUser entity) {
        if (entity == null) {
            return null;
        }
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setUserId(entity.getId());
        authUserDto.setAge(entity.getAge());
        authUserDto.setName(entity.getName());
        authUserDto.setSex(entity.getGender());//proc ty renamy?
        authUserDto.setWeightCategory(entity.getWeightCat());
        authUserDto.setUsername(entity.getUsername());
        authUserDto.setPassword(entity.getPassword());
        return authUserDto;
    }
}
