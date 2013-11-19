package cz.fi.muni.pa165.calorycounter.backend.dao;

import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;

/**
 * DAO interface - for operations on the persistence layer on User entities.
 *
 * @author Martin Pasko (smartly23)
 */
public interface UserDao extends Dao<AuthUser> {
    
    /*
     * Finds user with given username.
     * 
     * @param username Username (login) given at registration (must be unique).
     * @return Authenticated user with given username.
     * @throws IllegalArgumentException if parameter is null or invalid
     */
    AuthUser getByUsername(String username);
    
    /** 
     * Verify if user with given username and password exists
     * @param username
     * @param password
     * @return User with given username and password
     */
    AuthUser login(String username, String password);
    
}