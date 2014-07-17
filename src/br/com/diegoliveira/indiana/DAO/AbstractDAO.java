package br.com.diegoliveira.indiana.DAO;

import java.sql.SQLException;

import br.com.diegoliveira.indiana.persistencia.HibernateUtil;
import org.hibernate.Session;

/**
 * Classe de DAO (Data access object) com métodos genéricos
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class AbstractDAO {
	private static final String COUNT_SQL = "SELECT count(*) as c FROM ";
	private static final String GETLASTID_SQL = "SELECT DISTINCT last_insert_id() last FROM ";
	private Session session;

    /**
     * Contrutor que pega uma sessao ao ser criado
     */
	public AbstractDAO() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

    /**
     * Método que devolve a sessão do DAO
     * @return Session - Sessão
     */
	public Session getSession() {
		return session;
	}

    /**
     * Método que devolve o último id inserido no Banco de Dados
     * @param tabela String - nome da tabela
     * @return int - id
     */
	public int getLastID(String tabela) throws SQLException {
		int result = (Integer) session.createQuery(GETLASTID_SQL + tabela).uniqueResult();

		return result;
	}

    /**
     * Método que devolve o número de registros no Banco de Dados
     * @param tabela String - nome da tabela
     * @return int - quantidade
     */
	public int count(String tabela) throws SQLException {
		int result = (Integer) session.createQuery(COUNT_SQL + tabela).uniqueResult();

		return result;
	}
}
