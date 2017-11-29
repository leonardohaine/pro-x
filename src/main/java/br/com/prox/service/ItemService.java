package br.com.prox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prox.model.Item;
import br.com.prox.repository.ItemDAO;


@Service
public class ItemService {
	
	@Autowired
	private ItemDAO dao;
	
	@Transactional
	public void salvar(Item item){
		dao.save(item);
	}

	@Transactional
	public void excluir(Item item) {
		dao.delete(item);
	}
	
}
