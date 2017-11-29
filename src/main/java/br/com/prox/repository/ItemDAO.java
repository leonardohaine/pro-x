package br.com.prox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prox.model.Item;

@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {

}
