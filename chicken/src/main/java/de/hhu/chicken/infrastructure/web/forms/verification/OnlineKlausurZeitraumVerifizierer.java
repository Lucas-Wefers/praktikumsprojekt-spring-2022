package de.hhu.chicken.infrastructure.web.forms.verification;

import de.hhu.chicken.infrastructure.web.forms.verification.stereotypes.IsValidOnlineKlausurZeitraum;
import java.time.LocalTime;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

public class OnlineKlausurZeitraumVerifizierer implements
    ConstraintValidator<IsValidOnlineKlausurZeitraum, List<LocalTime>> {

  @Value("${praktikumszeiten.start.uhrzeit}")
  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime start;

  @Value("${praktikumszeiten.ende.uhrzeit}")
  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime ende;

  @Override
  public boolean isValid(List<LocalTime> zeitraum, ConstraintValidatorContext context) {
    if (zeitraum == null) {
      return true;
    }

    LocalTime von = zeitraum.get(0);
    LocalTime bis = zeitraum.get(1);

    if (von.isAfter(bis)) {
      return false;
    }

    if (von.minusMinutes(30L).isBefore(von)) {
      von = von.minusMinutes(30L);
    }

    return !bis.isBefore(start) && !von.isAfter(ende);
  }

  void setStart(LocalTime start) {
    this.start = start;
  }

  void setEnde(LocalTime ende) {
    this.ende = ende;
  }
}
