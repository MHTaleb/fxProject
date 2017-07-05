/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.projetfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Taleb
 */
public class FXMLController implements Initializable {

    @FXML
    private AnchorPane menuPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuFichier;
    @FXML
    private MenuItem menuItemQuitter;
    @FXML
    private Menu menuVente;
    @FXML
    private MenuItem menuItemMagasin;
    @FXML
    private MenuItem menuItemCaisse;
    @FXML
    private MenuItem menuItemSortie;
    @FXML
    private MenuItem menuItemFournisseur;
    @FXML
    private MenuItem menuItemProduit;
    @FXML
    private MenuItem menuItemFacture;
    @FXML
    private MenuItem menuItemStock;
    @FXML
    private MenuItem menuItemContact;
    @FXML
    private Accordion accordion;
    @FXML
    private Button buttonQuickSell;
    @FXML
    private Button buttonFundsToday;
    @FXML
    private Button buttonArticleBack;
    @FXML
    private Button buttonAlertes;
    @FXML
    private Button buttonStoque;
    @FXML
    private Button buttonFournisseur;
    @FXML
    private Button buttonPrestataires;
    @FXML
    private Button buttonApprovisionement;
    @FXML
    private Button buttonFactures;
    @FXML
    private Button buttonProduits;
    @FXML
    private Button buttonRemarques;
    @FXML
    private Button buttonServiceApresVente;
    @FXML
    private Button buttonPropos;
    @FXML
    private TabPane MainTabPane;
    @FXML
    private Tab mainTab;
    @FXML
    private AnchorPane footerPane;
//    @FXML
//    private AnchorPane mainAnchor;
    @FXML
    private HBox mainHBoxContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionQuitter(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void actionShowMagasin(ActionEvent event) {
        try {
            Node load = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            mainHBoxContainer.getChildren().setAll(load);

            System.out.println("done");
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void actionShowCaisse(ActionEvent event) {
    }

    @FXML
    private void actionShowSortie(ActionEvent event) {
    }

    @FXML
    private void actionShowFournisseur(ActionEvent event) {
    }

    @FXML
    private void actionShowProduit(ActionEvent event) {
    }

    @FXML
    private void actionShowFacture(ActionEvent event) {
    }

    @FXML
    private void actionShowStock(ActionEvent event) {
    }

    @FXML
    private void actionShowContact(ActionEvent event) {
    }

    @FXML
    private void showQuickSellOnTab(ActionEvent event) {
    }

    @FXML
    private void showFundsToday(ActionEvent event) {
    }

    @FXML
    private void showAlertes(ActionEvent event) {
    }

    @FXML
    private void showStoque(ActionEvent event) {
    }

    @FXML
    private void showFournisseur(ActionEvent event) {
    }

    @FXML
    private void showPrestataires(ActionEvent event) {
    }

    @FXML
    private void showApprovisionement(ActionEvent event) {
        try {
            
            Node load = FXMLLoader.load(getClass().getResource("/fxml/Approvisionement.fxml"));
            mainHBoxContainer.getChildren().setAll(load);

            System.out.println("done");
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showFactures(ActionEvent event) {
    }

    @FXML
    private void showProduits(ActionEvent event) {
        try {
            Node load = FXMLLoader.load(getClass().getResource("/fxml/FXMLProduit.fxml"));
            mainHBoxContainer.getChildren().setAll(load);

            System.out.println("done");
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showRemarques(ActionEvent event) {
    }

    @FXML
    private void showServiceAVente(ActionEvent event) {
    }

    @FXML
    private void showPropos(ActionEvent event) {
    }

}
