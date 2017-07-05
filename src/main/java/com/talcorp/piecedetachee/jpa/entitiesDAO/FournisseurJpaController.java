/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.jpa.entitiesDAO;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.talcorp.piecedetachee.jpa.entities.Facture;
import com.talcorp.piecedetachee.jpa.entities.Fournisseur;
import com.talcorp.piecedetachee.jpa.entitiesDAO.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Taleb
 */
public class FournisseurJpaController implements Serializable {

    public FournisseurJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fournisseur fournisseur) {
        if (fournisseur.getFactures() == null) {
            fournisseur.setFactures(new ArrayList<Facture>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Facture> attachedFactures = new ArrayList<Facture>();
            for (Facture facturesFactureToAttach : fournisseur.getFactures()) {
                facturesFactureToAttach = em.getReference(facturesFactureToAttach.getClass(), facturesFactureToAttach.getId());
                attachedFactures.add(facturesFactureToAttach);
            }
            fournisseur.setFactures(attachedFactures);
            em.persist(fournisseur);
            for (Facture facturesFacture : fournisseur.getFactures()) {
                Fournisseur oldFournisseurOfFacturesFacture = facturesFacture.getFournisseur();
                facturesFacture.setFournisseur(fournisseur);
                facturesFacture = em.merge(facturesFacture);
                if (oldFournisseurOfFacturesFacture != null) {
                    oldFournisseurOfFacturesFacture.getFactures().remove(facturesFacture);
                    oldFournisseurOfFacturesFacture = em.merge(oldFournisseurOfFacturesFacture);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fournisseur fournisseur) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fournisseur persistentFournisseur = em.find(Fournisseur.class, fournisseur.getId());
            List<Facture> facturesOld = persistentFournisseur.getFactures();
            List<Facture> facturesNew = fournisseur.getFactures();
            List<Facture> attachedFacturesNew = new ArrayList<Facture>();
            for (Facture facturesNewFactureToAttach : facturesNew) {
                facturesNewFactureToAttach = em.getReference(facturesNewFactureToAttach.getClass(), facturesNewFactureToAttach.getId());
                attachedFacturesNew.add(facturesNewFactureToAttach);
            }
            facturesNew = attachedFacturesNew;
            fournisseur.setFactures(facturesNew);
            fournisseur = em.merge(fournisseur);
            for (Facture facturesOldFacture : facturesOld) {
                if (!facturesNew.contains(facturesOldFacture)) {
                    facturesOldFacture.setFournisseur(null);
                    facturesOldFacture = em.merge(facturesOldFacture);
                }
            }
            for (Facture facturesNewFacture : facturesNew) {
                if (!facturesOld.contains(facturesNewFacture)) {
                    Fournisseur oldFournisseurOfFacturesNewFacture = facturesNewFacture.getFournisseur();
                    facturesNewFacture.setFournisseur(fournisseur);
                    facturesNewFacture = em.merge(facturesNewFacture);
                    if (oldFournisseurOfFacturesNewFacture != null && !oldFournisseurOfFacturesNewFacture.equals(fournisseur)) {
                        oldFournisseurOfFacturesNewFacture.getFactures().remove(facturesNewFacture);
                        oldFournisseurOfFacturesNewFacture = em.merge(oldFournisseurOfFacturesNewFacture);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fournisseur.getId();
                if (findFournisseur(id) == null) {
                    throw new NonexistentEntityException("The fournisseur with id " + id + " no longer exists.");
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
            Fournisseur fournisseur;
            try {
                fournisseur = em.getReference(Fournisseur.class, id);
                fournisseur.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fournisseur with id " + id + " no longer exists.", enfe);
            }
            List<Facture> factures = fournisseur.getFactures();
            for (Facture facturesFacture : factures) {
                facturesFacture.setFournisseur(null);
                facturesFacture = em.merge(facturesFacture);
            }
            em.remove(fournisseur);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fournisseur> findFournisseurEntities() {
        return findFournisseurEntities(true, -1, -1);
    }

    public List<Fournisseur> findFournisseurEntities(int maxResults, int firstResult) {
        return findFournisseurEntities(false, maxResults, firstResult);
    }

    private List<Fournisseur> findFournisseurEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Fournisseur as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fournisseur findFournisseur(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fournisseur.class, id);
        } finally {
            em.close();
        }
    }

    public int getFournisseurCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Fournisseur as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
