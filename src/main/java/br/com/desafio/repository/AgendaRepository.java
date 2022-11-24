package br.com.desafio.repository;

import br.com.desafio.domain.Agenda;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AgendaRepository extends ReactiveCrudRepository<Agenda, String> {

}
