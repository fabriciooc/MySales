package br.com.mysales.conexoesdao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

public class ConexaoFactory {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("dao-generico");;
	
	public static EntityManager getEntityManager(){
		return factory.createEntityManager();
		}
}
}