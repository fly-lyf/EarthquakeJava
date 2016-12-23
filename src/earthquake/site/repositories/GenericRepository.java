package earthquake.site.repositories;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Validated
public interface GenericRepository<ID extends Serializable, E>
{
    @NotNull
    Iterable<E> getAll();

    E get(@NotNull ID id);

    void add(@NotNull E entity);

    void update(@NotNull E entity);

    void delete(@NotNull E entity);

    void deleteById(@NotNull ID id);
}
