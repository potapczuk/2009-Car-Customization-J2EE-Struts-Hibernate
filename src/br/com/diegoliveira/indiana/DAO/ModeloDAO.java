package br.com.diegoliveira.indiana.DAO;

import br.com.diegoliveira.indiana.entity.Carro;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.diegoliveira.indiana.entity.Modelo;
import org.hibernate.Query;

/**
 * Classe DAO da entidade Modelo
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class ModeloDAO extends AbstractDAO {

    /**
     * Método que salva uma modelo
     * @param modelo Modelo
     */
	public void salva(Modelo modelo) throws SQLException {
        this.getSession().beginTransaction();
        this.getSession().save(modelo);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que atualiza uma modelo
     * @param modelo Modelo
     */
	public void atualiza(Modelo modelo) throws SQLException {
        if (modelo.getId() <= 0) {
			// TODO Reportar erro
			return;
		}
        this.getSession().beginTransaction();
        this.getSession().update(modelo);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que salva ou atualiza uma modelo
     * @param modelo Modelo
     */
	public void salvaOuAtualiza(Modelo modelo) throws SQLException {
        if (modelo.getId() > 0) {
			atualiza(modelo);
		} else {
            salva(modelo);
        }
	}

    /**
     * Método que remove uma modelo
     * @param modelo Modelo
     */
	public void remove(Modelo modelo) throws SQLException {
		this.getSession().beginTransaction();
        Modelo modeloAt = (Modelo) this.getSession().get(Modelo.class, modelo.getId());
        if(modeloAt != null)
            this.getSession().delete(modeloAt);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que procura por uma modelo
     * @param modelo Modelo
     * @return List<Modelo>
     */
	public List<Modelo> procura(Modelo modelo) throws SQLException {
		if (modelo != null) {
			if (modelo.getId() >= 0) {
				List<Modelo> lista = new ArrayList<Modelo>();
				lista.add(procuraById(modelo.getId()));
				return lista;
			}
		}
		return null;
	}

    /**
     * Método que procura uma modelo pelo id
     * @param id int
     * @return Modelo
     */
	public Modelo procuraById(int id) throws SQLException {
		if (id <= 0)
			return null;
		getSession().beginTransaction();
        Modelo modelo = (Modelo) getSession().get(Modelo.class, id);
        getSession().getTransaction().commit();
        return modelo;
	}

    /**
     * Método que procura uma modelo pelo carro
     * @param id int
     * @return Modelo
     */
	public List<Modelo> procuraByCarro(Carro carro) throws SQLException {
		if(carro == null)
			return null;
		getSession().beginTransaction();

        Query q = getSession().getNamedQuery("modelo.carro");
        q.setEntity("carro", carro);
        List<Modelo> lista = q.list();

        getSession().getTransaction().commit();
        return lista;
	}

    /**
     * Método que pega uma lista de todas as modelos
     * @return List<Modelo>
     */
	public List<Modelo> getLista() throws SQLException {
        getSession().beginTransaction();
        List<Modelo> lista = getSession().createQuery("from Modelo").list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega uma lista de todas as modelos
     * @param inicio int
     * @param quantidade int
     * @return List<Modelo>
     */
	public List<Modelo> getListaPaginada(int inicio, int quantidade)
			throws SQLException {
		getSession().beginTransaction();
        Query query = getSession().createQuery("from Modelo");
        query.setFirstResult(inicio);
        query.setMaxResults(quantidade);
        List<Modelo> lista = query.list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega o último id inserido no Banco de Dados
     * @return int - id
     */
	public int getLastID() throws SQLException {
		return getLastID("modelo");
	}

    /**
     * Método que pega o número de registros no Banco de Dados
     * @return int - quantidade
     */
	public int count() throws SQLException {
		return count("modelo");
	}
}