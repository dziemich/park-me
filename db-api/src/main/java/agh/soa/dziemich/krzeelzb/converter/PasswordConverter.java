package agh.soa.dziemich.krzeelzb.converter;

import javax.persistence.AttributeConverter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordConverter implements AttributeConverter<String, String> {

  @Override
  public String convertToDatabaseColumn(String password) {

    byte[] hash = DigestUtils.sha1(password.getBytes());

    String base64String = Base64.encodeBase64String(hash);

    return base64String;
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    return dbData;
  }
}
