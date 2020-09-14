package masko.mirotest.widgetservice;

import masko.mirotest.widgetservice.api.model.Widget;
import masko.mirotest.widgetservice.api.model.WidgetCreate;
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
import java.util.Arrays;
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

    private static ArrayList<WidgetEntity> testWidgetEntities = new ArrayList<WidgetEntity>();
    static {
        testWidgetEntities.add(new WidgetEntity(1L, 100, 122, 10));
        testWidgetEntities.add(new WidgetEntity(2L, 53, 322, 15));
        testWidgetEntities.add(new WidgetEntity(3L, 532, 501, 20));
        testWidgetEntities.add(new WidgetEntity(4L, 321, 15, 25));
    }

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @BeforeEach
    public void initialize() {
        widgetRepository.deleteAll();
        for (WidgetEntity widgetEntity : testWidgetEntities) {
            widgetRepository.save(widgetEntity);
        }
    }

    @Test
    public void testGetAllWidgets() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Widget[]> response = restTemplate.exchange(getRootUrl() + "/widgets", HttpMethod.GET, entity, Widget[].class);
        Widget[] allWidgets = response.getBody();
        Assert.assertNotNull(allWidgets);
        Assert.assertEquals("Status OK", 200, response.getStatusCode().value());
        Assert.assertEquals("Widgets Found", testWidgetEntities.size(), allWidgets.length);
        Assert.assertEquals("Widget 1 Id", 1, allWidgets[0].getId().intValue());
        Assert.assertEquals("Widget 2 Id", 2, allWidgets[1].getId().intValue());
        Assert.assertEquals("Widget 3 Id", 3, allWidgets[2].getId().intValue());
        Assert.assertEquals("Widget 4 Id", 4, allWidgets[3].getId().intValue());
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
        HttpEntity<WidgetCreate> entity = new HttpEntity<WidgetCreate>(new WidgetCreate(222, 444, 20), headers);
        ResponseEntity<Widget> response = restTemplate.exchange(getRootUrl() + "/widgets", HttpMethod.POST, entity, Widget.class);
        Widget widget = response.getBody();
        Assert.assertEquals("Status OK No Content", 201, response.getStatusCode().value());
        Assert.assertNotNull(widget);
        System.out.println(widget);
        Assert.assertEquals("Widget 5 ID",  5L, widget.getId().longValue());
        Assert.assertEquals("Widget 5 X", 222, widget.getX().intValue());
        Assert.assertEquals("Widget 5 Y", 444, widget.getY().intValue());
        Assert.assertEquals("Widget 5 Z", 20, widget.getZ().intValue());
    }

    @Test
    public void testCreateWidgetExistingZIndex() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<WidgetCreate> entity = new HttpEntity<WidgetCreate>(new WidgetCreate(222,444,15), headers);
        ResponseEntity<Widget> response = restTemplate.exchange(getRootUrl() + "/widgets", HttpMethod.POST, entity, Widget.class);
        Widget widget = response.getBody();
        Assert.assertEquals("Status OK", 201, response.getStatusCode().value());
        Assert.assertNotNull(widget);
        System.out.println(widget);
        Assert.assertEquals("Widget 5 ID",  5L, widget.getId().longValue());
        Assert.assertEquals("Widget 5 X", 222, widget.getX().intValue());
        Assert.assertEquals("Widget 5 Y", 444, widget.getY().intValue());
        Assert.assertEquals("Widget 5 Z", 15, widget.getZ().intValue());

        HttpEntity<String> getAllEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<Widget[]> getAllResponse = restTemplate.exchange(getRootUrl() + "/widgets", HttpMethod.GET, entity, Widget[].class);
        Assert.assertEquals("Status OK", 200, getAllResponse.getStatusCode().value());
        Assert.assertNotNull(getAllResponse.getBody());
        Widget[] allWidgets = getAllResponse.getBody();
/*
        System.out.println("---- " + getAllResponse.getBody()[1].getClass().getName());
        for (Object o : getAllResponse.getBody()) {
            if (o instanceof Widget)
                allWidgets.add((Widget) o);
        }
*/
        System.out.println(" Create ---- " + Arrays.asList(allWidgets));
        Assert.assertEquals("Widget 1 Z", 10, allWidgets[0].getZ().intValue());
        Assert.assertEquals("Widget 2 Z", 15, allWidgets[1].getZ().intValue());
        Assert.assertEquals("Widget 2 Z", 16, allWidgets[2].getZ().intValue());
        Assert.assertEquals("Widget 3 Z", 21, allWidgets[3].getZ().intValue());
        Assert.assertEquals("Widget 4 Z", 26, allWidgets[4].getZ().intValue());
    }

    @Test
    public void testUpdatePostUniqueZIndex() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<WidgetCreate> entity = new HttpEntity<WidgetCreate>(new WidgetCreate(707,709,40), headers);
        ResponseEntity<Widget> response = restTemplate.exchange(getRootUrl() + "/widgets/3", HttpMethod.PUT, entity, Widget.class);
        Widget widget = response.getBody();
        Assert.assertEquals("Status OK", 200, response.getStatusCode().value());
        Assert.assertNotNull(widget);
        Assert.assertEquals("Widget 1 ID", 3, widget.getId().intValue());
        Assert.assertEquals("Widget 1 X", 707, widget.getX().intValue());
        Assert.assertEquals("Widget 1 Y", 709, widget.getY().intValue());
        Assert.assertEquals("Widget 1 Z", 40, widget.getZ().intValue());

        HttpEntity<String> getAllEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<Widget[]> getAllResponse = restTemplate.exchange(getRootUrl() + "/widgets", HttpMethod.GET, entity, Widget[].class);
        Assert.assertEquals("Status OK", 200, getAllResponse.getStatusCode().value());
        Assert.assertNotNull(getAllResponse.getBody());
        Widget[] allWidgets = getAllResponse.getBody();
        System.out.println(" Update ---- " + Arrays.asList(allWidgets));
        Assert.assertEquals("Widget 1 Id", 1, allWidgets[0].getId().intValue());
        Assert.assertEquals("Widget 1 Z", 10, allWidgets[0].getZ().intValue());
        Assert.assertEquals("Widget 2 Id", 2, allWidgets[1].getId().intValue());
        Assert.assertEquals("Widget 2 Z", 15, allWidgets[1].getZ().intValue());
        Assert.assertEquals("Widget 3 Id", 4, allWidgets[2].getId().intValue());
        Assert.assertEquals("Widget 3 Z", 25, allWidgets[2].getZ().intValue());
        Assert.assertEquals("Widget 4 Id", 3, allWidgets[3].getId().intValue());
        Assert.assertEquals("Widget 4 Z", 40, allWidgets[3].getZ().intValue());
    }

    @Test
    public void testUpdatePostExistingZIndex() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<WidgetCreate> entity = new HttpEntity<WidgetCreate>(new WidgetCreate(707,709,15), headers);
        ResponseEntity<Widget> response = restTemplate.exchange(getRootUrl() + "/widgets/3", HttpMethod.PUT, entity, Widget.class);
        Widget widget = response.getBody();
        Assert.assertEquals("Status OK", 200, response.getStatusCode().value());
        Assert.assertNotNull(widget);
        Assert.assertEquals("Widget 1 ID", 3, widget.getId().intValue());
        Assert.assertEquals("Widget 1 X", 707, widget.getX().intValue());
        Assert.assertEquals("Widget 1 Y", 709, widget.getY().intValue());
        Assert.assertEquals("Widget 1 Z", 15, widget.getZ().intValue());

        HttpEntity<String> getAllEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<Widget[]> getAllResponse = restTemplate.exchange(getRootUrl() + "/widgets", HttpMethod.GET, entity, Widget[].class);
        Assert.assertEquals("Status OK", 200, getAllResponse.getStatusCode().value());
        Assert.assertNotNull(getAllResponse.getBody());
        Widget[] allWidgets = getAllResponse.getBody();
        System.out.println(" Update ---- " + Arrays.asList(allWidgets));
        Assert.assertEquals("Widget 1 Id", 1, allWidgets[0].getId().intValue());
        Assert.assertEquals("Widget 1 Z", 10, allWidgets[0].getZ().intValue());
        Assert.assertEquals("Widget 2 Id", 3, allWidgets[1].getId().intValue());
        Assert.assertEquals("Widget 2 Z", 15, allWidgets[1].getZ().intValue());
        Assert.assertEquals("Widget 3 Id", 2, allWidgets[2].getId().intValue());
        Assert.assertEquals("Widget 3 Z", 16, allWidgets[2].getZ().intValue());
        Assert.assertEquals("Widget 4 Id", 4, allWidgets[3].getId().intValue());
        Assert.assertEquals("Widget 4 Z", 26, allWidgets[3].getZ().intValue());
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

        ResponseEntity<Widget> responseNotFound = restTemplate.exchange(getRootUrl() + "/widgets/1", HttpMethod.DELETE, entity, Widget.class);
        Assert.assertEquals("Status Not Found", 404, responseNotFound.getStatusCode().value());
        Assert.assertNull(responseNotFound.getBody());

//        //Test second delete fails
//        Assert.assertThrows(HttpClientErrorException.class,
//                () ->
//                    ResponseEntity<Widget> responseNotFound = restTemplate.exchange(getRootUrl() + "/widgets/1", HttpMethod.DELETE, entity, Widget.class);
//                });
    }
}
