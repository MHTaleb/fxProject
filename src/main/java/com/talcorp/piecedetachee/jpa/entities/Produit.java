/**
 * This file was generated by the JPA Modeler
 */
package com.talcorp.piecedetachee.jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Taleb
 */
@Entity
@Table(name = "produits")
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p"),
    @NamedQuery(name = "Produit.findById", query = "SELECT p FROM Produit p WHERE p.id = :id"),
    @NamedQuery(name = "Produit.findByTitre", query = "SELECT p FROM Produit p WHERE p.titre = :titre"),
    @NamedQuery(name = "Produit.findByQtMin", query = "SELECT p FROM Produit p WHERE p.qtMin = :qtMin"),
    @NamedQuery(name = "Produit.findByCodebar", query = "SELECT p FROM Produit p WHERE p.codebar = :codebar"),
    //@NamedQuery(name = "Produit.findByDetail", query = "SELECT p FROM Produit p WHERE p.detail = :detail")
})
@XmlRootElement
public class Produit implements Serializable {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TITRE")
    @Basic
    private String titre;

    @Column(name = "QT_MIN")
    @Basic
    private int qtMin;

    @Column(name = "CODEBAR")
    @Basic
    private String codebar;

    @Embedded
    private DetailProduit detailProduit;

    @OneToOne(targetEntity = Categorie.class)
    private Categorie categorie;

    @OneToMany(targetEntity = Marque.class)
    private List<Marque> marques;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getQtMin() {
        return this.qtMin;
    }

    public void setQtMin(int qtMin) {
        this.qtMin = qtMin;
    }

    public String getCodebar() {
        return this.codebar;
    }

    public void setCodebar(String codebar) {
        this.codebar = codebar;
    }

    public DetailProduit getDetailProduit() {
        return this.detailProduit;
    }

    public void setDetailProduit(DetailProduit detailProduit) {
        this.detailProduit = detailProduit;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Marque> getMarques() {
        return this.marques;
    }

    public void setMarques(List<Marque> marques) {
        this.marques = marques;
    }

}
