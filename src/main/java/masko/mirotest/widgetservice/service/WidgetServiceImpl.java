package masko.mirotest.widgetservice.service;

import masko.mirotest.widgetservice.model.WidgetEntity;
import masko.mirotest.widgetservice.repository.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

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
    public List<WidgetEntity> getAllWidgets(Integer page, Integer limit) {
        return widgetRepository.findAll(PageRequest.of(page, limit)).getContent();
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
    public Optional<WidgetEntity> updateWidget(WidgetEntity widgetEntity) {
        if (widgetRepository.existsById(widgetEntity.getId()))
            return Optional.of(widgetRepository.save(widgetEntity));
        else
            return Optional.empty();
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
