package br.com.atilarodrigues.repository;

import br.com.atilarodrigues.database.HibernateUtil;
import br.com.atilarodrigues.exceptions.CepAlreadyStoredException;
import br.com.atilarodrigues.model.CepModel;
import org.hibernate.Session;

import java.util.ArrayList;

public class CepRepository {

    public static Session session = HibernateUtil.getSessionFactory().openSession();

    public CepModel findAddress(String cep) {
        try {
            session.beginTransaction();
            // Find the address by the CEP column

            // If the CEP is not found, don't throw an exception
            CepModel cepModel = session.createQuery("from cep_model where cep = :cep", CepModel.class)
                    .setParameter("cep", cep)
                    .getSingleResult();

            return cepModel;
        } catch (Exception e) {
            if (e instanceof javax.persistence.NoResultException) {
                return null;
            }
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        return null;
    }

    public ArrayList<CepModel> findAllAddresses() {
        try {
            session.beginTransaction();
            ArrayList<CepModel> cepModels = (ArrayList<CepModel>) session.createQuery("from cep_model").list();
            return cepModels;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        return null;
    }

    public CepModel saveAddress(CepModel cepModel) {
        try {
            // Verify if the CEP already exists in the database
            if (findAddress(cepModel.getCep()) != null) {
                throw new CepAlreadyStoredException();
            }
            session.beginTransaction();
            session.save(cepModel);
            session.getTransaction().commit();
            return findAddress(cepModel.getCep());
        } catch (Exception e) {
            if (!(e instanceof CepAlreadyStoredException)) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
            }
            e.printStackTrace();
        }
        return null;
    }


}
