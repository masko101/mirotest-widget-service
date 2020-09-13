package masko.mirotest.widgetservice.api.model;

import masko.mirotest.widgetservice.model.WidgetEntity;

import java.util.List;
import java.util.stream.Collectors;

public class WidgetMapper {
    public static List<Widget> widgetFromWidgetEntities(List<WidgetEntity> widgetEntities) {
        return widgetEntities.stream().map(WidgetMapper::widgetFromWidgetEntity)
                .collect(Collectors.toList());
    }

    public static Widget widgetFromWidgetEntity(WidgetEntity widgetEntity) {
        return new Widget(widgetEntity.getId(), widgetEntity.getX(), widgetEntity.getY(), widgetEntity.getZ());
    }

    public static WidgetEntity widgetEntityFromWidget(Widget widget) {
        return new WidgetEntity(widget.getId(), widget.getX(), widget.getY(), widget.getZ());
    }
}
