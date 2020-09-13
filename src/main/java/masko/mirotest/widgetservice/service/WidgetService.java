package masko.mirotest.widgetservice.service;

import masko.mirotest.widgetservice.model.WidgetEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface WidgetService {

    public List<WidgetEntity> getAllWidgets();

    public Optional<WidgetEntity> getWidgetById(Long id);

    public WidgetEntity createWidget(WidgetEntity widgetEntity);

    public WidgetEntity updateWidget(WidgetEntity widgetEntity);

    public Optional<WidgetEntity> deleteWidgetById(Long id);
}
