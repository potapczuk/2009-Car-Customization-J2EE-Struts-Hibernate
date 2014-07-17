package br.com.diegoliveira.indiana.DAO;

import br.com.diegoliveira.indiana.entity.Carro;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.diegoliveira.indiana.entity.Pintura;
import br.com.diegoliveira.indiana.entity.TipoDePintura;
import org.hibernate.Query;

/**
 * Classe DAO da entidade Pintura
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class PinturaDAO extends AbstractDAO {

    /**
     * Método que salva uma pintura
     * @param pintura Pintura
     */
	public void salva(Pintura pintura) throws SQLException {
        this.getSession().beginTransaction();
        this.getSession().save(pintura);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que atualiza uma pintura
     * @param pintura Pintura
     */
	public void atualiza(Pintura pintura) throws SQLException {
        if (pintura.getId() <= 0) {
			// TODO Reportar erro
			return;
		}
        this.getSession().beginTransaction();
        this.getSession().update(pintura);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que salva ou atualiza uma pintura
     * @param pintura Pintura
     */
	public void salvaOuAtualiza(Pintura pintura) throws SQLException {
        if (pintura.getId() > 0) {
			atualiza(pintura);
		} else {
            salva(pintura);
        }
	}

    /**
     * Método que remove uma pintura
     * @param pintura Pintura
     */
	public void remove(Pintura pintura) throws SQLException {
		this.getSession().beginTransaction();
        Pintura pinturaAt = (Pintura) this.getSession().get(Pintura.class, pintura.getId());
        if(pinturaAt != null)
            this.getSession().delete(pinturaAt);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que procura por uma pintura
     * @param pintura Pintura
     * @return List<Pintura>
     */
	public List<Pintura> procura(Pintura pintura) throws SQLException {
		if (pintura != null) {
			if (pintura.getId() >= 0) {
				List<Pintura> lista = new ArrayList<Pintura>();
				lista.add(procuraById(pintura.getId()));
				return lista;
			}
		}
		return null;
	}

    /**
     * Método que procura uma pintura pelo id
     * @param id int
     * @return Pintura
     */
	public Pintura procuraById(int id) throws SQLException {
		if (id <= 0)
			return null;
		getSession().beginTransaction();
        Pintura pintura = (Pintura) getSession().get(Pintura.class, id);
        getSession().getTransaction().commit();
        return pintura;
	}

    /**
     * Método que procura uma pintura pelo tipo
     * @param id int
     * @return Pintura
     */
	public List<Pintura> procuraByTipoDePintura(TipoDePintura tipo) throws SQLException {
		if(tipo == null)
			return null;
		getSession().beginTransaction();

        Query q = getSession().getNamedQuery("pintura.por.tipo");
        q.setEntity("tipoDePintura", tipo);
        List<Pintura> lista = q.list();

        getSession().getTransaction().commit();
        return lista;
	}

    /**
     * Método que pega uma lista de todas as pinturas
     * @return List<Pintura>
     */
	public List<Pintura> getLista() throws SQLException {
        getSession().beginTransaction();
        List<Pintura> lista = getSession().createQuery("from Pintura order by nome").list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega uma lista de todas as pinturas
     * @param inicio int
     * @param quantidade int
     * @return List<Pintura>
     */
	public List<Pintura> getListaPaginada(int inicio, int quantidade)
			throws SQLException {
		getSession().beginTransaction();
        Query query = getSession().createQuery("from Pintura");
        query.setFirstResult(inicio);
        query.setMaxResults(quantidade);
        List<Pintura> lista = query.list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega o último id inserido no Banco de Dados
     * @return int - id
     */
	public int getLastID() throws SQLException {
		return getLastID("pintura");
	}

    /**
     * Método que pega o número de registros no Banco de Dados
     * @return int - quantidade
     */
	public int count() throws SQLException {
		return count("pintura");
	}
}