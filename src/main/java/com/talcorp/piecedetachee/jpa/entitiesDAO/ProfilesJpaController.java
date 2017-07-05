/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.jpa.entitiesDAO;

import com.talcorp.piecedetachee.jpa.entities.Profiles;
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
public class ProfilesJpaController implements Serializable {

    public ProfilesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Profiles profiles) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(profiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Profiles profiles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            profiles = em.merge(profiles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = profiles.getId();
                if (findProfiles(id) == null) {
                    throw new NonexistentEntityException("The profiles with id " + id + " no longer exists.");
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
            Profiles profiles;
            try {
                profiles = em.getReference(Profiles.class, id);
                profiles.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profiles with id " + id + " no longer exists.", enfe);
            }
            em.remove(profiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profiles> findProfilesEntities() {
        return findProfilesEntities(true, -1, -1);
    }

    public List<Profiles> findProfilesEntities(int maxResults, int firstResult) {
        return findProfilesEntities(false, maxResults, firstResult);
    }

    private List<Profiles> findProfilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Profiles as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Profiles findProfiles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profiles.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfilesCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Profiles as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
