package agh.soa.dziemich.krzeelzb.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter("localDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
    if (modelValue == null) {
      return "";
    }

    if (modelValue instanceof LocalDateTime) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String format = ((LocalDateTime) modelValue).format(formatter);
      System.out.println(format);
      return format;
    } else {
      throw new ConverterException(new FacesMessage(modelValue + " is not a valid LocalDateTime"));
    }
  }

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
    if (submittedValue == null || submittedValue.isEmpty()) {
      return null;
    }

    try {
      return DateTimeFormatter.ofPattern(submittedValue, Locale.getDefault());
    } catch (DateTimeParseException e) {
      throw new ConverterException(
          new FacesMessage(submittedValue + " is not a valid local date time"), e);
    }
  }
}
