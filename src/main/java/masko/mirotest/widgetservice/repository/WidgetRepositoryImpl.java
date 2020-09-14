package masko.mirotest.widgetservice.repository;

import masko.mirotest.widgetservice.api.model.Widget;
import masko.mirotest.widgetservice.model.WidgetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.threeten.bp.OffsetDateTime;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class WidgetRepositoryImpl implements WidgetRepository {

    private static TreeMap<Long, WidgetEntity> widgetsById = new TreeMap<Long, WidgetEntity>();
    private static TreeMap<Integer, WidgetEntity> widgetsByZIndex = new TreeMap<Integer, WidgetEntity>();

    @Override
    public Page<WidgetEntity> findAll(Pageable pageable) {
        List<WidgetEntity> widgetEntities = findAllList();
//        List<Widget> pageList =
//                widgets.subList(pageable.getPageNumber() * pageable.getPageSize(), pageable.getPageSize());
        return new PageImpl<WidgetEntity>(widgetEntities);
    }

    @Override
    public <S extends WidgetEntity> S save(S s) {
        Long id = (s.getId() == null) ? getLastId() + 1 : s.getId();
        Integer z = (s.getZ() == null) ? getLastZIndex() + 1 : s.getZ();

        WidgetEntity updatedWidget = new WidgetEntity(id, s.getX(), s.getY(), z, OffsetDateTime.now());
        WidgetEntity replacedWidget = widgetsById.put(updatedWidget.getId(), updatedWidget);
        if (replacedWidget != null)
            widgetsByZIndex.remove(replacedWidget.getZ());

        if (widgetsByZIndex.containsKey(z)) {
            Set<Integer> keysToShift =
                    widgetsByZIndex.keySet().stream().filter(zIndex -> zIndex >= z).collect(Collectors.toSet());
            for (Integer key : keysToShift) {
                WidgetEntity widgetToShift = widgetsByZIndex.remove(key);
                widgetToShift.setZ(key + 1);
                widgetsByZIndex.put(key + 1, widgetToShift);
            }
        }
        widgetsByZIndex.put(updatedWidget.getZ(), updatedWidget);

        return (S) updatedWidget;
    }

    private Long getLastId() {
        return (widgetsById.isEmpty()) ? 0 : widgetsById.lastKey();
    }

    private Integer getLastZIndex() {
        return (widgetsByZIndex.isEmpty()) ? 1 : widgetsByZIndex.lastKey();
    }

    private Integer getFirstZIndex() {
        return (widgetsByZIndex.isEmpty()) ? 1 : widgetsByZIndex.firstKey();
    }

    @Override
    public <S extends WidgetEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        for (S s : iterable) {
            save(s);
        }
        return iterable;
    }

    @Override
    public Optional<WidgetEntity> findById(Long id) {
        WidgetEntity foundWidget = widgetsById.get(id);
        if (foundWidget == null)
            return Optional.empty();
        else
            return Optional.of(new WidgetEntity(foundWidget));
    }

    @Override
    public boolean existsById(Long id) {
        return widgetsById.containsKey(id);
    }

    @Override
    public Iterable<WidgetEntity> findAll() {
        return findAllList();
    }

    private List<WidgetEntity> findAllList() {
        return widgetsByZIndex.values().stream().map(WidgetEntity::new).collect(Collectors.toList());
    }

    @Override
    public Iterable<WidgetEntity> findAllById(Iterable<Long> iterable) {
        List<WidgetEntity> foundWidgetEntities = new ArrayList<>();
        for (Long id : iterable) {
            foundWidgetEntities.add(new WidgetEntity(widgetsById.get(id)));
        }
        return foundWidgetEntities;
    }

    @Override
    public long count() {
        return widgetsById.size();
    }

    @Override
    public void deleteById(Long id) {
        Optional<WidgetEntity> foundWidgetOption = findById(id);
        if  (foundWidgetOption.isPresent()) {
            WidgetEntity foundWidget = foundWidgetOption.get();
            widgetsById.remove(id);
            widgetsByZIndex.remove(foundWidget.getZ());
        }
    }

    @Override
    public void delete(WidgetEntity widgetEntity) {
        deleteById(widgetEntity.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends WidgetEntity> iterable) {
        for (WidgetEntity widget : iterable) {
            deleteById(widget.getId());
        }
    }

    @Override
    public void deleteAll() {
        widgetsById.clear();
        widgetsByZIndex.clear();
    }
}
