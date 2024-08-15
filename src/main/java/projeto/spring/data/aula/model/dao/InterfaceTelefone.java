package projeto.spring.data.aula.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.spring.data.aula2.Telefone;

@Repository
public interface InterfaceTelefone extends CrudRepository<Telefone, Long> {

}
