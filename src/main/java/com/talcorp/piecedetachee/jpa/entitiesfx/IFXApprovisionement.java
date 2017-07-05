/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.jpa.entitiesfx;

import com.talcorp.piecedetachee.jpa.entities.Facture;
import com.talcorp.piecedetachee.jpa.entities.Produit;
import com.talcorp.piecedetachee.jpa.entities.Vente;
import java.util.List;

/**
 *
 * @author Taleb
 */
public interface IFXApprovisionement {

    Facture getIDfacture();

    Integer getId();

    Integer getPrixAchat();

    Integer getPrixVente();

    Produit getProduit();

    Integer getQuantite();

 

    void setIDfacture(Facture IDfacture);

    void setId(Integer id);

    void setPrixAchat(Integer prixAchat);

    void setPrixVente(Integer prixVente);

    void setProduit(Produit produit);

    void setQuantite(Integer quantite);

 
    
}
