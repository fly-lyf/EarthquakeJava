package earthquake.site.repositories;

import earthquake.site.SearchForm;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Validated
public interface GenericRepository<ID extends Serializable, E>
{
    @NotNull
    Iterable<E> getAll();

    E get(@NotNull ID id);

    Iterable<E> getByCondition(Map<String, String> conditions);

    void add(@NotNull E entity);

    void update(@NotNull E entity);

    void delete(@NotNull E entity);

    void deleteById(@NotNull ID id);
}
