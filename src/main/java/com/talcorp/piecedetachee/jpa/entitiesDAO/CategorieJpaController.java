/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.jpa.entitiesDAO;

import com.talcorp.piecedetachee.jpa.entities.Categorie;
import com.talcorp.piecedetachee.jpa.entitiesDAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Taleb
 */
public class CategorieJpaController implements Serializable {

    public CategorieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categorie categorie) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(categorie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categorie categorie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            categorie = em.merge(categorie);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categorie.getId();
                if (findCategorie(id) == null) {
                    throw new NonexistentEntityException("The categorie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorie categorie;
            try {
                categorie = em.getReference(Categorie.class, id);
                categorie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categorie with id " + id + " no longer exists.", enfe);
            }
            em.remove(categorie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categorie> findCategorieEntities() {
        return findCategorieEntities(true, -1, -1);
    }

    public List<Categorie> findCategorieEntities(int maxResults, int firstResult) {
        return findCategorieEntities(false, maxResults, firstResult);
    }

    private List<Categorie> findCategorieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Categorie as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Categorie findCategorie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categorie.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategorieCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Categorie as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
