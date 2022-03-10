package de.hhu.chicken.service.klausurservice;

import de.hhu.chicken.domain.klausur.Klausur;
import de.hhu.chicken.service.repositories.KlausurRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class KlausurService {

  KlausurRepository repo;

  public KlausurService(
      KlausurRepository repo) {
    this.repo = repo;
  }

  public List<Klausur> alleKlausuren() {
    return repo.alleKlausuren();
  }

  public void klausurSpeichern(Klausur klausur) {
    repo.klausurSpeichern(klausur);
  }

  public Klausur findKlausurByUuid(UUID uuid) {
    return repo.findKlausurByUuid(uuid);
  }
}