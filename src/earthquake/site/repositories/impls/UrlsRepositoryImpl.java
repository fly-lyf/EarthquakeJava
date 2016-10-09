package earthquake.site.repositories.impls;

import earthquake.site.repositories.GenericJpaBaseRepository;
import earthquake.site.repositories.interfaces.UrlsRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by earthquake on 2016/10/8.
 */

@Repository
public class UrlsRepositoryImpl extends GenericJpaBaseRepository<String, String> implements UrlsRepository {

}
