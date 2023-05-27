package br.inatel.projetoc317back.repository;

import br.inatel.projetoc317back.model.Entry;
import br.inatel.projetoc317back.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EntryRepository extends JpaRepository<Entry, UUID> {

    List<Entry> findByType(Type type);

}
