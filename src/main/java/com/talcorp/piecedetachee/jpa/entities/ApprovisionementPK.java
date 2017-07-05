//
// This file was generated by the JPA Modeler
//
package com.talcorp.piecedetachee.jpa.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author $author
 */
public class ApprovisionementPK implements Serializable {

    private Integer id;

    private Integer produit;

    private FacturePK facture;

    public ApprovisionementPK() {

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduit() {
        return this.produit;
    }

    public void setProduit(Integer produit) {
        this.produit = produit;
    }

    public FacturePK getFacture() {
        return this.facture;
    }

    public void setFacture(FacturePK facture) {
        this.facture = facture;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.produit);
        hash = 59 * hash + Objects.hashCode(this.facture);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApprovisionementPK other = (ApprovisionementPK) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.produit, other.produit)) {
            return false;
        }
        if (!Objects.equals(this.facture, other.facture)) {
            return false;
        }
        return true;
    }

}
