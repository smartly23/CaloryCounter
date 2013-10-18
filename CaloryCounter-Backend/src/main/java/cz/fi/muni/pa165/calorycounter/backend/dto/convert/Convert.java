package cz.fi.muni.pa165.calorycounter.backend.dto.convert;

/**
 * Interface for conversion between DTO and entity back and forth.
 *
 * @author Martin Pasko (smartly23)
 */
public interface Convert<T, U> {

    public T fromDtoToEntity(U dto);

    public U fromEntityToDto(T entity);
}
