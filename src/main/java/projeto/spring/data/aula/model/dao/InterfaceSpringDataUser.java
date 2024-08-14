package projeto.spring.data.aula.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.aula2.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {

	// Cria uma consulta mais customizada, nesse caso por nome
	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNome(String nome);

	// Só retornará se for realmente igual a busca, não retorna lista, apenas o
	// objeto
	@Transactional
	@Query(value = "select p from UsuarioSpringData p where p.nome = :paranome")
	public UsuarioSpringData buscaPorNomeParam(@Param("paranome") String paranome);

	@Modifying // Anotação para fazer modificação no banco
	@Transactional // anotação para fazer uma transação no banco
	@Query("delete from UsuarioSpringData u where u.nome = ?1")
	public void deletePorNome(String nome);

	// update condicional, atualizando e-mail onde o nome for igual ao nome enviado
	@Transactional
	@Modifying
	@Query("update UsuarioSpringData u set u.email = ?1 where u.nome = ?2")
	public void updateEmailPorNome(String email, String nome);

}
