package de.hhu.chicken.service.klausurenservice;

import static de.hhu.chicken.service.klausurenservice.KlausurTemplates.beispielklausur;
import static de.hhu.chicken.service.klausurenservice.KlausurTemplates.zweiBeispielklausuren;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hhu.chicken.domain.klausur.Klausur;
import de.hhu.chicken.service.klausurservice.KlausurService;
import de.hhu.chicken.service.repositories.KlausurRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KlausurServiceTest {

  @Test
  @DisplayName("Der Service ruft das Repository auf und gibt die richtigen Klausurtermine zurück")
  void test_1() {
    KlausurRepository repo = mock(KlausurRepository.class);
    KlausurService service = new KlausurService(repo);
    List<Klausur> beispielklausuren = zweiBeispielklausuren();
    when(repo.alleKlausuren()).thenReturn(beispielklausuren);

    List<Klausur> klausurtermine = service.alleKlausuren();

    verify(repo).alleKlausuren();
    assertThat(klausurtermine).isEqualTo(beispielklausuren);
  }

  @Test
  @DisplayName("Der Service ruft das Repository auf und speichert eine Klausur")
  void test_2() {
    KlausurRepository repo = mock(KlausurRepository.class);
    KlausurService service = new KlausurService(repo);
    Klausur beispielklausur = beispielklausur();

    service.klausurSpeichern(beispielklausur);

    verify(repo).klausurSpeichern(beispielklausur);
  }

  @Test
  @DisplayName("Der Service ruft das Repository auf und holt eine Klausur nach UUID")
  void test_3() {
    KlausurRepository repo = mock(KlausurRepository.class);
    KlausurService service = new KlausurService(repo);
    Klausur beispielklausur = beispielklausur();
    UUID uuid = beispielklausur.getUuid();
    when(repo.findKlausurByUuid(uuid)).thenReturn(beispielklausur);

    Klausur klausurByUuid = service.findKlausurByUuid(uuid);

    assertThat(klausurByUuid).isEqualTo(beispielklausur);
    verify(repo).findKlausurByUuid(uuid);
  }
}
