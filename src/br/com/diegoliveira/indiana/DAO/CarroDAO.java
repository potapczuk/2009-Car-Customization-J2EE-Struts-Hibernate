package br.com.diegoliveira.indiana.DAO;

import br.com.diegoliveira.indiana.entity.Acessorio;
import br.com.diegoliveira.indiana.entity.Marca;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.diegoliveira.indiana.entity.Carro;
import br.com.diegoliveira.indiana.entity.Pintura;
import br.com.diegoliveira.indiana.entity.TipoDeAcessorio;
import br.com.diegoliveira.indiana.entity.TipoDePintura;
import org.hibernate.Query;

/**
 * Classe DAO da entidade Carro
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class CarroDAO extends AbstractDAO {

    /**
     * Método que salva uma carro
     * @param carro Carro
     */
	public void salva(Carro carro) throws SQLException {
        this.getSession().beginTransaction();
        this.getSession().save(carro);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que atualiza uma carro
     * @param carro Carro
     */
	public void atualiza(Carro carro) throws SQLException {
        if (carro.getId() <= 0) {
			// TODO Reportar erro
			return;
		}
        this.getSession().beginTransaction();
        this.getSession().update(carro);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que salva ou atualiza uma carro
     * @param carro Carro
     */
	public void salvaOuAtualiza(Carro carro) throws SQLException {
        if (carro.getId() > 0) {
			atualiza(carro);
		} else {
            salva(carro);
        }
	}

    /**
     * Método que remove uma carro
     * @param carro Carro
     */
	public void remove(Carro carro) throws SQLException {
		this.getSession().beginTransaction();
        Carro carroAt = (Carro) this.getSession().get(Carro.class, carro.getId());
        if(carroAt != null)
            this.getSession().delete(carroAt);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que procura por uma carro
     * @param carro Carro
     * @return List<Carro>
     */
	public List<Carro> procura(Carro carro) throws SQLException {
		if (carro != null) {
			if (carro.getId() >= 0) {
				List<Carro> lista = new ArrayList<Carro>();
				lista.add(procuraById(carro.getId()));
				return lista;
			}
		}
		return null;
	}

    /**
     * Método que procura uma carro pelo id
     * @param id int
     * @return Carro
     */
	public Carro procuraById(int id) throws SQLException {
		if (id <= 0)
			return null;
		getSession().beginTransaction();
        Carro carro = (Carro) getSession().get(Carro.class, id);
        getSession().getTransaction().commit();
        return carro;
	}

    public List<Carro> procuraByMarca(Marca marca) {
		if(marca == null)
			return null;
		getSession().beginTransaction();

        Query q = getSession().getNamedQuery("carro.por.marca");
        q.setEntity("marca", marca);
        List<Carro> lista = q.list();

        getSession().getTransaction().commit();
        return lista;
	}

    public List<Pintura> pinturasPorTipoDePintura(TipoDePintura tipo, Carro carro) {
		if(tipo == null)
			return null;
		getSession().beginTransaction();

        Carro carroPers = (Carro) getSession().get(Carro.class, carro.getId());
        Query q = getSession().createFilter(carroPers.getPinturas(), "where this.tipoDePintura = :tipoDePintura order by this.nome");
        q.setEntity("tipoDePintura", tipo);
        List<Pintura> lista = q.list();

        getSession().getTransaction().commit();
        return lista;
	}

    public List<Acessorio> acessoriosPorTipoDeAcessorio(TipoDeAcessorio tipo, Carro carro) {
		if(tipo == null)
			return null;
		getSession().beginTransaction();

        Carro carroPers = (Carro) getSession().get(Carro.class, carro.getId());
        Query q = getSession().createFilter(carroPers.getAcessorios(), "where this.tipoDeAcessorio = :tipoDeAcessorio order by this.nome");
        q.setEntity("tipoDeAcessorio", tipo);
        List<Acessorio> lista = q.list();

        getSession().getTransaction().commit();
        return lista;
	}

    /**
     * Método que pega uma lista de todas as carros
     * @return List<Carro>
     */
	public List<Carro> getLista() throws SQLException {
        getSession().beginTransaction();
        List<Carro> lista = getSession().createQuery("from Carro").list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega uma lista de todas as carros
     * @param inicio int
     * @param quantidade int
     * @return List<Carro>
     */
	public List<Carro> getListaPaginada(int inicio, int quantidade)
			throws SQLException {
		getSession().beginTransaction();
        Query query = getSession().createQuery("from Carro");
        query.setFirstResult(inicio);
        query.setMaxResults(quantidade);
        List<Carro> lista = query.list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega o último id inserido no Banco de Dados
     * @return int - id
     */
	public int getLastID() throws SQLException {
		return getLastID("carro");
	}

    /**
     * Método que pega o número de registros no Banco de Dados
     * @return int - quantidade
     */
	public int count() throws SQLException {
		return count("carro");
	}
}