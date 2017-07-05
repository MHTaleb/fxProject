/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.emf.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Taleb
 */
public class EMF_Factory {
   
    private static String persistenceUnitName="DEFAULT_PU";
    public static EntityManagerFactory getInstance(){
        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }
}
