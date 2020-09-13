package masko.mirotest.widgetservice.repository;

import masko.mirotest.widgetservice.model.WidgetEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetRepository extends PagingAndSortingRepository<WidgetEntity, Long> {}
