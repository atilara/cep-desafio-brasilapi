package br.com.atilarodrigues.repository;

import br.com.atilarodrigues.database.HibernateUtil;
import br.com.atilarodrigues.model.CepModel;
import org.hibernate.Session;

public class CepRepository {

    public static Session session = HibernateUtil.getSessionFactory().openSession();

    public void saveAddress(CepModel cepModel) {
        try {
            session.beginTransaction();
            session.save(cepModel);
            session.getTransaction().commit();
            System.out.println("Endereço salvo com sucesso!");

            findAddress(cepModel.getCep());
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void findAddress(String cep) {
        try {
            session.beginTransaction();
            CepModel cepModel = session.find(CepModel.class, cep);
            System.out.println(cepModel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
    }

}
