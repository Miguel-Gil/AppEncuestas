package AppEncuesta;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_PREF_SIZE;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lobo
 */
public class Home extends BorderPane{
 
 //Creating elements variables
 Button btnAbout = new Button("Acerca de ");
 
 Image imgJammy = new Image("img/encuestas.jpg");
 Image imgAbout = new Image("img/aboutIco.png", 30, 30, false, false);
 HBox hBox = new HBox();

    public Home() {
        super();        
        
        //Set Id
        this.setId("home");
        
        //Style
        ImageView imgLogo = new ImageView(imgJammy);
        ImageView imgLogoA = new ImageView(imgAbout);
        btnAbout.setGraphic(imgLogoA);
        hBox.getChildren().add(btnAbout);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(25, 50, 25, 25));
        
        //Add elements to layout
        this.setCenter(imgLogo);
        this.setBottom(hBox);

    }    
}
