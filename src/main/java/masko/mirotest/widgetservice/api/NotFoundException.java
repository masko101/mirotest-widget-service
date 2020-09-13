package masko.mirotest.widgetservice.api;

@javax.annotation.Generated(value = "masko.mirotest.widgetservice.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-09-12T08:21:10.023Z[GMT]")
public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
