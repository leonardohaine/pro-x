package br.com.prox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prox.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
	
	public Usuario findByLoginAndSenha(String login, String senha);
	public Usuario findByLogin(String login);

}
