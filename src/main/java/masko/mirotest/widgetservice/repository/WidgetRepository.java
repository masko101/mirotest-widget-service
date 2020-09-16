package masko.mirotest.widgetservice.repository;

import masko.mirotest.widgetservice.model.WidgetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetRepository extends CrudRepository<WidgetEntity, Long> {
    public Page<WidgetEntity> findAll(Pageable pageable);

}
