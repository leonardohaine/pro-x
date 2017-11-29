package br.com.prox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prox.model.Role;



public interface RoleDAO extends JpaRepository<Role, Long> {

	public List<Role> findByRoleContaining(String login);
	
	
}
