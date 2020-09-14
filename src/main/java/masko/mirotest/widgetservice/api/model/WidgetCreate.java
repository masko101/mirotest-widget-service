package masko.mirotest.widgetservice.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * WidgetCreate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-09-13T12:43:13.230Z[GMT]")
public class WidgetCreate   {
    @NotNull
    @JsonProperty("x")
    private Integer x = null;

    @NotNull
    @JsonProperty("y")
    private Integer y = null;

    @JsonProperty("z")
    private Integer z = null;

    public WidgetCreate(@NotNull Integer x, @NotNull Integer y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public WidgetCreate x(Integer x) {
        this.x = x;
        return this;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public WidgetCreate y(Integer y) {
        this.y = y;
        return this;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public WidgetCreate z(Integer z) {
        this.z = z;
        return this;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WidgetCreate widgetCreate = (WidgetCreate) o;
        return Objects.equals(this.x, widgetCreate.x) &&
                Objects.equals(this.y, widgetCreate.y) &&
                Objects.equals(this.z, widgetCreate.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WidgetCreate {\n");

        sb.append("    x: ").append(toIndentedString(x)).append("\n");
        sb.append("    y: ").append(toIndentedString(y)).append("\n");
        sb.append("    z: ").append(toIndentedString(z)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}