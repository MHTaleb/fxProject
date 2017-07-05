/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.jpa.entitiesDAO;

import com.talcorp.piecedetachee.jpa.entities.Produit;
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
public class ProduitJpaController implements Serializable {

    public ProduitJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produit produit) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(produit);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Produit produit) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            produit = em.merge(produit);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produit.getId();
                if (findProduit(id) == null) {
                    throw new NonexistentEntityException("The produit with id " + id + " no longer exists.");
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
            Produit produit;
            try {
                produit = em.getReference(Produit.class, id);
                produit.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produit with id " + id + " no longer exists.", enfe);
            }
            em.remove(produit);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produit> findProduitEntities() {
        return findProduitEntities(true, -1, -1);
    }

    public List<Produit> findProduitEntities(int maxResults, int firstResult) {
        return findProduitEntities(false, maxResults, firstResult);
    }

    private List<Produit> findProduitEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Produit as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Produit findProduit(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produit.class, id);
        } finally {
            em.close();
        }
    }

    public int getProduitCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Produit as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
