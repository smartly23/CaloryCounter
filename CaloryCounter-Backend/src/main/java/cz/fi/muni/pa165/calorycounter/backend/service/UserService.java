package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.UserStatsDto;
import java.util.List;

/**
 * User service interface for non-CRUD operations on User DTO.
 *
 * @author Jan Kucera (Greld)
 */
public interface UserService{

    /**
     *  Verify if user with given username and password exists.
     * @param username
     * @param password
     * @return User if user with given username and password exist, null otherwise
     */
    AuthUserDto login(String username, String password);
    
    /**
     * Create new user.
     * @param user
     * @param username
     * @param password
     * @return User id if registration was successfull, null otherwise
     */
    Long register(AuthUserDto user, String username, String password);
    
    /**
     * 
     * @param username
     * @return user with given username.
     */
    AuthUserDto getByUsername(String username);
    
    /** Returns all users with their stats
     * 
     * @return All users with their stats
     */
    List<UserStatsDto> getAllUserStats();
}
