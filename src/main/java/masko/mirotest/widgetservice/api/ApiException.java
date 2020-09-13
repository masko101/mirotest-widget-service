package masko.mirotest.widgetservice.api;

@javax.annotation.Generated(value = "masko.mirotest.widgetservice.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-09-12T08:21:10.023Z[GMT]")
public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
