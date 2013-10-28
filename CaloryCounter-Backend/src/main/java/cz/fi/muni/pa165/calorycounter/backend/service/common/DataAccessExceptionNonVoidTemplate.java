package cz.fi.muni.pa165.calorycounter.backend.service.common;

import org.springframework.dao.RecoverableDataAccessException;

/**
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
    /*
    public void tryVoidMethod() {
        try {
            doVoidMethod();
        } catch (Exception ex) {
            throw new RecoverableDataAccessException("Operation 'create' failed." + ex.getMessage(), ex);
        }
    }
    * */
    
    public abstract T doMethod();
    
    //public abstract void doVoidMethod();
    
    public U getU() {
        return u;
    }
    
    
}
