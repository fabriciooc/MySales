package br.com.mysales.DAO;

import javax.persistence.EntityManager;

import br.com.mysales.conexoesdao.ConexaoFactory;
import br.edu.mysales.estoque.model.EntidadeBasica;


public class DaoGenerico<T extends EntidadeBasica> {
	
	private static EntityManager manager = ConexaoFactory.getEntityManager();;
	
	public T findById(Class<T> clazz, Long id){
		return manager.find(clazz, id);
	}
	
	public void saveOrUpdate(T obj){
		try{
			manager.getTransaction().begin();
			if(obj.getId() == null){
				manager.persist(obj);
			}else{
				manager.merge(obj);
			}
			manager.getTransaction().commit();
		}catch(Exception e){
			manager.getTransaction().rollback();
		}
	}
	
	public void remove(Class<T> clazz, Long id){
		T t = findById(clazz, id);
		try{
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		}catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

}
