package cz.fi.muni.pa165.calorycounter.backend.service.common;

import org.springframework.dao.RecoverableDataAccessException;

/**
 *
 * @author Martin Pasko (smartly23)
 */
public abstract class DataAccessExceptionVoidTemplate<U> {
        private final U u;

    public DataAccessExceptionVoidTemplate(U u) {
        this.u = u;
    }
    
    public void tryMethod() {
        try {
            doMethod();
        } catch (Exception ex) {
            throw new RecoverableDataAccessException("Operation 'create' failed." + ex.getMessage(), ex);
        }
    }
    
    public abstract void doMethod();
    
    public U getU() {
        return u;
    }
        

}
