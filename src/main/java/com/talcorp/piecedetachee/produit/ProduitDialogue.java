/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talcorp.piecedetachee.produit;

import com.talcorp.piecedetachee.jpa.entities.Categorie;
import com.talcorp.piecedetachee.jpa.entities.DetailProduit;
import com.talcorp.piecedetachee.jpa.entities.Marque;
import com.talcorp.piecedetachee.jpa.entities.Produit;
import com.talcorp.piecedetachee.jpa.entitiesDAO.CategorieJpaController;
import com.talcorp.piecedetachee.jpa.entitiesDAO.MarqueJpaController;
import com.talcorp.piecedetachee.jpa.entitiesDAO.ProduitJpaController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import javax.persistence.EntityManagerFactory;
import org.controlsfx.control.CheckComboBox;

/**
 *
 * @author Taleb
 */
public class ProduitDialogue implements Callback<ButtonType, Produit> {

    private Dialog<Produit> dialog;

    private final String TITLE = "Fenetre d'ajout de produits";
    private final String HEADER_TEXT = "Veuillez saisir toutes les informations.";
    private final ImageView ICON = new ImageView(ProduitDialogue.class.getResource("/icons/delivery.png").toString());

    private GridPane Content;


    private final Label titleLabel;
    private final TextField titleField;
    private final Label codeBarLabel;
    private final TextField codeBarField;
    private final Label numDeLotLabel;
    private final TextField numDeLotField;
    private final Label categorieLabel;
    private final Label numSerieLabel;
    private final TextField numSerieField;
    private final Label marqueLabel;
    private final Label qtMinLabel;
    private final TextField qtMinField;
    private ComboBox<FilterCategorieCollectionItem> boxCategorie;
    private CheckComboBox<FilterMarqueCollectionItem> boxMarques;

    private  Produit puttedProduct;
    private final EntityManagerFactory emf;
    private final ButtonType btValidate;
    private final ButtonType btCancel;
    
    public ProduitDialogue(EntityManagerFactory emf,Produit p) {
        this(emf);
        this.puttedProduct= p;
        doEdit=true;
        titleField.setText(p.getTitre());
        qtMinField.setText(p.getQtMin()+"");
        codeBarField.setText(p.getCodebar());
        numDeLotField.setText(p.getDetailProduit().getNumeroLot());
        numSerieField.setText(p.getDetailProduit().getNumeroSerie());
        boxCategorie.setValue(new FilterCategorieCollectionItem(p.getCategorie()));
        
        List<Marque> marques = p.getMarques();
        ObservableList<FilterMarqueCollectionItem> checkableItems = boxMarques.getItems();
        int i = 0 ;
        for (FilterMarqueCollectionItem checkableItem : checkableItems) {
            i++;
            for (Marque marque : marques) {
                if (marque.getId().intValue()==checkableItem.marque.getId().intValue()) {
                    boxMarques.getCheckModel().check(checkableItem);
                }
            }
        }
        

    }
    public ProduitDialogue(EntityManagerFactory emf) {

        dialog = new Dialog<Produit>();
        dialog.setTitle(TITLE);
        dialog.setHeaderText(HEADER_TEXT);
        dialog.setGraphic(ICON);

        Content = new GridPane();
        Content.setHgap(20);
        Content.setVgap(20);
        Content.setPadding(new Insets(10, 20, 10, 20));
//        GridPane.setFillWidth(boxCategorie, Boolean.TRUE);
//        GridPane.setFillWidth(boxMarques, Boolean.TRUE);
        Content.prefWidth(700);
        Content.minWidth(700);
        Content.minHeight(1024);
        Content.prefHeight(1024);
        
        
        this.emf = emf;
        CategorieJpaController cjc = new CategorieJpaController(emf);
        boxCategorie = new ComboBox<>();
        ObservableList<FilterCategorieCollectionItem> items = boxCategorie.getItems();
        cjc.findCategorieEntities().stream().filter((Categorie ctgr) -> {
            return ctgr != null;
        }).map(FilterCategorieCollectionItem::new)
                .forEach(items::add);

        MarqueJpaController mjc = new MarqueJpaController(emf);
        boxMarques = new CheckComboBox<>();
        ObservableList<FilterMarqueCollectionItem> items1 = boxMarques.getItems();
        mjc.findMarqueEntities()
                .stream()
                .filter((Marque marque) -> {
                    return marque != null;
                })
                .map(FilterMarqueCollectionItem::new)
                .forEach(items1::add);

        titleLabel = new Label(" :", new ImageView(ProduitDialogue.class.getResource("/icons/crate.png").toString()));
        codeBarLabel = new Label(" :", new ImageView(ProduitDialogue.class.getResource("/icons/barcode.png").toString()));
        numDeLotLabel = new Label(" :", new ImageView(ProduitDialogue.class.getResource("/icons/numLot.png").toString()));
        categorieLabel = new Label(" :", new ImageView(ProduitDialogue.class.getResource("/icons/category.png").toString()));
        marqueLabel = new Label(" :", new ImageView(ProduitDialogue.class.getResource("/icons/numLot.png").toString()));
        numSerieLabel = new Label(" :", new ImageView(ProduitDialogue.class.getResource("/icons/numLot.png").toString()));
        qtMinLabel = new Label(" :", new ImageView(ProduitDialogue.class.getResource("/icons/numLot.png").toString()));
//        titleLabel = new Label("Titre :", new ImageView(ProduitDialogue.class.getResource("/icons/crate.png").toString()));
//        codeBarLabel = new Label("Code à bar :", new ImageView(ProduitDialogue.class.getResource("/icons/barcode.png").toString()));
//        numDeLotLabel = new Label("Numéro de lot :", new ImageView(ProduitDialogue.class.getResource("/icons/numLot.png").toString()));
//        categorieLabel = new Label("catégorie :", new ImageView(ProduitDialogue.class.getResource("/icons/category.png").toString()));
//        marqueLabel = new Label("marque :", new ImageView(ProduitDialogue.class.getResource("/icons/numLot.png").toString()));
//        numSerieLabel = new Label("numéro de serie :", new ImageView(ProduitDialogue.class.getResource("/icons/numLot.png").toString()));
//        qtMinLabel = new Label("quantité minimal :", new ImageView(ProduitDialogue.class.getResource("/icons/numLot.png").toString()));

        titleField = new TextField();
        titleField.setPromptText("Tapez le titre du produit ici");
        codeBarField = new TextField();
        codeBarField.setPromptText("Tapez le code à bare ici");
        numDeLotField = new TextField();
        numDeLotField.setPromptText("Tapez le numéro de lot ici");
        numSerieField = new TextField();
        numSerieField.setPromptText("Tapez le numéro de série ici");
        qtMinField = new TextField();
        qtMinField.setPromptText("Tapez la quantité minimal ici");
       
        titleField.setMinWidth(300);
        boxCategorie.setMinWidth(300);
        boxCategorie.setValue(boxCategorie.getItems().get(0));
        boxMarques.setMinWidth(300);
        GridPane.setHgrow(titleField, Priority.ALWAYS);
        GridPane.setHgrow(qtMinField, Priority.ALWAYS);
        GridPane.setHgrow(numDeLotField, Priority.ALWAYS);
        GridPane.setHgrow(numSerieField, Priority.ALWAYS);
        GridPane.setHgrow(qtMinField, Priority.ALWAYS);
        GridPane.setHgrow(boxMarques, Priority.ALWAYS);
        GridPane.setHgrow(boxCategorie, Priority.ALWAYS);
        
        Content.add(titleLabel, 0, 0);
        Content.add(titleField, 1, 0);
        Content.add(codeBarLabel, 0, 1);
        Content.add(codeBarField, 1, 1);
        Content.add(numDeLotLabel, 0, 2);
        Content.add(numDeLotField, 1, 2);
        Content.add(numSerieLabel, 0, 3);
        Content.add(numSerieField, 1, 3);
        Content.add(qtMinLabel, 0, 4);
        Content.add(qtMinField, 1, 4);
        Content.add(categorieLabel, 0, 5);
        Content.add(boxCategorie, 1, 5);
        Content.add(marqueLabel, 0, 6);
        Content.add(boxMarques, 1, 6);

        btValidate = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
        btCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().setContent(Content);
        dialog.getDialogPane().getButtonTypes().addAll(btValidate,btCancel);
        
        Button btnOK = (Button) dialog.getDialogPane().lookupButton(btValidate);
        btnOK.addEventFilter(ActionEvent.ACTION, eventFilter->{
            if (!isValid()) {
                System.out.println("is valid");
                eventFilter.consume();
            }
            
        });
        dialog.setResultConverter(this);
        
    }

    private boolean doEdit=false;
    
    @Override
    public Produit call(ButtonType param) {
        
        if (param == btValidate) {
            Produit p =(doEdit)?doEdit():doAdd();
            return p;
        }
        return  null;
        
    }

    private Produit doAdd() {
        Produit p = getInput();
        System.out.println("product = "+p.getTitre()+"-"+p.getCategorie().getTitreCategorie());
        ProduitJpaController pjc = new ProduitJpaController(emf);
        pjc.create(p);
        return p;
    }

    private Produit getInput() throws NumberFormatException {
        Produit p = new Produit();
        p.setTitre(titleField.getText());
        p.setCategorie(boxCategorie.getValue().getCategorie());
        p.setCodebar(codeBarField.getText().trim());
        DetailProduit detailProduit = new DetailProduit();
        detailProduit.setNumeroLot(numDeLotField.getText());
        detailProduit.setNumeroSerie(numSerieField.getText());
        p.setQtMin(Integer.parseInt(qtMinField.getText().trim()));
        p.setDetailProduit(detailProduit);
        ArrayList<Marque> marques = new ArrayList<>();
        boxMarques.getCheckModel().getCheckedItems().stream().forEach((checkedItem) -> {
            marques.add(checkedItem.marque);
        });
        p.setMarques(marques);
        return p;
    }

    void showAndWait() {
        dialog.showAndWait();
    }

    private boolean isValid() {
        TextField[] checkedFields = new TextField[]{codeBarField,qtMinField,titleField};
        for (TextField checkedField : checkedFields) {
            if (checkedField.getText().trim().isEmpty()) {
                checkedField.requestFocus();
                System.out.println("here");
                return false;
            }
        }
        try {
            int parseInt = Integer.parseInt(qtMinField.getText());
        } catch (Exception e) {
            qtMinField.requestFocus();
            return false;
        }
        if (boxCategorie.getValue()==null) {
            boxCategorie.requestFocus();
            return false;
        }
        return true;
                
    }

    private Produit doEdit() {
        Produit input = getInput();
        puttedProduct.setCategorie(input.getCategorie());
        puttedProduct.setTitre(input.getTitre());
        puttedProduct.setCodebar(input.getCodebar());
        puttedProduct.setMarques(input.getMarques());
        puttedProduct.setDetailProduit(input.getDetailProduit());
        puttedProduct.setQtMin(input.getQtMin());
        ProduitJpaController pjc = new ProduitJpaController(emf);
        try {
            pjc.edit(puttedProduct);
        } catch (Exception ex) {
            Logger.getLogger(ProduitDialogue.class.getName()).log(Level.SEVERE, null, ex);
    }
        return puttedProduct;
    }

}
