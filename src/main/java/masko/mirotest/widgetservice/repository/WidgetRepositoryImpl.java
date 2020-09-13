package masko.mirotest.widgetservice.repository;

import masko.mirotest.widgetservice.model.WidgetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class WidgetRepositoryImpl implements WidgetRepository {

    private static TreeMap<Long, WidgetEntity> widgetsById = new TreeMap<Long, WidgetEntity>();

    @Override
    public Iterable<WidgetEntity> findAll(Sort sort) {
        return widgetsById.values();
    }

    @Override
    public Page<WidgetEntity> findAll(Pageable pageable) {
        List<WidgetEntity> widgetEntities = List.copyOf(widgetsById.values());
//        List<Widget> pageList =
//                widgets.subList(pageable.getPageNumber() * pageable.getPageSize(), pageable.getPageSize());
        return new PageImpl<WidgetEntity>(widgetEntities);
    }

    @Override
    public <S extends WidgetEntity> S save(S s) {
        if (s.getId() == null) {
            s.setId(getLastId());
        }
        widgetsById.put(s.getId(), s);
        return s;
    }

    private Long getLastId() {
        return (widgetsById.isEmpty()) ? 1 : widgetsById.lastKey() + 1;
    }

    @Override
    public <S extends WidgetEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        for (S s : iterable) {
            if (s.getId() == null)
                s.setId(getLastId());
            widgetsById.put(s.getId(), s);
        }
        return iterable;
    }

    @Override
    public Optional<WidgetEntity> findById(Long id) {
        return Optional.ofNullable(widgetsById.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return widgetsById.containsKey(id);
    }

    @Override
    public Iterable<WidgetEntity> findAll() {
        return widgetsById.values();
    }

    @Override
    public Iterable<WidgetEntity> findAllById(Iterable<Long> iterable) {
        Set<WidgetEntity> foundWidgetEntities = new HashSet<>();
        for (Long id : iterable) {
            foundWidgetEntities.add(widgetsById.get(id));
        }
        return foundWidgetEntities;
    }

    @Override
    public long count() {
        return widgetsById.size();
    }

    @Override
    public void deleteById(Long id) {
        widgetsById.remove(id);
    }

    @Override
    public void delete(WidgetEntity widgetEntity) {
        deleteById(widgetEntity.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends WidgetEntity> iterable) {
        for (WidgetEntity id : iterable) {
            widgetsById.remove(id.getId());
        }
    }

    @Override
    public void deleteAll() {
        widgetsById.clear();
    }
}
