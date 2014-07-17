package br.com.diegoliveira.indiana.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.diegoliveira.indiana.entity.Acessorio;
import br.com.diegoliveira.indiana.entity.TipoDeAcessorio;
import org.hibernate.Query;

/**
 * Classe DAO da entidade Acessorio
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class AcessorioDAO extends AbstractDAO {

    /**
     * Método que salva uma acessorio
     * @param acessorio Acessorio
     */
	public void salva(Acessorio acessorio) throws SQLException {
        this.getSession().beginTransaction();
        this.getSession().save(acessorio);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que atualiza uma acessorio
     * @param acessorio Acessorio
     */
	public void atualiza(Acessorio acessorio) throws SQLException {
        if (acessorio.getId() <= 0) {
			// TODO Reportar erro
			return;
		}
        this.getSession().beginTransaction();
        this.getSession().update(acessorio);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que salva ou atualiza uma acessorio
     * @param acessorio Acessorio
     */
	public void salvaOuAtualiza(Acessorio acessorio) throws SQLException {
        if (acessorio.getId() > 0) {
			atualiza(acessorio);
		} else {
            salva(acessorio);
        }
	}

    /**
     * Método que remove uma acessorio
     * @param acessorio Acessorio
     */
	public void remove(Acessorio acessorio) throws SQLException {
		this.getSession().beginTransaction();
        Acessorio acessorioAt = (Acessorio) this.getSession().get(Acessorio.class, acessorio.getId());
        if(acessorioAt != null)
            this.getSession().delete(acessorioAt);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que procura por uma acessorio
     * @param acessorio Acessorio
     * @return List<Acessorio>
     */
	public List<Acessorio> procura(Acessorio acessorio) throws SQLException {
		if (acessorio != null) {
			if (acessorio.getId() >= 0) {
				List<Acessorio> lista = new ArrayList<Acessorio>();
				lista.add(procuraById(acessorio.getId()));
				return lista;
			}
		}
		return null;
	}

    /**
     * Método que procura uma acessorio pelo id
     * @param id int
     * @return Acessorio
     */
	public Acessorio procuraById(int id) throws SQLException {
		if (id <= 0)
			return null;
		getSession().beginTransaction();
        Acessorio acessorio = (Acessorio) getSession().get(Acessorio.class, id);
        getSession().getTransaction().commit();
        return acessorio;
	}

    /**
     * Método que procura uma acessorio pelo carro
     * @param id int
     * @return Acessorio
     */
	public List<Acessorio> procuraByTipoDeAcessorio(TipoDeAcessorio tipo) throws SQLException {
		if(tipo == null)
			return null;
		getSession().beginTransaction();

        Query q = getSession().getNamedQuery("acessorio.por.tipo");
        q.setEntity("tipoDeAcessorio", tipo);
        List<Acessorio> lista = q.list();

        getSession().getTransaction().commit();
        return lista;
	}

    /**
     * Método que pega uma lista de todas as acessorios
     * @return List<Acessorio>
     */
	public List<Acessorio> getLista() throws SQLException {
        getSession().beginTransaction();
        List<Acessorio> lista = getSession().createQuery("from Acessorio order by nome").list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega uma lista de todas as acessorios
     * @param inicio int
     * @param quantidade int
     * @return List<Acessorio>
     */
	public List<Acessorio> getListaPaginada(int inicio, int quantidade)
			throws SQLException {
		getSession().beginTransaction();
        Query query = getSession().createQuery("from Acessorio");
        query.setFirstResult(inicio);
        query.setMaxResults(quantidade);
        List<Acessorio> lista = query.list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega o último id inserido no Banco de Dados
     * @return int - id
     */
	public int getLastID() throws SQLException {
		return getLastID("acessorio");
	}

    /**
     * Método que pega o número de registros no Banco de Dados
     * @return int - quantidade
     */
	public int count() throws SQLException {
		return count("acessorio");
	}
}