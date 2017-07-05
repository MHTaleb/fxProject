/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.produit;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Taleb
 */
public class FilterProduitCollectionItem {
  
    private FilterProduitItem filterProduitItem;
    private final SimpleStringProperty visualText;

    public FilterProduitCollectionItem(FilterProduitItem filterProduitItem) {
        this.filterProduitItem=filterProduitItem;
        this.visualText = new SimpleStringProperty(filterProduitItem.getItemText());
    }

    public FilterProduitItem getFilterProduitItem() {
        return filterProduitItem;
    }

    public String getVisualText() {
        return visualText.getValue();
    }

    public void setFilterProduitItem(FilterProduitItem filterProduitItem) {
        this.filterProduitItem = filterProduitItem;
    }

    public void setVisualText(String visualText) {
        this.visualText.set(visualText);
    }

    @Override
    public String toString() {
        return this.getVisualText();
    }
    
    
    
  
    
}
