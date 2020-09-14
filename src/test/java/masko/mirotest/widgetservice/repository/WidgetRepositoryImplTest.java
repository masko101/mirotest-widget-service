package masko.mirotest.widgetservice.repository;

import masko.mirotest.widgetservice.api.model.Widget;
import masko.mirotest.widgetservice.model.WidgetEntity;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WidgetRepositoryImplTest {

    WidgetRepositoryImpl widgetRepository = new WidgetRepositoryImpl();

    private static ArrayList<WidgetEntity> testWidgetEntities = new ArrayList<WidgetEntity>();
    static {
        testWidgetEntities.add(new WidgetEntity(1L, 321, 102, 10));
        testWidgetEntities.add(new WidgetEntity(2L, 123, 201, 20));
        testWidgetEntities.add(new WidgetEntity(3L, 643, 164, 30));
    }

    @BeforeEach
    void initialize() {
        widgetRepository.deleteAll();
        for (WidgetEntity widgetEntity : testWidgetEntities) {
            widgetRepository.save(widgetEntity);
        }
    }

    @Test
    void findAllWithPaging() {
/*
        Iterable<WidgetEntity> foundWidgets = widgetRepository.findAll();
        Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

        Assert.assertEquals("Widget 1 Id", 1, widgetEntityIterator.next().getId().intValue());
        Assert.assertEquals("Widget 2 Id", 2, widgetEntityIterator.next().getId().intValue());
        Assert.assertEquals("Widget 3 Id", 3, widgetEntityIterator.next().getId().intValue());
        Assert.assertFalse("No More Widgets", widgetEntityIterator.hasNext());
*/
    }

    @Nested
    @DisplayName("Save Feature")
    class SaveFeature {
        @Test
        void saveNewNullId() {
            WidgetEntity newWidget =
                    widgetRepository.save(new WidgetEntity(null, 821, 464, 40, null));
            Assert.assertEquals("New Widget id", 4L, newWidget.getId().longValue());
            Assert.assertEquals("New Widget x", 821, newWidget.getX().intValue());
            Assert.assertEquals("New Widget y", 464, newWidget.getY().intValue());
            Assert.assertEquals("New Widget z", 40, newWidget.getZ().intValue());
            Assert.assertNotNull("New Widget modified not null", newWidget.getModified());

            Iterable<WidgetEntity> foundWidgets = widgetRepository.findAll();
            Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

            WidgetEntity widget1 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 1 Id", 1, widget1.getId().intValue());
            WidgetEntity widget2 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 2 Id", 2, widget2.getId().intValue());
            WidgetEntity widget3 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 3 Id", 3, widget3.getId().intValue());
            WidgetEntity widget4 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 4 Id", 4, widget4.getId().intValue());
            Assert.assertFalse("No More Widgets", widgetEntityIterator.hasNext());
        }

        @Test
        void saveNewUniqueId() {
            WidgetEntity newWidget =
                    widgetRepository.save(new WidgetEntity(6L, 821, 464, 40, null));
            Assert.assertEquals("New Widget id", 6L, newWidget.getId().longValue());
            Assert.assertEquals("New Widget x", 821, newWidget.getX().intValue());
            Assert.assertEquals("New Widget y", 464, newWidget.getY().intValue());
            Assert.assertEquals("New Widget z", 40, newWidget.getZ().intValue());
            Assert.assertNotNull("New Widget modified not null", newWidget.getModified());

            Iterable<WidgetEntity> foundWidgets = widgetRepository.findAll();
            Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

            WidgetEntity widget1 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 1 Id", 1, widget1.getId().intValue());
            WidgetEntity widget2 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 2 Id", 2, widget2.getId().intValue());
            WidgetEntity widget3 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 3 Id", 3, widget3.getId().intValue());
            WidgetEntity widget4 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 4 Id", 6, widget4.getId().intValue());
            Assert.assertFalse("No More Widgets", widgetEntityIterator.hasNext());
        }

        @Test
        void saveExistingIdUniqueZIndex() {
            WidgetEntity newWidget =
                    widgetRepository.save(new WidgetEntity(2L, 821, 464, 40, null));
            Assert.assertEquals("New Widget id", 2L, newWidget.getId().longValue());
            Assert.assertEquals("New Widget x", 821, newWidget.getX().intValue());
            Assert.assertEquals("New Widget y", 464, newWidget.getY().intValue());
            Assert.assertEquals("New Widget z", 40, newWidget.getZ().intValue());
            Assert.assertNotNull("New Widget modified not null", newWidget.getModified());

            Iterable<WidgetEntity> foundWidgets = widgetRepository.findAll();
            Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

            WidgetEntity widget1 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 1 Id", 1, widget1.getId().intValue());
            Assert.assertEquals("Widget 1 Id", 10, widget1.getZ().intValue());
            WidgetEntity widget2 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 2 Id", 3, widget2.getId().intValue());
            Assert.assertEquals("Widget 2 Id", 30, widget2.getZ().intValue());
            WidgetEntity widget3 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 3 Id", 2, widget3.getId().intValue());
            Assert.assertEquals("Widget 3 Id", 40, widget3.getZ().intValue());
            Assert.assertFalse("No More Widgets", widgetEntityIterator.hasNext());
        }

        @Test
        void saveExistingIdExistingZIndex() {
            WidgetEntity newWidget =
                    widgetRepository.save(new WidgetEntity(3L, 821, 464, 20, null));
            Assert.assertEquals("New Widget id", 3L, newWidget.getId().longValue());
            Assert.assertEquals("New Widget x", 821, newWidget.getX().intValue());
            Assert.assertEquals("New Widget y", 464, newWidget.getY().intValue());
            Assert.assertEquals("New Widget z", 20, newWidget.getZ().intValue());
            Assert.assertNotNull("New Widget modified not null", newWidget.getModified());

            Iterable<WidgetEntity> foundWidgets = widgetRepository.findAll();
            Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

            WidgetEntity widget1 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 1 Id", 1, widget1.getId().intValue());
            Assert.assertEquals("Widget 1 Id", 10, widget1.getZ().intValue());
            WidgetEntity widget2 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 2 Id", 3, widget2.getId().intValue());
            Assert.assertEquals("Widget 2 Id", 20, widget2.getZ().intValue());
            WidgetEntity widget3 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 3 Id", 2, widget3.getId().intValue());
            Assert.assertEquals("Widget 3 Id", 21, widget3.getZ().intValue());
            Assert.assertFalse("No More Widgets", widgetEntityIterator.hasNext());
        }

        @Test
        void saveAll() {
            ArrayList<WidgetEntity> saveWidgets = new ArrayList<>();
            saveWidgets.add(new WidgetEntity(3L, 821, 464, 20, null));
            saveWidgets.add(new WidgetEntity(4L, 821, 464, 40, null));
            Iterable<WidgetEntity> newWidgets = widgetRepository.saveAll(saveWidgets);
            Iterator<WidgetEntity> newWidgetsIterator = newWidgets.iterator();
            WidgetEntity newWidget1 = newWidgetsIterator.next();
            Assert.assertEquals("Widget 1 Id", 3, newWidget1.getId().intValue());
            Assert.assertEquals("Widget 1 Id", 20, newWidget1.getZ().intValue());
            WidgetEntity newWidget2 = newWidgetsIterator.next();
            Assert.assertEquals("Widget 2 Id", 4, newWidget2.getId().intValue());
            Assert.assertEquals("Widget 2 Id", 40, newWidget2.getZ().intValue());

            Iterable<WidgetEntity> foundWidgets = widgetRepository.findAll();
            Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

            WidgetEntity widget1 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 1 Id", 1, widget1.getId().intValue());
            Assert.assertEquals("Widget 1 Id", 10, widget1.getZ().intValue());
            WidgetEntity widget2 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 2 Id", 3, widget2.getId().intValue());
            Assert.assertEquals("Widget 2 Id", 20, widget2.getZ().intValue());
            WidgetEntity widget3 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 3 Id", 2, widget3.getId().intValue());
            Assert.assertEquals("Widget 3 Id", 21, widget3.getZ().intValue());
            WidgetEntity widget4 = widgetEntityIterator.next();
            Assert.assertEquals("Widget 4 Id", 4, widget4.getId().intValue());
            Assert.assertEquals("Widget 4 Id", 40, widget4.getZ().intValue());
            Assert.assertFalse("No More Widgets", widgetEntityIterator.hasNext());
        }

    }

    @Test
    void findById() {
        Optional<WidgetEntity> foundWidgetOption = widgetRepository.findById(2L);
        Assert.assertTrue(foundWidgetOption.isPresent());
        WidgetEntity foundWidget = foundWidgetOption.get();
        Assert.assertEquals("New Widget id", 2L, foundWidget.getId().longValue());
        Assert.assertEquals("New Widget x", 123, foundWidget.getX().intValue());
        Assert.assertEquals("New Widget y", 201, foundWidget.getY().intValue());
        Assert.assertEquals("New Widget z", 20, foundWidget.getZ().intValue());
        Assert.assertNotNull("New Widget modified not null", foundWidget.getModified());
    }

    @Test
    void existsById() {
        Boolean widgetExists = widgetRepository.existsById(2L);
        Assert.assertTrue(widgetExists);
    }

    @Test
    void findAll() {
        Iterable<WidgetEntity> foundWidgets = widgetRepository.findAll();
        Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

        WidgetEntity widget1 = widgetEntityIterator.next();
        Assert.assertEquals("Widget 1 Id", 1, widget1.getId().intValue());
        Assert.assertEquals("Widget 1 Id", 10, widget1.getZ().intValue());
        WidgetEntity widget2 = widgetEntityIterator.next();
        Assert.assertEquals("Widget 2 Id", 2, widget2.getId().intValue());
        Assert.assertEquals("Widget 2 Id", 20, widget2.getZ().intValue());
        WidgetEntity widget3 = widgetEntityIterator.next();
        Assert.assertEquals("Widget 3 Id", 3, widget3.getId().intValue());
        Assert.assertEquals("Widget 3 Id", 30, widget3.getZ().intValue());
        Assert.assertFalse("No More Widgets", widgetEntityIterator.hasNext());
    }

    @Test
    void findAllById() {

        Iterable<WidgetEntity> foundWidgets = widgetRepository.findAllById(Arrays.asList(1L,3L));
        Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

        WidgetEntity widget1 = widgetEntityIterator.next();
        Assert.assertEquals("Widget 1 Id", 1, widget1.getId().intValue());
        Assert.assertEquals("Widget 1 Id", 10, widget1.getZ().intValue());
        WidgetEntity widget2 = widgetEntityIterator.next();
        Assert.assertEquals("Widget 2 Id", 3, widget2.getId().intValue());
        Assert.assertEquals("Widget 2 Id", 30, widget2.getZ().intValue());
        Assert.assertFalse("No More Widgets", widgetEntityIterator.hasNext());
    }

    @Test
    void count() {
        Assert.assertEquals("Widget count", 3L, widgetRepository.count());
    }

    @Test
    void deleteById() {
        widgetRepository.deleteById(2L);

        Iterable<WidgetEntity> foundWidgets = widgetRepository.findAll();
        Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

        WidgetEntity widget1 = widgetEntityIterator.next();
        Assert.assertEquals("Widget 1 Id", 1, widget1.getId().intValue());
        WidgetEntity widget3 = widgetEntityIterator.next();
        Assert.assertEquals("Widget 3 Id", 3, widget3.getId().intValue());
    }

    @Test
    void delete() {
        widgetRepository.delete(new WidgetEntity(2L, 821, 464, 40, null));

        Iterable<WidgetEntity> foundWidgets = widgetRepository.findAll();
        Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

        WidgetEntity widget1 = widgetEntityIterator.next();
        Assert.assertEquals("Widget 1 Id", 1, widget1.getId().intValue());
        WidgetEntity widget3 = widgetEntityIterator.next();
        Assert.assertEquals("Widget 3 Id", 3, widget3.getId().intValue());
    }

    @Test
    void deleteAll() {
        widgetRepository.deleteAll();
        Assert.assertEquals("Widget count after delete all", 0L, widgetRepository.count());
    }

    @Test
    void deleteAllSpecified() {
        widgetRepository.deleteAll(Arrays.asList(
                    new WidgetEntity(1L, 0, 0, 0, null),
                    new WidgetEntity(3L, 0, 0, 0, null)
                )
        );
        Iterable<WidgetEntity> foundWidgets = widgetRepository.findAll();
        Iterator<WidgetEntity> widgetEntityIterator = foundWidgets.iterator();

        WidgetEntity widget1 = widgetEntityIterator.next();
        Assert.assertEquals("Widget 1 Id", 2, widget1.getId().intValue());
    }
}