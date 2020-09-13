package masko.mirotest.widgetservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Widget Entity
 */
@Entity
public class WidgetEntity {
  @NotNull
  @Id
  private Long id = null;

  @NotNull
  private Integer x = null;

  @NotNull
  private Integer y = null;

  @NotNull
  private Integer z = null;

  public WidgetEntity(Long id, Integer x, Integer y, Integer z) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public WidgetEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  public Integer getZ() {
    return z;
  }

  public void setZ(Integer z) {
    this.z = z;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WidgetEntity widgetEntity = (WidgetEntity) o;
    return Objects.equals(this.id, widgetEntity.id) &&
        Objects.equals(this.x, widgetEntity.x) &&
        Objects.equals(this.y, widgetEntity.y) &&
        Objects.equals(this.z, widgetEntity.z);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, x, y, z);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Widget {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
