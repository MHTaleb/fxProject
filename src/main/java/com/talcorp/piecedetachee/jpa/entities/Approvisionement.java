/**
 * This file was generated by the JPA Modeler
 */
package com.talcorp.piecedetachee.jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Taleb
 */
@Entity
@Table(name = "approvisionement")
@IdClass(ApprovisionementPK.class)
@NamedQueries({
    @NamedQuery(name = "Approvisionement.findAll", query = "SELECT a FROM Approvisionement a"),
    @NamedQuery(name = "Approvisionement.findById", query = "SELECT a FROM Approvisionement a WHERE a.id = :id"),
   // @NamedQuery(name = "Approvisionement.findByIdFacture", query = "SELECT a FROM Approvisionement a WHERE a.idFacture = :idFacture"),
   // @NamedQuery(name = "Approvisionement.findByIdProduit", query = "SELECT a FROM Approvisionement a WHERE a.idProduit = :idProduit"),
    @NamedQuery(name = "Approvisionement.findByQuantite", query = "SELECT a FROM Approvisionement a WHERE a.quantite = :quantite"),
    @NamedQuery(name = "Approvisionement.findByPrixAchat", query = "SELECT a FROM Approvisionement a WHERE a.prixAchat = :prixAchat"),
    @NamedQuery(name = "Approvisionement.findByPrixVente", query = "SELECT a FROM Approvisionement a WHERE a.prixVente = :prixVente")})
@XmlRootElement
public class Approvisionement implements Serializable {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "QUANTITE")
    @Basic
    private Integer quantite;

    @Column(name = "PRIX_ACHAT")
    @Basic
    private Integer prixAchat;

    @Column(name = "PRIX_VENTE")
    @Basic
    private Integer prixVente;

    @Id
    @OneToOne(targetEntity = Produit.class)
    private Produit produit;

    @Id
    @OneToOne(targetEntity = Facture.class)
    @JoinColumns({
        @JoinColumn(name = "FACTURE_FOURNISSEUR_ID", referencedColumnName = "FOURNISSEUR_ID"),
        @JoinColumn(name = "FACTURE_ID", referencedColumnName = "ID")})
    private Facture facture;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return this.quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Integer getPrixAchat() {
        return this.prixAchat;
    }

    public void setPrixAchat(Integer prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Integer getPrixVente() {
        return this.prixVente;
    }

    public void setPrixVente(Integer prixVente) {
        this.prixVente = prixVente;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Facture getFacture() {
        return this.facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

}
