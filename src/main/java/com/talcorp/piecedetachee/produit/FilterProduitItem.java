/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.produit;

/**
 *
 * @author Taleb
 */
public class FilterProduitItem {

 produitCriteria criteria;   
    
    public String getItemText() {
        switch(criteria){
            case CODE_A_BAR : return "code à bar";
            case QT_MIN : return "quantité minimale";
            case TITRE : return "titre";
        }
        return "aucun";
    }

    public FilterProduitItem(produitCriteria criteria) {
        this.criteria = criteria;
    }
    
    public enum produitCriteria{TITRE,CODE_A_BAR,QT_MIN}
}
