/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.produit;

import com.talcorp.piecedetachee.jpa.entities.Marque;

/**
 *
 * @author Taleb
 */
public class FilterMarqueCollectionItem {
    Marque marque;

    public FilterMarqueCollectionItem(Marque marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return marque.getTitreMarque();
    }
    
}
