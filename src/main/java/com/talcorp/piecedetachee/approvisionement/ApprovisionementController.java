/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.approvisionement;


import com.talcorp.piecedetachee.jpa.entities.Approvisionement;
import com.talcorp.piecedetachee.jpa.entitiesDAO.ApprovisionementJpaController;
import com.talcorp.piecedetachee.jpa.entitiesfx.FXApprovisionementEntity;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Taleb
 */
public class ApprovisionementController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Button printBTN;
    @FXML
    private Button addBTN;
    @FXML
    private Button updateBTN;
    @FXML
    private Button deleteBTN;
    @FXML
    private TableView<FXApprovisionementEntity> mainGrid;
    
    private EntityManagerFactory emf;
    @FXML
    private ComboBox<?> searchFilter;
    @FXML
    private TableColumn<?, ?> columnID;
    @FXML
    private TableColumn<?, ?> columnProduit;
    @FXML
    private TableColumn<?, ?> columnCodeBar;
    @FXML
    private TableColumn<?, ?> columnQTMin;
    @FXML
    private TableColumn<?, ?> columnCategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        doInitEntityManager();
        diInitGrid();
        
    }    

    @FXML
    private void doSearch(ActionEvent event) {
    }

    @FXML
    private void doPrint(ActionEvent event) {
    }

    @FXML
    private void doADD(ActionEvent event) {
    }

    @FXML
    private void doUpdate(ActionEvent event) {
    }

    @FXML
    private void doDelete(ActionEvent event) {
    }


// first step start the entity manager to get dba 
    private void doInitEntityManager() {
        String persistenceUnitName = "DEFAULT_PU";
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    private void diInitGrid() {
        ApprovisionementJpaController ajc = new ApprovisionementJpaController(emf);
        List<Approvisionement> findApprovisionementEntities = ajc.findApprovisionementEntities();
        for (Approvisionement findApprovisionementEntity : findApprovisionementEntities) {
            System.out.println(findApprovisionementEntity.getProduit().getTitre());
        }
    }
    
}
