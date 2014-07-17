package br.com.diegoliveira.indiana.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.diegoliveira.indiana.entity.Usuario;
import org.hibernate.Query;

/**
 * Classe DAO da entidade Usuario
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class UsuarioDAO extends AbstractDAO {

    /**
     * Método que salva uma usuario
     * @param usuario Usuario
     */
	public void salva(Usuario usuario) throws SQLException {
        this.getSession().beginTransaction();
        this.getSession().save(usuario);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que atualiza uma usuario
     * @param usuario Usuario
     */
	public void atualiza(Usuario usuario) throws SQLException {
        if (usuario.getId() <= 0) {
			// TODO Reportar erro
			return;
		}
        this.getSession().beginTransaction();
        this.getSession().update(usuario);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que salva ou atualiza uma usuario
     * @param usuario Usuario
     */
	public void salvaOuAtualiza(Usuario usuario) throws SQLException {
        if (usuario.getId() > 0) {
			atualiza(usuario);
		} else {
            salva(usuario);
        }
	}

    /**
     * Método que remove uma usuario
     * @param usuario Usuario
     */
	public void remove(Usuario usuario) throws SQLException {
		this.getSession().beginTransaction();
        Usuario usuarioAt = (Usuario) this.getSession().get(Usuario.class, usuario.getId());
        if(usuarioAt != null)
            this.getSession().delete(usuarioAt);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que procura por uma usuario
     * @param usuario Usuario
     * @return List<Usuario>
     */
	public List<Usuario> procura(Usuario usuario) throws SQLException {
		if (usuario != null) {
			if (usuario.getId() >= 0) {
				List<Usuario> lista = new ArrayList<Usuario>();
				lista.add(procuraById(usuario.getId()));
				return lista;
			}
		}
		return null;
	}

    /**
     * Método que procura uma usuario pelo id
     * @param id int
     * @return Usuario
     */
	public Usuario procuraById(int id) throws SQLException {
		if (id <= 0)
			return null;
		getSession().beginTransaction();
        Usuario usuario = (Usuario) getSession().get(Usuario.class, id);
        getSession().getTransaction().commit();
        return usuario;
	}

    /**
     * Método que procura uma usuario pelo username
     * @param id int
     * @return Usuario
     */
	public Usuario procuraByUsername(String username) throws SQLException {
		getSession().beginTransaction();
        Query q = getSession().getNamedQuery("usuario.getByUsername");
        q.setString("username", username);
        List<Usuario> lista = q.list();
        
        Usuario usuario = null;
        if(lista.size() > 0){
            usuario = lista.get(0);
        }
        getSession().getTransaction().commit();
        return usuario;
	}

    /**
     * Método que faz a verificacao de um usuario
     * @param id int
     * @return Usuario
     */
	public boolean verificaUsuario(String username, String password) throws SQLException {
		getSession().beginTransaction();

        Query q = getSession().getNamedQuery("usuario.verifica");
        q.setString("username", username);
        q.setString("password", password);
        List<Usuario> lista = q.list();

        getSession().getTransaction().commit();
        return (lista != null && (lista.size() > 0));
	}

    /**
     * Método que pega uma lista de todas as usuarios
     * @return List<Usuario>
     */
	public List<Usuario> getLista() throws SQLException {
        getSession().beginTransaction();
        List<Usuario> lista = getSession().createQuery("from Usuario order by nome").list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega uma lista de todas as usuarios
     * @param inicio int
     * @param quantidade int
     * @return List<Usuario>
     */
	public List<Usuario> getListaPaginada(int inicio, int quantidade)
			throws SQLException {
		getSession().beginTransaction();
        Query query = getSession().createQuery("from Usuario");
        query.setFirstResult(inicio);
        query.setMaxResults(quantidade);
        List<Usuario> lista = query.list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega o último id inserido no Banco de Dados
     * @return int - id
     */
	public int getLastID() throws SQLException {
		return getLastID("usuario");
	}

    /**
     * Método que pega o número de registros no Banco de Dados
     * @return int - quantidade
     */
	public int count() throws SQLException {
		return count("usuario");
	}
}