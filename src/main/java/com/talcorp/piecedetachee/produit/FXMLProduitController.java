/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.produit;

import com.talcorp.piecedetachee.emf.factory.EMF_Factory;
import com.talcorp.piecedetachee.jpa.entities.Categorie;
import com.talcorp.piecedetachee.jpa.entities.DetailProduit;
import com.talcorp.piecedetachee.jpa.entities.Produit;
import com.talcorp.piecedetachee.jpa.entitiesDAO.ProduitJpaController;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;

/**
 * FXML Controller class
 *
 * @author Taleb
 */
public class FXMLProduitController implements Initializable {

    @FXML
    private ComboBox<FilterProduitCollectionItem> searchFilter;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<FXProduit> mainGrid;
    @FXML
    private TableColumn<FXProduit, Integer> columnID;
    @FXML
    private TableColumn<FXProduit, String> columnProduit;
    @FXML
    private TableColumn<FXProduit, String> columnCodeBar;
    @FXML
    private TableColumn<FXProduit, Integer> columnQTMin;
    @FXML
    private TableColumn<FXProduit, String> columnCategorie;
    @FXML
    private Button printBTN;
    @FXML
    private Button addBTN;
    @FXML
    private Button updateBTN;
    @FXML
    private Button deleteBTN;

    private EntityManagerFactory emf;
    private ProduitJpaController pjc;
    private ObservableList<FXProduit> observableArrayList;

    public FXMLProduitController() {
       
        getAllProduit();
    }

    private void getAllProduit() {
        emf = EMF_Factory.getInstance();
        pjc = new ProduitJpaController(emf);
        observableArrayList = FXCollections.observableArrayList();
        try {

            pjc.findProduitEntities().parallelStream()
                    .peek(produit -> {
                        if (produit.getCategorie() == null) {
                            produit.setCategorie(new Categorie());
                        }
                        if (produit.getDetailProduit() == null) {
                            produit.setDetailProduit(new DetailProduit());
                        }
                    })
                    .map(FXProduit::new)
                    .forEach(observableArrayList::add);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupColumns();
        setupFilter();
        refreshGrid();
    }
//<editor-fold defaultstate="collapsed" desc="search methods that i need someday to delegate to">

    private void searchGridByNumLot(String numLot) {
        EntityManagerFactory emf = EMF_Factory.getInstance();
        ProduitJpaController pjc = new ProduitJpaController(emf);
        ObservableList<FXProduit> observableArrayList = FXCollections.observableArrayList();
        pjc.findProduitEntities().parallelStream()
                .peek(produit -> System.out.println("p =" + produit.getTitre()))
                .filter(produit -> {
                    return produit.getDetailProduit().getNumeroLot().toLowerCase().equals(numLot.toLowerCase());
                })
                .map(FXProduit::new)
                .forEach(observableArrayList::add);
    }

    private void searchGridByNumSerie(String numSerie) {
        EntityManagerFactory emf = EMF_Factory.getInstance();
        ProduitJpaController pjc = new ProduitJpaController(emf);
        ObservableList<FXProduit> observableArrayList = FXCollections.observableArrayList();
        pjc.findProduitEntities().parallelStream()
                .peek(produit -> System.out.println("p =" + produit.getTitre()))
                .filter(produit -> {
                    return produit.getDetailProduit().getNumeroSerie().toLowerCase().equals(numSerie.toLowerCase());
                })
                .map(FXProduit::new)
                .forEach(observableArrayList::add);
    }

    private void searchGridByCodeABar(String codeABar) {
        EntityManagerFactory emf = EMF_Factory.getInstance();
        ProduitJpaController pjc = new ProduitJpaController(emf);
        ObservableList<FXProduit> observableArrayList = FXCollections.observableArrayList();
        pjc.findProduitEntities().parallelStream()
                .peek(produit -> System.out.println("p =" + produit.getTitre()))
                .filter(produit -> {
                    return produit.getCodebar().toLowerCase().equals(codeABar.toLowerCase());
                })
                .map(FXProduit::new)
                .forEach(observableArrayList::add);
    }

    private void searchGridByTitle(String title) {
        EntityManagerFactory emf = EMF_Factory.getInstance();
        ProduitJpaController pjc = new ProduitJpaController(emf);
        ObservableList<FXProduit> observableArrayList = FXCollections.observableArrayList();
        pjc.findProduitEntities().parallelStream()
                .peek(produit -> System.out.println("p =" + produit.getTitre()))
                .filter(produit -> {
                    return produit.getTitre().toLowerCase().contains(title.toLowerCase());
                })
                .map(FXProduit::new)
                .forEach(observableArrayList::add);
    }

    private void searchGridByID(Integer id) {
        EntityManagerFactory emf = EMF_Factory.getInstance();
        ProduitJpaController pjc = new ProduitJpaController(emf);
        ObservableList<FXProduit> observableArrayList = FXCollections.observableArrayList();
        pjc.findProduitEntities().parallelStream()
                .peek(produit -> System.out.println("p =" + produit.getTitre()))
                .filter(produit -> {
                    return Objects.equals(produit.getId(), id);
                })
                .map(FXProduit::new)
                .forEach(observableArrayList::add);
    }
//</editor-fold>

    private void refreshGrid() {

        mainGrid.setItems(observableArrayList);
        System.out.println("init done Produit controller");
    }

    private void setupColumns() {
        try {
            columnCategorie.setCellValueFactory(new PropertyValueFactory<>(FXProduit.CATEGORIE_PROPERTY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            columnCodeBar.setCellValueFactory(new PropertyValueFactory<>(FXProduit.CODEBAR_PROPERTY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            columnID.setCellValueFactory(new PropertyValueFactory<>(FXProduit.ID_PROPERTY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            columnProduit.setCellValueFactory(new PropertyValueFactory<>(FXProduit.TITRE_PROPERTY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            columnQTMin.setCellValueFactory(new PropertyValueFactory<>(FXProduit.QTMIN_PROPERTY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void doSearch(ActionEvent event) {
        observableArrayList.clear();

        FilterProduitItem filterProduitItem = searchFilter.getValue().getFilterProduitItem();
        String text = searchField.getText();
        pjc.findProduitEntities().stream()
                .peek(produit -> {
                    if (produit.getCategorie() == null) {
                        produit.setCategorie(new Categorie());
                    }
                    if (produit.getDetailProduit() == null) {
                        produit.setDetailProduit(new DetailProduit());
                    }
                })
                .filter((Produit produit) -> {
                    if (text.trim().isEmpty()) {
                        return true;

                    }
                    System.out.println("text " + text + " - " + filterProduitItem.getItemText());
                    switch (filterProduitItem.criteria) {
                        case CODE_A_BAR:
                            return produit.getCodebar().toLowerCase().equals(text.toLowerCase());
                        case QT_MIN:
                            return produit.getQtMin() == Integer.parseInt(text.trim());
                        case TITRE:
                            return produit.getTitre().toLowerCase().contains(text.toLowerCase());
                    }
                    return false;
                })
                .map(FXProduit::new)
                .forEach(observableArrayList::add);

        refreshGrid();

    }

    @FXML
    private void doPrint(ActionEvent event) {
    }

    @FXML
    private void doADD(ActionEvent event) {

        ProduitDialogue produitDialogue = new ProduitDialogue(emf);
        produitDialogue.showAndWait();
        getAllProduit();
        refreshGrid();

    }

    @FXML
    private void doUpdate(ActionEvent event) {
        Produit p = mainGrid.getSelectionModel().getSelectedItem().getProduit();
        ProduitDialogue dialogue = new ProduitDialogue(emf, p);
        dialogue.showAndWait();
        getAllProduit();
        refreshGrid();
    }

    @FXML
    private void doDelete(ActionEvent event) {

        FXProduit selectedItem = mainGrid.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Fenetre de Confirmation ");
        alert.setHeaderText("Veuillez Confirmer la supression");
        alert.setContentText("Vous venez de lancer la supression de l'élement :\n"
                + "  " + selectedItem.getTitre() + '-' + selectedItem.getCategorie());

        Produit produit = selectedItem.getProduit();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK && produit != null) {
            try {
                // ... user chose OK
                pjc.destroy(produit.getId());
                getAllProduit();
                refreshGrid();
            } catch (Exception ex) {
                Logger.getLogger(FXMLProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    private void setupFilter() {
        ObservableList<FilterProduitCollectionItem> items = searchFilter.getItems();

        for (FilterProduitItem.produitCriteria criteria : FilterProduitItem.produitCriteria.values()) {
            items.add(new FilterProduitCollectionItem(new FilterProduitItem(criteria)));
        }
        searchFilter.getSelectionModel().select(0);
    }

}
