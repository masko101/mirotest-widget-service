package masko.mirotest.widgetservice.model;

import org.threeten.bp.OffsetDateTime;

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

    @NotNull
    private OffsetDateTime modified;

    public WidgetEntity(@NotNull Long id, @NotNull Integer x, @NotNull Integer y, @NotNull Integer z, @NotNull OffsetDateTime modified) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.modified = modified;
    }

    public WidgetEntity(@NotNull Long id, @NotNull Integer x, @NotNull Integer y, @NotNull Integer z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.modified = OffsetDateTime.now();
    }

    public WidgetEntity(@NotNull WidgetEntity widgetEntity) {
        this.id = widgetEntity.id;
        this.x = widgetEntity.x;
        this.y = widgetEntity.y;
        this.z = widgetEntity.z;
        this.modified = widgetEntity.modified;
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
        WidgetEntity widget = (WidgetEntity) o;
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
