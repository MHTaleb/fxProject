/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.jpa.entitiesDAO;

import com.talcorp.piecedetachee.jpa.entities.Approvisionement;
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
public class ApprovisionementJpaController implements Serializable {

    public ApprovisionementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Approvisionement approvisionement) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(approvisionement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Approvisionement approvisionement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            approvisionement = em.merge(approvisionement);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = approvisionement.getId();
                if (findApprovisionement(id) == null) {
                    throw new NonexistentEntityException("The approvisionement with id " + id + " no longer exists.");
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
            Approvisionement approvisionement;
            try {
                approvisionement = em.getReference(Approvisionement.class, id);
                approvisionement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The approvisionement with id " + id + " no longer exists.", enfe);
            }
            em.remove(approvisionement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Approvisionement> findApprovisionementEntities() {
        return findApprovisionementEntities(true, -1, -1);
    }

    public List<Approvisionement> findApprovisionementEntities(int maxResults, int firstResult) {
        return findApprovisionementEntities(false, maxResults, firstResult);
    }

    private List<Approvisionement> findApprovisionementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Approvisionement as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Approvisionement findApprovisionement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Approvisionement.class, id);
        } finally {
            em.close();
        }
    }

    public int getApprovisionementCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Approvisionement as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
