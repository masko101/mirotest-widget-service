package masko.mirotest.widgetservice.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

/**
 * GeneralError
 */
@Validated
@javax.annotation.Generated(value = "masko.mirotest.widgetservice.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-09-12T08:21:10.023Z[GMT]")


public class GeneralError   {
  @JsonProperty("message")
  private String message = null;

  public GeneralError message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  **/
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeneralError generalError = (GeneralError) o;
    return Objects.equals(this.message, generalError.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeneralError {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
