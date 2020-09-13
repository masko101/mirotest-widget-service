package masko.mirotest.widgetservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import masko.mirotest.widgetservice.api.model.Widget;
import masko.mirotest.widgetservice.model.WidgetEntity;
import masko.mirotest.widgetservice.service.WidgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "masko.mirotest.widgetservice.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-09-12T08:21:10.023Z[GMT]")
@RestController
@Validated
public class WidgetsApiController implements WidgetsApi {

    private static final Logger log = LoggerFactory.getLogger(WidgetsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final WidgetService widgetService;

    @Autowired
    public WidgetsApiController(ObjectMapper objectMapper, HttpServletRequest request, WidgetService widgetService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.widgetService = widgetService;
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public void handleConstraintViolationError(HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.BAD_REQUEST.value());
//    }
//
    private List<Widget> widgetFromWidgetEntities(List<WidgetEntity> widgetEntities) {
        return widgetEntities.stream().map(widgetEntity -> widgetFromWidgetEntity(widgetEntity))
                .collect(Collectors.toList());
    }

    private Widget widgetFromWidgetEntity(WidgetEntity widgetEntity) {
        return new Widget(widgetEntity.getId(), widgetEntity.getX(), widgetEntity.getY(), widgetEntity.getZ());
    }

    private WidgetEntity widgetEntityFromWidget(Widget widget) {
        return new WidgetEntity(widget.getId(), widget.getX(), widget.getY(), widget.getZ());
    }

    public ResponseEntity<List<Widget>> widgetsGet(@NotNull @Valid @RequestParam(value = "skip", required = false) Integer skip, @NotNull @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<Widget> foundWidgets = widgetFromWidgetEntities(widgetService.getAllWidgets());
            return new ResponseEntity<List<Widget>>(foundWidgets, HttpStatus.OK);
        }

        return new ResponseEntity<List<Widget>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Widget> widgetsIdDelete(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Optional<WidgetEntity> widgetEntityOptional = widgetService.deleteWidgetById(id);
            if (widgetEntityOptional.isPresent()) {
                WidgetEntity widgetEntity = widgetEntityOptional.get();
                Widget apiWidget = widgetFromWidgetEntity(widgetEntity);
                return new ResponseEntity<Widget>(apiWidget, HttpStatus.OK);
            } else {
                return new ResponseEntity<Widget>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<Widget>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Widget> widgetsIdGet(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Optional<WidgetEntity> widgetOption = widgetService.getWidgetById(id);
            if (widgetOption.isPresent()) {
                WidgetEntity widgetEntity = widgetOption.get();
                Widget apiWidget = widgetFromWidgetEntity(widgetEntity);
                return new ResponseEntity<Widget>(apiWidget, HttpStatus.OK);
            } else {
                return new ResponseEntity<Widget>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<Widget>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Widget> widgetsPost(@Valid @RequestBody Widget widget) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            WidgetEntity createdWidgetEntity = widgetService.createWidget(widgetEntityFromWidget(widget));
            return new ResponseEntity<Widget>(widgetFromWidgetEntity(createdWidgetEntity), HttpStatus.CREATED);
        }
        return new ResponseEntity<Widget>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Widget> widgetsIdPut(@PathVariable("id") Long id, @Valid @RequestBody Widget widget) {
        String accept = request.getHeader("Accept");
        if (id != widget.getId()) {
            return new ResponseEntity<Widget>(HttpStatus.BAD_REQUEST);
        }
        if (accept != null && accept.contains("application/json")) {
            WidgetEntity widgetEntity = widgetService.updateWidget(widgetEntityFromWidget(widget));
            return new ResponseEntity<Widget>(widgetFromWidgetEntity(widgetEntity), HttpStatus.OK);
        }

        return new ResponseEntity<Widget>(HttpStatus.NOT_IMPLEMENTED);
    }

}
