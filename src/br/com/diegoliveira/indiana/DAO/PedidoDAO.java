package br.com.diegoliveira.indiana.DAO;

import br.com.diegoliveira.indiana.entity.Carro;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.diegoliveira.indiana.entity.Pedido;
import org.hibernate.Query;

/**
 * Classe DAO da entidade Pedido
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class PedidoDAO extends AbstractDAO {

    /**
     * Método que salva uma pedido
     * @param pedido Pedido
     */
	public void salva(Pedido pedido) throws SQLException {
        this.getSession().beginTransaction();
        this.getSession().save(pedido);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que atualiza uma pedido
     * @param pedido Pedido
     */
	public void atualiza(Pedido pedido) throws SQLException {
        if (pedido.getId() <= 0) {
			// TODO Reportar erro
			return;
		}
        this.getSession().beginTransaction();
        this.getSession().update(pedido);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que salva ou atualiza uma pedido
     * @param pedido Pedido
     */
	public void salvaOuAtualiza(Pedido pedido) throws SQLException {
        if (pedido.getId() > 0) {
			atualiza(pedido);
		} else {
            salva(pedido);
        }
	}

    /**
     * Método que remove uma pedido
     * @param pedido Pedido
     */
	public void remove(Pedido pedido) throws SQLException {
		this.getSession().beginTransaction();
        Pedido pedidoAt = (Pedido) this.getSession().get(Pedido.class, pedido.getId());
        if(pedidoAt != null)
            this.getSession().delete(pedidoAt);
        this.getSession().getTransaction().commit();
	}

    /**
     * Método que procura por uma pedido
     * @param pedido Pedido
     * @return List<Pedido>
     */
	public List<Pedido> procura(Pedido pedido) throws SQLException {
		if (pedido != null) {
			if (pedido.getId() >= 0) {
				List<Pedido> lista = new ArrayList<Pedido>();
				lista.add(procuraById(pedido.getId()));
				return lista;
			}
		}
		return null;
	}

    /**
     * Método que procura uma pedido pelo id
     * @param id int
     * @return Pedido
     */
	public Pedido procuraById(int id) throws SQLException {
		if (id <= 0)
			return null;
		getSession().beginTransaction();
        Pedido pedido = (Pedido) getSession().get(Pedido.class, id);
        getSession().getTransaction().commit();
        return pedido;
	}

    /**
     * Método que procura uma pedido pelo carro
     * @param id int
     * @return Pedido
     */
	public List<Pedido> procuraByCarro(Carro carro) throws SQLException {
		if(carro == null)
			return null;
		getSession().beginTransaction();

        Query q = getSession().getNamedQuery("pedido.por.carro");
        q.setEntity("nome", carro.getNome());
        List<Pedido> lista = q.list();

        getSession().getTransaction().commit();
        return lista;
	}

    /**
     * Método que pega uma lista de todas as pedidos
     * @return List<Pedido>
     */
	public List<Pedido> getLista() throws SQLException {
        getSession().beginTransaction();
        List<Pedido> lista = getSession().createQuery("from Pedido order by nome").list();
        getSession().getTransaction().commit();

		return lista;
	}

    /**
     * Método que pega uma lista de todas as pedidos
     * @param inicio int
     * @param quantidade int
     * @return List<Pedido>
     */
	public List<Pedido> getListaPaginada(int inicio, int quantidade)
			throws SQLException {
		getSession().beginTransaction();
        Query query = getSession().createQuery("from Pedido");
        query.setFirstResult(inicio);
        query.setMaxResults(quantidade);
        List<Pedido> lista = query.list();
        getSession().getTransaction().commit();

		return lista;
	}

	public ArrayList<String> getEstados(){
		ArrayList<String> estados = new ArrayList<String>();

		estados.add("Acre");
		estados.add("Alagoas");
		estados.add("Amapá");
		estados.add("Amazonas");
		estados.add("Bahia");
		estados.add("Ceará");
		estados.add("Distrito Federal");
		estados.add("Goiás");
		estados.add("Espírito Santo");
		estados.add("Maranhão");
		estados.add("Mato Grosso");
		estados.add("Mato Grosso do Sul");
		estados.add("Minas Gerais");
		estados.add("Pará");
		estados.add("Paraiba");
		estados.add("Paraná");
		estados.add("Pernambuco");
		estados.add("Piauí");
		estados.add("Rio de Janeiro");
		estados.add("Rio Grande do Norte");
		estados.add("Rio Grande do Sul");
		estados.add("Rondônia");
		estados.add("Rorâima");
		estados.add("São Paulo");
		estados.add("Santa Catarina");
		estados.add("Sergipe");
		estados.add("Tocantins");

		return estados;
	}

    /**
     * Método que pega o último id inserido no Banco de Dados
     * @return int - id
     */
	public int getLastID() throws SQLException {
		return getLastID("pedido");
	}

    /**
     * Método que pega o número de registros no Banco de Dados
     * @return int - quantidade
     */
	public int count() throws SQLException {
		return count("pedido");
	}
}