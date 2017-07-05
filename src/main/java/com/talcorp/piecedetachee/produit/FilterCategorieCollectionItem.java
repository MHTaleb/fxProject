/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.produit;

import com.talcorp.piecedetachee.jpa.entities.Categorie;

/**
 *
 * @author Taleb
 */
public class FilterCategorieCollectionItem {

    private final Categorie categorie;

    public FilterCategorieCollectionItem(Categorie categorie) {
        this.categorie = categorie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    @Override
    public String toString() {
        return categorie.getTitreCategorie();
    }

}
