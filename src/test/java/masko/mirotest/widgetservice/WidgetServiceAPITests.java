package masko.mirotest.widgetservice;

import masko.mirotest.widgetservice.api.model.Widget;
import masko.mirotest.widgetservice.api.model.WidgetMapper;
import masko.mirotest.widgetservice.model.WidgetEntity;
import masko.mirotest.widgetservice.repository.WidgetRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = WidgetServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WidgetServiceAPITests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WidgetRepository widgetRepository;

    @LocalServerPort
    private int port;

    private static ArrayList<WidgetEntity> widgetEntities = new ArrayList<WidgetEntity>();
    static {
        widgetEntities.add(new WidgetEntity(1L, 100, 122, 10));
        widgetEntities.add(new WidgetEntity(2L, 53, 322, 15));
        widgetEntities.add(new WidgetEntity(3L, 532, 501, 20));
        widgetEntities.add(new WidgetEntity(4L, 321, 15, 25));
    }

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @BeforeEach
    public void initialize() {
        for (WidgetEntity widgetEntity : widgetEntities) {
            widgetRepository.save(widgetEntity);
        }
    }

    @Test
    public void testGetAllWidgets() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<ArrayList> response = restTemplate.exchange(getRootUrl() + "/widgets", HttpMethod.GET, entity, ArrayList.class);
        List widgets = response.getBody();
        Assert.assertNotNull(widgets);
        Assert.assertEquals("Widgets Found", 5, widgets.size());
    }

    @Test
    public void testGetWidgetById() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Widget> response = restTemplate.exchange(getRootUrl() + "/widgets/1", HttpMethod.GET, entity, Widget.class);
        Widget widget = response.getBody();
        Assert.assertEquals("Status OK", 200, response.getStatusCode().value());
        Assert.assertNotNull(widget);
        Assert.assertEquals("Widget 1 ID", 1, widget.getId().intValue());
    }

    @Test
    public void testCreateWidgetUniqueZIndex() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Widget> entity = new HttpEntity<Widget>(new Widget(8L,222,444,30), headers);
        ResponseEntity<Widget> response = restTemplate.exchange(getRootUrl() + "/widgets", HttpMethod.POST, entity, Widget.class);
        Widget widget = response.getBody();
        Assert.assertEquals("Status OK", 201, response.getStatusCode().value());
        Assert.assertNotNull(widget);
        System.out.println(widget);
        Assert.assertEquals("Widget 5 ID",  8L, widget.getId().longValue());
        Assert.assertEquals("Widget 5 X", 222, widget.getX().intValue());
        Assert.assertEquals("Widget 5 Y", 444, widget.getY().intValue());
        Assert.assertEquals("Widget 5 Z", 30, widget.getZ().intValue());
    }

    @Test
    public void testUpdatePostUniqueZIndex() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Widget> entity = new HttpEntity<Widget>(new Widget(8L,707,709,40), headers);
        ResponseEntity<Widget> response = restTemplate.exchange(getRootUrl() + "/widgets/1", HttpMethod.PUT, entity, Widget.class);
        Widget widget = response.getBody();
        Assert.assertEquals("Status OK", 200, response.getStatusCode().value());
        Assert.assertNotNull(widget);
        Assert.assertEquals("Widget 1 ID", 8, widget.getId().intValue());
        Assert.assertEquals("Widget 1 X", 707, widget.getX().intValue());
        Assert.assertEquals("Widget 1 Y", 709, widget.getY().intValue());
        Assert.assertEquals("Widget 1 Z", 40, widget.getZ().intValue());
    }

    @Test()
    public void testDeletePost() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Widget> response = restTemplate.exchange(getRootUrl() + "/widgets/1", HttpMethod.DELETE, entity, Widget.class);
        Assert.assertEquals(200, response.getStatusCode().value());
        Widget widget = response.getBody();
        Assert.assertEquals("Status OK", 200, response.getStatusCode().value());
        Assert.assertNotNull(widget);
        Assert.assertEquals("Widget 1 deleted and returned", widget.getId().intValue(), 1);

        //Test second delete fails
        Assert.assertThrows(HttpClientErrorException.class,
                () -> restTemplate.exchange(getRootUrl() + "/widgets/1", HttpMethod.DELETE, entity, Widget.class));
    }
}
