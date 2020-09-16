package masko.mirotest.widgetservice.repository;

import masko.mirotest.widgetservice.model.WidgetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.threeten.bp.OffsetDateTime;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class WidgetRepositoryImpl implements WidgetRepository {

    private static final TreeMap<Long, WidgetEntity> widgetsById = new TreeMap<>();
    private static final TreeMap<Integer, WidgetEntity> widgetsByZIndex = new TreeMap<>();

    @Override
    public Page<WidgetEntity> findAll(Pageable pageable) {
        List<WidgetEntity> widgetEntities = findAllList();
        int widgetCount = widgetEntities.size();
        if (pageable.getOffset() < widgetCount) {
            long safeSize = Math.min(
                    Math.max(0, widgetCount - pageable.getOffset()), pageable.getPageSize());
            //Not good
            List<WidgetEntity> pageList = widgetEntities.subList((int) pageable.getOffset(),
                    (int) (pageable.getOffset() + safeSize));
            return new PageImpl<>(pageList, pageable, widgetCount);
        } else {
            return new PageImpl<>(new ArrayList<>(), pageable, widgetCount);
        }
    }

    @Override
    public <S extends WidgetEntity> S save(S s) {
        Long id = (s.getId() == null) ? getLastId() + 1 : s.getId();
        Integer z = (s.getZ() == null) ? getLastZIndex() + 1 : s.getZ();

        WidgetEntity updatedWidget = new WidgetEntity(id, s.getX(), s.getY(), z, s.getWidth(), s.getHeight(),
                OffsetDateTime.now());
        WidgetEntity replacedWidget = widgetsById.put(updatedWidget.getId(), updatedWidget);
        if (replacedWidget != null)
            widgetsByZIndex.remove(replacedWidget.getZ());

        shiftWidgetsUp(z);
        widgetsByZIndex.put(updatedWidget.getZ(), updatedWidget);

        return (S) updatedWidget;
    }

    private void shiftWidgetsUp(Integer z) {
        if (widgetsByZIndex.containsKey(z)) {
            List<Integer> keysToShift =
                    widgetsByZIndex.keySet().stream().filter(zIndex -> zIndex >= z).collect(Collectors.toList());
            ListIterator<Integer> iterator = keysToShift.listIterator(keysToShift.size());
            while (iterator.hasPrevious()) {
                Integer key = iterator.previous();
                WidgetEntity widgetToShift = widgetsByZIndex.remove(key);
                widgetToShift.setZ(key + 1);
                widgetsByZIndex.put(widgetToShift.getZ(), widgetToShift);
            }
        }
    }

    private Long getLastId() {
        return (widgetsById.isEmpty()) ? 0 : widgetsById.lastKey();
    }

    private Integer getLastZIndex() {
        return (widgetsByZIndex.isEmpty()) ? 1 : widgetsByZIndex.lastKey();
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
