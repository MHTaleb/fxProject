/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.jpa.entitiesDAO;

import com.talcorp.piecedetachee.jpa.entities.Marque;
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
public class MarqueJpaController implements Serializable {

    public MarqueJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marque marque) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(marque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Marque marque) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            marque = em.merge(marque);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = marque.getId();
                if (findMarque(id) == null) {
                    throw new NonexistentEntityException("The marque with id " + id + " no longer exists.");
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
            Marque marque;
            try {
                marque = em.getReference(Marque.class, id);
                marque.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marque with id " + id + " no longer exists.", enfe);
            }
            em.remove(marque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Marque> findMarqueEntities() {
        return findMarqueEntities(true, -1, -1);
    }

    public List<Marque> findMarqueEntities(int maxResults, int firstResult) {
        return findMarqueEntities(false, maxResults, firstResult);
    }

    private List<Marque> findMarqueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Marque as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Marque findMarque(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marque.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarqueCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Marque as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
