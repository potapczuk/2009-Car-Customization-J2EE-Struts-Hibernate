package br.com.diegoliveira.indiana.persistencia;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Classe responsável por criar o Banco de Dados e o DDL do projeto
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class GeraTabelas {

    public static void main(String[] args) {
        AnnotationConfiguration cfg = new AnnotationConfiguration().configure();
        SchemaExport schemaExport = new SchemaExport(cfg);
        schemaExport.create(true, false);

//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        Session session = sf.openSession();
//        Marca marca = new Marca();
//
//        marca.setNome("Ford");
//
//        session.save(marca);

//        List<Marca> list = session.createCriteria(Marca.class).list();
//
//        for(Marca marca : list){
//            System.out.println(marca.getNome());
//        }

//        Marca marca = (Marca) session.load(Marca.class, 2L);
//        System.out.println(marca.getNome());

//        Marca marca = (Marca) session.load(Marca.class, 1L);
//        System.out.println("O select já foi feito");
//        System.out.println(marca.getNome());
//        System.out.println(marca.getClass().getName());
    }
}