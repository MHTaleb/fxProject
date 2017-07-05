/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.produit;

import com.talcorp.piecedetachee.jpa.entities.Produit;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Taleb
 */
public class FXProduit {

    private Produit produit;

    public final static String ID_PROPERTY = "id";
    public final static String QTMIN_PROPERTY = "qtMin";
    public final static String TITRE_PROPERTY = "titre";
    public final static String CODEBAR_PROPERTY = "codeBar";
    public final static String NUMLOT_PROPERTY = "numLot";
    public final static String NUMSERIE_PROPERTY = "numSerie";
    public final static String CATEGORIE_PROPERTY = "categorie";

    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty qtMin;
    private final SimpleStringProperty titre;
    private final SimpleStringProperty codeBar;
    private final SimpleStringProperty numLot;
    private final SimpleStringProperty numSerie;
    private final SimpleStringProperty categorie;

    public FXProduit(Integer id, Integer qtMin, String titre, String codeBar, String numLot, String numSerie, String categorie) {
        id=(id==null)?-1:id;
        qtMin=(qtMin==null)?-1:qtMin;
        titre=(titre==null)?"-1":titre;
        codeBar=(codeBar==null)?"-1":codeBar;
        numLot=(numLot==null)?"-1":numLot;
        numSerie=(numSerie==null)?"-1":numSerie;
        categorie=(categorie==null)?"-1":categorie;
        this.id = new SimpleIntegerProperty(id);
        this.qtMin = new SimpleIntegerProperty(qtMin);
        this.titre = new SimpleStringProperty(titre);
        this.codeBar = new SimpleStringProperty(codeBar);
        this.numLot = new SimpleStringProperty(numLot);
        this.numSerie = new SimpleStringProperty(numSerie);
        this.categorie = new SimpleStringProperty(categorie);
        System.out.println("creation done reflexive");
    }

    
    public FXProduit(Produit produit) {
        
        this(
                produit.getId(), 
                produit.getQtMin(), 
                produit.getTitre(), 
                produit.getCodebar(),
                produit.getDetailProduit().getNumeroLot(),
                produit.getDetailProduit().getNumeroSerie(), 
                produit.getCategorie().getTitreCategorie()
        );
        this.produit = produit;
        System.out.println("creation done final");
    }

    public void setCategorie(String categorie) {
        this.categorie.setValue(categorie);
    }

    public void setCodeBar(String codeBar) {
        this.codeBar.setValue(codeBar);
    }

    public void setId(Integer id) {
        this.id.setValue(id);
    }

    public void setNumLot(String numLot) {
        this.numLot.setValue(numLot);
    }

    public void setNumSerie(String numSerie) {
        this.numSerie.setValue(numSerie);
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setQtMin(Integer qtMin) {
        this.qtMin.setValue(qtMin);
    }

    public void setTitre(String titre) {
        this.titre.setValue(titre);
    }

    public String getCategorie() {
        return categorie.getValue();
    }

    public String getCodeBar() {
        return codeBar.getValue();
    }

    public Integer getId() {
        return id.getValue();
    }

    public String getNumLot() {
        return numLot.getValue();
    }

    public String getNumSerie() {
        return numSerie.getValue();
    }

    public Produit getProduit() {
        return produit;
    }

    public Integer getQtMin() {
        return qtMin.getValue();
    }

    public String getTitre() {
        return titre.getValue();
    }

}
