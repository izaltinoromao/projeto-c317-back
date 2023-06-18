package br.inatel.projetoc317back.repository;

import br.inatel.projetoc317back.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EntryRepository extends JpaRepository<Entry, UUID> {

    List<Entry> findByDateBetween(LocalDate startOfMonth, LocalDate endOfMonth);

    List<Entry> findAllByOrderByDateDesc();

    @Query(value = "SELECT e FROM Entry e WHERE e.value = (SELECT MAX(e2.value) FROM Entry e2 WHERE e2.date BETWEEN :startOfMonth and :endOfMonth)")
    Entry findMostExpansive(@Param("startOfMonth") LocalDate startOfMonth,@Param("endOfMonth") LocalDate endOfMonth);

}
