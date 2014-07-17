package br.com.diegoliveira.indiana.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.diegoliveira.indiana.entity.TipoDePintura;
import org.hibernate.Query;

/**
 * Classe DAO da entidade TipoDePintura
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class TipoDePinturaDAO extends AbstractDAO {

    /**
     * Método que salva uma tipoDePintura
     * @param tipoDePintura TipoDePintura
     */
	public void salva(TipoDePintura tipoDePintura) throws SQLException {
        this.getSession().beginTransaction();
        this.getSession().save(tipoDePintura);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que atualiza uma tipoDePintura
     * @param tipoDePintura TipoDePintura
     */
	public void atualiza(TipoDePintura tipoDePintura) throws SQLException {
        if (tipoDePintura.getId() <= 0) {
			// TODO Reportar erro
			return;
		}
        this.getSession().beginTransaction();
        this.getSession().update(tipoDePintura);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que salva ou atualiza uma tipoDePintura
     * @param tipoDePintura TipoDePintura
     */
	public void salvaOuAtualiza(TipoDePintura tipoDePintura) throws SQLException {
        if (tipoDePintura.getId() > 0) {
			atualiza(tipoDePintura);
		} else {
            salva(tipoDePintura);
        }
	}

    /**
     * Método que remove uma tipoDePintura
     * @param tipoDePintura TipoDePintura
     */
	public void remove(TipoDePintura tipoDePintura) throws SQLException {
		this.getSession().beginTransaction();
        TipoDePintura tipoDePinturaAt = (TipoDePintura) this.getSession().get(TipoDePintura.class, tipoDePintura.getId());
        if(tipoDePinturaAt != null)
            this.getSession().delete(tipoDePinturaAt);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que procura por uma tipoDePintura
     * @param tipoDePintura TipoDePintura
     * @return List<TipoDePintura>
     */
	public List<TipoDePintura> procura(TipoDePintura tipoDePintura) throws SQLException {
		if (tipoDePintura != null) {
			if (tipoDePintura.getId() >= 0) {
				List<TipoDePintura> lista = new ArrayList<TipoDePintura>();
				lista.add(procuraById(tipoDePintura.getId()));
				return lista;
			}
		}
		return null;
	}

    /**
     * Método que procura uma tipoDePintura pelo id
     * @param id int
     * @return TipoDePintura
     */
	public TipoDePintura procuraById(int id) throws SQLException {
		if (id <= 0)
			return null;
		getSession().beginTransaction();
        TipoDePintura tipoDePintura = (TipoDePintura) getSession().get(TipoDePintura.class, id);
        getSession().getTransaction().commit();
        return tipoDePintura;
	}

    /**
     * Método que pega uma lista de todas as tipoDePinturas
     * @return List<TipoDePintura>
     */
	public List<TipoDePintura> getLista() throws SQLException {
        getSession().beginTransaction();
        List<TipoDePintura> lista = getSession().createQuery("from TipoDePintura order by nome").list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega uma lista de todas as tipoDePinturas
     * @param inicio int
     * @param quantidade int
     * @return List<TipoDePintura>
     */
	public List<TipoDePintura> getListaPaginada(int inicio, int quantidade)
			throws SQLException {
		getSession().beginTransaction();
        Query query = getSession().createQuery("from TipoDePintura");
        query.setFirstResult(inicio);
        query.setMaxResults(quantidade);
        List<TipoDePintura> lista = query.list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega o último id inserido no Banco de Dados
     * @return int - id
     */
	public int getLastID() throws SQLException {
		return getLastID("tipoDePintura");
	}

    /**
     * Método que pega o número de registros no Banco de Dados
     * @return int - quantidade
     */
	public int count() throws SQLException {
		return count("tipoDePintura");
	}
}