package projeto.spring.data.aula2;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.model.dao.InterfaceSpringDataUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

	// Injeção da interface
	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;

	@Test
	public void testeInsert() {

		System.out.println("Iniciou spring com sucesso");

		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();

		usuarioSpringData.setEmail("trento.robsonn@gmail.com");
		usuarioSpringData.setLogin("teste_123");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("Gabriella Beauty");
		usuarioSpringData.setIdade(26);

		interfaceSpringDataUser.save(usuarioSpringData);

		System.out.println("Usuários Cadastrados: " + interfaceSpringDataUser.count());

	}

	@Test
	public void testeConsulta() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(5L);

		System.out.println("ID: " + usuarioSpringData.get().getId());
		System.out.println("Nome: " + usuarioSpringData.get().getNome());
		System.out.println("Idade: " + usuarioSpringData.get().getIdade());
		System.out.println("Login: " + usuarioSpringData.get().getLogin());
		System.out.println("Senha: " + usuarioSpringData.get().getSenha());
		System.out.println("Email: " + usuarioSpringData.get().getEmail());

	}

	@Test
	public void testeConsultaTodos() {

		// Iterable é uma interface genérica, serve para percorrer elementos de forma
		// sequencial, nesse caso uma lista recuperada do banco
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();

		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println("ID: " + usuarioSpringData.getId());
			System.out.println("Nome: " + usuarioSpringData.getNome());
			System.out.println("Idade: " + usuarioSpringData.getIdade());
			System.out.println("Login: " + usuarioSpringData.getLogin());
			System.out.println("Senha: " + usuarioSpringData.getSenha());
			System.out.println("Email: " + usuarioSpringData.getEmail());
			System.out.println();
		}

	}

	@Test
	public void testeUpdate() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);

		UsuarioSpringData data = usuarioSpringData.get();

		data.setIdade(35);

		interfaceSpringDataUser.save(data);

	}

	@Test
	public void testeDelete() {

		// Consulta primeiro para depois deletar
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(18L);

		interfaceSpringDataUser.delete(usuarioSpringData.get());

	}

	@Test
	public void testeConsultaNome() {

		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Gabriella");

		for (UsuarioSpringData usuarioSpringData : list) {

			System.out.println();
			System.out.println("ID: " + usuarioSpringData.getId());
			System.out.println("Nome: " + usuarioSpringData.getNome());
			System.out.println("Idade: " + usuarioSpringData.getIdade());
			System.out.println("Login: " + usuarioSpringData.getLogin());
			System.out.println("Senha: " + usuarioSpringData.getSenha());
			System.out.println("Email: " + usuarioSpringData.getEmail());

		}

	}

	@Test
	public void testeConsultaNomeParam() {

		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Gabriella Beauty");

		System.out.println("ID: " + usuarioSpringData.getId());
		System.out.println("Nome: " + usuarioSpringData.getNome());
		System.out.println("Idade: " + usuarioSpringData.getIdade());
		System.out.println("Login: " + usuarioSpringData.getLogin());
		System.out.println("Senha: " + usuarioSpringData.getSenha());
		System.out.println("Email: " + usuarioSpringData.getEmail());
		System.out.println();

	}

	@Test
	public void testeDeletePorNome() {
		interfaceSpringDataUser.deletePorNome("José");
	}

	@Test
	public void testeUpdatePorNome() {

		interfaceSpringDataUser.updateEmailPorNome("gabriellaB@gmail.com", "Gabriella Beauty");

	}

}
