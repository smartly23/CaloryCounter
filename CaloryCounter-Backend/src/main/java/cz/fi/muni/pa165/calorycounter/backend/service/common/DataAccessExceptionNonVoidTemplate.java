package cz.fi.muni.pa165.calorycounter.backend.service.common;

import org.springframework.dao.RecoverableDataAccessException;

/**
 * Exception template for wrapping try-catch block around any set of persistence operations, throwing
 * DataAccessExceptions when Exception in the wrapped code itself is thrown.
 * All tested code has to be put into implementation of the abstract doMethod().
 * This class is used when code is expected to include a non-void return statement.
 * 
 * @author Martin Pasko (smartly23)
 */
public abstract class DataAccessExceptionNonVoidTemplate<T,U> {
    
    private final U u;

    public DataAccessExceptionNonVoidTemplate(U u) {
        this.u = u;
    }
    
    public T tryMethod() {
        T returnedObject;
        try {
            returnedObject = doMethod();
        } catch (Exception ex) {
            throw new RecoverableDataAccessException("Operation 'create' failed." + ex.getMessage(), ex);
        }
        return returnedObject;
    }
    
    public abstract T doMethod();
    
    public U getU() {
        return u;
    }
    
    
}
