package de.hhu.chicken.infrastructure.web.forms.verification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KlausurDatumVerifiziererTest {

  static KlausurDatumVerifizierer verifizierer = new KlausurDatumVerifizierer();

  @BeforeAll
  static void setup() {
    verifizierer.setStartDatum(LocalDate.of(2022, 3, 8));
    verifizierer.setEndeDatum(LocalDate.of(2022, 3, 30));
  }

  @Test
  @DisplayName("Eine Klausur vor dem Praktikumszeitraums ist nicht valide")
  void test_1() {
    LocalDate datum = LocalDate.of(2022, 3, 4);

    boolean isValid = verifizierer.isValid(datum, mock(ConstraintValidatorContext.class));

    assertThat(isValid).isFalse();
  }

  @Test
  @DisplayName("Eine Klausur nach dem Praktikumszeitraums ist nicht valide")
  void test_2() {
    LocalDate datum = LocalDate.of(2022, 4, 4);

    boolean isValid = verifizierer.isValid(datum, mock(ConstraintValidatorContext.class));

    assertThat(isValid).isFalse();
  }

  @Test
  @DisplayName("Eine Klausur im Praktikumszeitraum ist valide")
  void test_3() {
    LocalDate datum = LocalDate.of(2022, 3, 17);

    boolean isValid = verifizierer.isValid(datum, mock(ConstraintValidatorContext.class));

    assertThat(isValid).isTrue();
  }
}
