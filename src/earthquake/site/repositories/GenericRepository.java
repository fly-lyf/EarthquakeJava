package earthquake.site.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
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

    void deleteAll();
}
