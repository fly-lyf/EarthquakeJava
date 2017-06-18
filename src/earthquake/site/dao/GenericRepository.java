package earthquake.site.dao;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * //通用仓库接口
 * @param <ID>
 * @param <E>
 */
@Validated
public interface GenericRepository<ID extends Serializable, E>
{
    @NotNull
    Iterable<E> getAll();

    E getById(@NotNull ID id);

    void add(@NotNull E entity);

    void update(@NotNull E entity);

    void delete(@NotNull E entity);

    void deleteById(@NotNull ID id);

}
