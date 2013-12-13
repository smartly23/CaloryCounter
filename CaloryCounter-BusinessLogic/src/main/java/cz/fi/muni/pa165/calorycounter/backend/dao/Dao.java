
package cz.fi.muni.pa165.calorycounter.backend.dao;

/**
 * DAO super-interface
 * Extending interfaces will inherit basic CRUD operations on their entities.
 * 
 * @author Martin Pasko (smartly23)
 * 
 * @param <T> Generic type of entity 
 */
public interface Dao<T> {
    
    /*
     * @throws IllegalArgumentException if parameter is null or invalid
     */
    Long create(T entity);
    
    /*
     * @throws IllegalArgumentException if parameter is null or invalid
     */
    T get(Long id);
    
    /*
     * @throws IllegalArgumentException if parameter is null, invalid or non-existent in the DB
     */
    void update(T entity);
    
    /*
     * @throws IllegalArgumentException if parameter is null or invalid. Does not throw this exception if
     * parameter is valid but given entity is nonexistent. 
     */
    void remove(Long id);
}
