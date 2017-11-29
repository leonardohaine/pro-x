package br.com.prox.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.prox.model.Item;
import br.com.prox.repository.ItemDAO;
import br.com.prox.service.ItemService;
import br.com.prox.util.FacesMessages;
import lombok.Data;

@Named
//@ViewScoped
@Data
public class ItemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ItemService service;
	
	@Autowired
	private ItemDAO itens;
	
	@Autowired
	private FacesMessages messages;
	
	private Item item = new Item();
	private Item itemSelecionado;
	private List<Item> todosItens;
	
	
	public void prepararNovoCadastro() {
		item = new Item();
	}
	
	public void salvar() {
		try {
			service.salvar(item);
			consultar();
			
			messages.info("Item salvo com sucesso!");
			FacesContext.getCurrentInstance().getExternalContext().redirect("Item.xhtml");
			//RequestContext.getCurrentInstance().update(Arrays.asList("frm:msgs", "frm:item-table"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String editar(Item item){
		
		System.out.println("Chamou editar");
		System.out.println("Descricao: " + item.getDescricao());
		this.item = item;
		
		return "gestaoItem.xhtml";
	}

	public void excluir(){
		
		service.excluir(itemSelecionado);
		itemSelecionado = null;
		consultar();
		
		messages.info("Item excluído com sucesso!");
	}
	
	public void consultar(){
		todosItens = itens.findAll();
	}
	
}
