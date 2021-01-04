package in.bushansirgur.springboot.crudapi.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.bushansirgur.springboot.crudapi.model.Employee;
import in.bushansirgur.springboot.crudapi.model.FileUpload;


@Repository
public class FileUploadDAOImpl implements FileUploadDAO {
   // @Autowired
   // private SessionFactory sessionFactory;
    
    @Autowired
	private EntityManager entityManager;
     
    public FileUploadDAOImpl() {
    }
 
    /*public FileUploadDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }*/
 
    @Override
    @Transactional
    public void save(FileUpload fileUpload) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(fileUpload);
	}
}