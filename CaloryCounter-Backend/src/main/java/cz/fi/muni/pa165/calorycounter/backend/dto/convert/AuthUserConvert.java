package cz.fi.muni.pa165.calorycounter.backend.dto.convert;

import cz.fi.muni.pa165.calorycounter.backend.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import javax.persistence.EntityManager;

/**
 * Conversion between AuthUser DTO and entity back and forth.
 *
 * @author
 */
public class AuthUserConvert implements Convert<AuthUser, AuthUserDto> {

    @Override
    public AuthUser fromDtoToEntity(AuthUserDto dto, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AuthUserDto fromEntityToDto(AuthUser entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
