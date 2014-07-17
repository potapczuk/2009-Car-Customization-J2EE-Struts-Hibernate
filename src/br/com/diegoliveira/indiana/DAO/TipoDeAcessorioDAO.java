package br.com.diegoliveira.indiana.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.diegoliveira.indiana.entity.TipoDeAcessorio;
import org.hibernate.Query;

/**
 * Classe DAO da entidade TipoDeAcessorio
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class TipoDeAcessorioDAO extends AbstractDAO {

    /**
     * Método que salva uma tipoDeAcessorio
     * @param tipoDeAcessorio TipoDeAcessorio
     */
	public void salva(TipoDeAcessorio tipoDeAcessorio) throws SQLException {
        this.getSession().beginTransaction();
        this.getSession().save(tipoDeAcessorio);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que atualiza uma tipoDeAcessorio
     * @param tipoDeAcessorio TipoDeAcessorio
     */
	public void atualiza(TipoDeAcessorio tipoDeAcessorio) throws SQLException {
        if (tipoDeAcessorio.getId() <= 0) {
			// TODO Reportar erro
			return;
		}
        this.getSession().beginTransaction();
        this.getSession().update(tipoDeAcessorio);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que salva ou atualiza uma tipoDeAcessorio
     * @param tipoDeAcessorio TipoDeAcessorio
     */
	public void salvaOuAtualiza(TipoDeAcessorio tipoDeAcessorio) throws SQLException {
        if (tipoDeAcessorio.getId() > 0) {
			atualiza(tipoDeAcessorio);
		} else {
            salva(tipoDeAcessorio);
        }
	}

    /**
     * Método que remove uma tipoDeAcessorio
     * @param tipoDeAcessorio TipoDeAcessorio
     */
	public void remove(TipoDeAcessorio tipoDeAcessorio) throws SQLException {
		this.getSession().beginTransaction();
        TipoDeAcessorio tipoDeAcessorioAt = (TipoDeAcessorio) this.getSession().get(TipoDeAcessorio.class, tipoDeAcessorio.getId());
        if(tipoDeAcessorioAt != null)
            this.getSession().delete(tipoDeAcessorioAt);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que procura por uma tipoDeAcessorio
     * @param tipoDeAcessorio TipoDeAcessorio
     * @return List<TipoDeAcessorio>
     */
	public List<TipoDeAcessorio> procura(TipoDeAcessorio tipoDeAcessorio) throws SQLException {
		if (tipoDeAcessorio != null) {
			if (tipoDeAcessorio.getId() >= 0) {
				List<TipoDeAcessorio> lista = new ArrayList<TipoDeAcessorio>();
				lista.add(procuraById(tipoDeAcessorio.getId()));
				return lista;
			}
		}
		return null;
	}

    /**
     * Método que procura uma tipoDeAcessorio pelo id
     * @param id int
     * @return TipoDeAcessorio
     */
	public TipoDeAcessorio procuraById(int id) throws SQLException {
		if (id <= 0)
			return null;
		getSession().beginTransaction();
        TipoDeAcessorio tipoDeAcessorio = (TipoDeAcessorio) getSession().get(TipoDeAcessorio.class, id);
        getSession().getTransaction().commit();
        return tipoDeAcessorio;
	}

    /**
     * Método que pega uma lista de todas as tipoDeAcessorios
     * @return List<TipoDeAcessorio>
     */
	public List<TipoDeAcessorio> getLista() throws SQLException {
        getSession().beginTransaction();
        List<TipoDeAcessorio> lista = getSession().createQuery("from TipoDeAcessorio order by nome").list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega uma lista de todas as tipoDeAcessorios
     * @param inicio int
     * @param quantidade int
     * @return List<TipoDeAcessorio>
     */
	public List<TipoDeAcessorio> getListaPaginada(int inicio, int quantidade)
			throws SQLException {
		getSession().beginTransaction();
        Query query = getSession().createQuery("from TipoDeAcessorio");
        query.setFirstResult(inicio);
        query.setMaxResults(quantidade);
        List<TipoDeAcessorio> lista = query.list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega o último id inserido no Banco de Dados
     * @return int - id
     */
	public int getLastID() throws SQLException {
		return getLastID("tipoDeAcessorio");
	}

    /**
     * Método que pega o número de registros no Banco de Dados
     * @return int - quantidade
     */
	public int count() throws SQLException {
		return count("tipoDeAcessorio");
	}
}