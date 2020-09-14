package masko.mirotest.widgetservice.api.model;

import masko.mirotest.widgetservice.model.WidgetEntity;

import java.util.List;
import java.util.stream.Collectors;

public class WidgetMapper {
    public static List<Widget> widgetsFromWidgetEntities(List<WidgetEntity> widgetEntities) {
        return widgetEntities.stream().map(WidgetMapper::widgetFromWidgetEntity)
                .collect(Collectors.toList());
    }

    public static Widget widgetFromWidgetEntity(WidgetEntity widgetEntity) {
        return new Widget(widgetEntity.getId(), widgetEntity.getX(), widgetEntity.getY(), widgetEntity.getZ(), widgetEntity.getModified());
    }

    public static WidgetEntity widgetEntityFromWidgetCreate(Widget widget) {
        return new WidgetEntity(widget.getId(), widget.getX(), widget.getY(), widget.getZ(), widget.getModified());
    }

    public static WidgetEntity widgetEntityFromWidgetCreate(Long id, WidgetCreate widget) {
        return new WidgetEntity(id, widget.getX(), widget.getY(), widget.getZ());
    }

    public static WidgetEntity widgetEntityFromWidgetCreate(WidgetCreate widget) {
        return new WidgetEntity(null, widget.getX(), widget.getY(), widget.getZ());
    }
}
