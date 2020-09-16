package masko.mirotest.widgetservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import masko.mirotest.widgetservice.api.model.Widget;
import masko.mirotest.widgetservice.api.model.WidgetCreate;
import masko.mirotest.widgetservice.api.model.WidgetMapper;
import masko.mirotest.widgetservice.model.WidgetEntity;
import masko.mirotest.widgetservice.service.WidgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<Widget>> widgetsGet(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            //Defaults not working
            if (page == null)
                page = 0;
            if (limit == null)
                limit = 10;
            List<Widget> foundWidgets = WidgetMapper.widgetsFromWidgetEntities(widgetService.getAllWidgets(page, limit));
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
                Widget apiWidget = WidgetMapper.widgetFromWidgetEntity(widgetEntity);
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
                Widget apiWidget = WidgetMapper.widgetFromWidgetEntity(widgetEntity);
                return new ResponseEntity<Widget>(apiWidget, HttpStatus.OK);
            } else {
                return new ResponseEntity<Widget>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<Widget>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Widget> widgetsPost(@RequestBody WidgetCreate widget) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            WidgetEntity createdWidgetEntity = widgetService.createWidget(WidgetMapper.widgetEntityFromWidgetCreate(widget));
            return new ResponseEntity<Widget>(WidgetMapper.widgetFromWidgetEntity(createdWidgetEntity), HttpStatus.CREATED);
        }
        return new ResponseEntity<Widget>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Widget> widgetsIdPut(@PathVariable("id") Long id, @RequestBody WidgetCreate widget) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Optional<WidgetEntity> widgetEntityOptional =
                    widgetService.updateWidget(WidgetMapper.widgetEntityFromWidgetCreate(id, widget));
            if (widgetEntityOptional.isPresent())
                return new ResponseEntity<Widget>(WidgetMapper.widgetFromWidgetEntity(widgetEntityOptional.get()),
                        HttpStatus.OK);
            else
                return new ResponseEntity<Widget>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Widget>(HttpStatus.NOT_IMPLEMENTED);
    }

}
