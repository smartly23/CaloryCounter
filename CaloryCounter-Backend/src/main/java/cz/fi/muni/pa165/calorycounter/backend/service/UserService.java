package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dto.AuthUserDto;

/**
 * User service interface for non-CRUD operations on User DTO.
 *
 * @author Martin Pasko (smartly23)
 */
public interface UserService extends Service<AuthUserDto> {

    AuthUserDto getByUsername(String username);
}
