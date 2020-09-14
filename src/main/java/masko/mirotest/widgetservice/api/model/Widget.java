package masko.mirotest.widgetservice.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.threeten.bp.OffsetDateTime;

import javax.validation.constraints.*;

/**
 * Widget
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-09-12T08:21:10.023Z[GMT]")
public class Widget {
  @JsonProperty("id")
  @NotNull
  private Long id;

  @JsonProperty("x")
  @NotNull
  private Integer x;

  @JsonProperty("y")
  @NotNull
  private Integer y;

  @JsonProperty("z")
  @NotNull
  private Integer z;

  @JsonProperty("modified")
  @NotNull
  private OffsetDateTime modified;

  public Widget(@NotNull Long id, @NotNull Integer x, @NotNull Integer y, @NotNull Integer z, @NotNull OffsetDateTime modified) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.z = z;
    this.modified = modified;
  }

  public Widget(@NotNull Widget widget) {
    this.id = widget.id;
    this.x = widget.x;
    this.y = widget.y;
    this.z = widget.z;
    this.modified = widget.modified;
  }

  public Widget id(Long id) {
    this.id = id;
    return this;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Widget x(Integer x) {
    this.x = x;
    return this;
  }

  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Widget y(Integer y) {
    this.y = y;
    return this;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  public Widget z(Integer z) {
    this.z = z;
    return this;
  }

  public Integer getZ() {
    return z;
  }

  public void setZ(Integer z) {
    this.z = z;
  }

  public OffsetDateTime getModified() {
    return modified;
  }

  public void setModified(OffsetDateTime modified) {
    this.modified = modified;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Widget widget = (Widget) o;
    return Objects.equals(this.id, widget.id) &&
            Objects.equals(this.x, widget.x) &&
            Objects.equals(this.y, widget.y) &&
            Objects.equals(this.z, widget.z) &&
            Objects.equals(this.modified, widget.modified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, x, y, z, modified);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Widget {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    x: ").append(toIndentedString(x)).append("\n");
    sb.append("    y: ").append(toIndentedString(y)).append("\n");
    sb.append("    z: ").append(toIndentedString(z)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
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
