package masko.mirotest.widgetservice.service;

import masko.mirotest.widgetservice.model.WidgetEntity;
import masko.mirotest.widgetservice.repository.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Validated
@Service("WidgetService")
public class WidgetServiceImpl implements WidgetService {

    WidgetRepository widgetRepository;

    @Autowired
    public WidgetServiceImpl(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WidgetEntity> getAllWidgets() {
        return StreamSupport.stream(widgetRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WidgetEntity> getWidgetById(Long id) {
        return  widgetRepository.findById(id);
    }

    @Override
    @Transactional()
    public WidgetEntity createWidget(WidgetEntity widgetEntity) {
        return  widgetRepository.save(widgetEntity);
    }

    @Override
    @Transactional()
    public WidgetEntity updateWidget(WidgetEntity widgetEntity) {
        return widgetRepository.save(widgetEntity);
    }

    @Override
    @Transactional()
    public Optional<WidgetEntity> deleteWidgetById(Long id) {
        Optional<WidgetEntity> widgetEntityOptional = widgetRepository.findById(id);
        if (widgetEntityOptional.isPresent()) {
            widgetRepository.deleteById(id);
        }
        return widgetEntityOptional;
    }
}
