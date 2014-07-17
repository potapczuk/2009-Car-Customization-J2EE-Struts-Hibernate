package br.com.diegoliveira.indiana.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.diegoliveira.indiana.entity.Marca;
import org.hibernate.Query;

/**
 * Classe DAO da entidade Marca
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class MarcaDAO extends AbstractDAO {

    /**
     * Método que salva uma marca
     * @param marca Marca
     */
	public void salva(Marca marca) throws SQLException {
        this.getSession().beginTransaction();
        this.getSession().save(marca);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que atualiza uma marca
     * @param marca Marca
     */
	public void atualiza(Marca marca) throws SQLException {
        if (marca.getId() <= 0) {
			// TODO Reportar erro
			return;
		}
        this.getSession().beginTransaction();
        this.getSession().update(marca);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que salva ou atualiza uma marca
     * @param marca Marca
     */
	public void salvaOuAtualiza(Marca marca) throws SQLException {
        if (marca.getId() > 0) {
			atualiza(marca);
		} else {
            salva(marca);
        }
	}

    /**
     * Método que remove uma marca
     * @param marca Marca
     */
	public void remove(Marca marca) throws SQLException {
		this.getSession().beginTransaction();
        Marca marcaAt = (Marca) this.getSession().get(Marca.class, marca.getId());
        if(marcaAt != null)
            this.getSession().delete(marcaAt);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que procura por uma marca
     * @param marca Marca
     * @return List<Marca>
     */
	public List<Marca> procura(Marca marca) throws SQLException {
		if (marca != null) {
			if (marca.getId() >= 0) {
				List<Marca> lista = new ArrayList<Marca>();
				lista.add(procuraById(marca.getId()));
				return lista;
			}
		}
		return null;
	}

    /**
     * Método que procura uma marca pelo id
     * @param id int
     * @return Marca
     */
	public Marca procuraById(int id) throws SQLException {
		if (id <= 0)
			return null;
		getSession().beginTransaction();
        Marca marca = (Marca) getSession().get(Marca.class, id);
        getSession().getTransaction().commit();
        return marca;
	}

    /**
     * Método que pega uma lista de todas as marcas
     * @return List<Marca>
     */
	public List<Marca> getLista() throws SQLException {
        getSession().beginTransaction();
        List<Marca> lista = getSession().createQuery("from Marca order by nome").list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega uma lista de todas as marcas
     * @param inicio int
     * @param quantidade int
     * @return List<Marca>
     */
	public List<Marca> getListaPaginada(int inicio, int quantidade)
			throws SQLException {
		getSession().beginTransaction();
        Query query = getSession().createQuery("from Marca");
        query.setFirstResult(inicio);
        query.setMaxResults(quantidade);
        List<Marca> lista = query.list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega o último id inserido no Banco de Dados
     * @return int - id
     */
	public int getLastID() throws SQLException {
		return getLastID("marca");
	}

    /**
     * Método que pega o número de registros no Banco de Dados
     * @return int - quantidade
     */
	public int count() throws SQLException {
		return count("marca");
	}
}