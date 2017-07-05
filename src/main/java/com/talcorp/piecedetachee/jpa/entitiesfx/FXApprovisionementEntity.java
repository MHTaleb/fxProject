/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.jpa.entitiesfx;

import com.talcorp.piecedetachee.jpa.entities.Approvisionement;
import com.talcorp.piecedetachee.jpa.entities.Facture;
import com.talcorp.piecedetachee.jpa.entities.Produit;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Taleb
 */
public class FXApprovisionementEntity implements IFXApprovisionement{

    private Approvisionement approvisionement;
    
    private SimpleStringProperty sspFacture = new SimpleStringProperty();
    private SimpleStringProperty sspProduit = new SimpleStringProperty();
    private SimpleIntegerProperty sspID = new SimpleIntegerProperty();
    private SimpleStringProperty sspPrixAchat= new SimpleStringProperty();
    private SimpleStringProperty sspPrixVente= new SimpleStringProperty();
    private SimpleIntegerProperty sspQuantity= new SimpleIntegerProperty();

    public FXApprovisionementEntity() {
    }

    public FXApprovisionementEntity(Approvisionement approvisionement) {
        this.approvisionement = approvisionement;
        adapt(approvisionement);
    }
    
//------------------------------------------------------------------------------    
    private PropertyChangeSupport pcs ;
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    
    
    private void adapt(Approvisionement approvisionement){
        setSspFacture(new SimpleStringProperty(approvisionement.getFacture().getNumeroFacture()));
        setSspID(new SimpleIntegerProperty(approvisionement.getId()));
        setSspPrixAchat(new SimpleStringProperty(approvisionement.getPrixAchat().toString()));
        setSspPrixVente(new SimpleStringProperty(approvisionement.getPrixVente().toString()));
        setSspQuantity(new SimpleIntegerProperty(approvisionement.getQuantite()));
        setSspProduit(new SimpleStringProperty(approvisionement.getProduit().getTitre()));
    }
//------------------------------------------------------------------------------    
    //-------------------------------------------------------------------------

    public void setSspProduit(SimpleStringProperty sspProduit) {
        String oldProduit = this.sspProduit.toString();
        this.sspProduit = sspProduit;
        pcs.firePropertyChange("sspProduit", oldProduit, sspProduit);
    }

    public SimpleStringProperty getSspProduit() {
        return sspProduit;
    }
    
    
    
    public void setSspFacture(SimpleStringProperty sspFacture) {    
        String oldSspFacture = this.sspFacture.toString();
        this.sspFacture = sspFacture;
        pcs.firePropertyChange("sspFacture", oldSspFacture, sspFacture);
    }
    
    public void setApprovisionement(Approvisionement approvisionement) {
        this.approvisionement = approvisionement;
        adapt(approvisionement);
    }

    public void setSspID(SimpleIntegerProperty sspID) {
        Integer oldID = this.sspID.getValue();
        this.sspID = sspID;
        pcs.firePropertyChange("sspID", oldID, sspID);
    }

    public void setSspPrixAchat(SimpleStringProperty sspPrixAchat) {
        String oldSspPrixAchat = this.sspPrixAchat.toString();
        this.sspPrixAchat = sspPrixAchat;
        pcs.firePropertyChange("sspPrixAchat", oldSspPrixAchat, sspPrixAchat);
    }

    public void setSspPrixVente(SimpleStringProperty sspPrixVente) {
        String oldSspPrixVente = this.sspPrixVente.toString();
        this.sspPrixVente = sspPrixVente;
        pcs.firePropertyChange("sspPrixVente", oldSspPrixVente, sspPrixVente);
    }

    public void setSspQuantity(SimpleIntegerProperty sspQuantity) {
        Integer oldSspQuantity = this.sspQuantity.getValue();
        this.sspQuantity = sspQuantity;
        pcs.firePropertyChange("sspQuantity", oldSspQuantity, sspQuantity);
    }

    public Approvisionement getApprovisionement() {
        return approvisionement;
    }

    public SimpleStringProperty getSspFacture() {
        return sspFacture;
    }

    public SimpleIntegerProperty getSspID() {
        return sspID;
    }

    public SimpleStringProperty getSspPrixAchat() {
        return sspPrixAchat;
    }

    public SimpleStringProperty getSspPrixVente() {
        return sspPrixVente;
    }

    public SimpleIntegerProperty getSspQuantity() {
        return sspQuantity;
    }

    
    
    //-------------------------------------------------------------------------
    @Override
    public Facture getIDfacture() {
        return approvisionement.getFacture();
    }

    @Override
    public Integer getId() {
        return approvisionement.getId();
    }

    @Override
    public Integer getPrixAchat() {
        return approvisionement.getPrixAchat();
    }

    @Override
    public Integer getPrixVente() {
        return approvisionement.getPrixVente();
    }

    @Override
    public Produit getProduit() {
        return approvisionement.getProduit();
    }

    @Override
    public Integer getQuantite() {
        return approvisionement.getQuantite();
    }

  

    @Override
    public void setIDfacture(Facture IDfacture) {
        approvisionement.setFacture(IDfacture);
    }

    @Override
    public void setId(Integer id) {
        approvisionement.setId(id);
    }

    @Override
    public void setPrixAchat(Integer prixAchat) {
        approvisionement.setPrixAchat(prixAchat);
    }

    @Override
    public void setPrixVente(Integer prixVente) {
        approvisionement.setPrixVente(prixVente);
    }

    @Override
    public void setProduit(Produit produit) {
        approvisionement.setProduit(produit);
    }

    @Override
    public void setQuantite(Integer quantite) {
        approvisionement.setQuantite(quantite);
    }

   
    
    
}
