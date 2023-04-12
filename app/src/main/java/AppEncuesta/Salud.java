/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppEncuesta;
//forzar cambio
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author lobo
 */
public class Salud extends BorderPane{

    public Salud(){
        //Set Id
        this.setId("salud");
        
        getSalud();
    }
    
    VBox vBox = new VBox(); 
    GridPane gridPane = new GridPane(); 
    GridPane lastGridPane = new GridPane(); 
    ChoiceBox<String> sexChoiceBox ; 
    DatePicker birthday; 
    TextField nationalityTF; 
    TextField residencePlaceTF; 
    RadioButton yesRB;
    RadioButton noRB;
    ToggleGroup radioGroup;
    DatePicker lastRevisionDate;
    RadioButton cronicYesRB;
    RadioButton cronicNoRB;
    ToggleGroup cronicRadioGroup;
    TextField cronicIllnessTF;
    RadioButton hereditaryYesRB;
    RadioButton hereditaryNoRB;
    ToggleGroup hereditaryRadioGroup;
    TextField hereditaryIllnessTF;
    RadioButton medicamentYesRB;
    RadioButton medicamentNoRB;
    ToggleGroup medicamentRadioGroup;
    TextField medicamentTF;
    CheckBox medicine1;
    CheckBox medicine2;
    CheckBox medicine3;
    CheckBox medicine4;

     Slider serviceSlider;
     Label serviceQuality;
     Button sendButton; 
    
    // Método que contiene toda la interfaz gráfica y lógica de la pestaña salud
    public void getSalud(){

        // Imagen
        Image image = new Image("img/salud.png", 120, 120, false, false);
        ImageView imageView = new ImageView(image);
        // Title
        Label saludTitle = new Label("Encuesta de Salud");
        saludTitle.setId("saludTitle");
        HBox hBox = new HBox(); 
        hBox.getChildren().add(imageView);
        hBox.getChildren().add(saludTitle);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(40, 0, 0, 0));
        hBox.setSpacing(45);
        this.setTop(hBox);
        this.setCenter(vBox);
        vBox.getChildren().add(gridPane);  
        vBox.setAlignment(Pos.CENTER);

        // Propiedades del GRIDPANE
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        //Sexo
        Label sexLabel = new Label("Sexo:");
        sexChoiceBox = new ChoiceBox<>();   
        sexChoiceBox.getItems().addAll("Sin seleccionar", "Hombre", "Mujer");         
        sexChoiceBox.setValue("Sin seleccionar");
        ImageView sexImage = new ImageView();  
        sexImage.setFitWidth(40);      
        sexImage.setFitHeight(40);     
        //Acción aplicada para cuando seleccionemos hombre o mujer nos aparezca la imagen correspondiente
        sexChoiceBox.setOnAction(event ->SexImage(sexImage, sexChoiceBox));
        gridPane.add(sexLabel, 0,1);
        gridPane.add(sexChoiceBox,1,1);
        gridPane.add(sexImage, 2, 1);
        
        //Fecha de nacimiento
        Label birthdayLabel = new Label("Fecha de nacimiento:");
        birthday = new DatePicker();
        birthday.setEditable(true);
        //Restringir la fecha en un rango
        birthday.setDayCellFactory((DatePicker picker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                 // Si la fecha es posterior a la atual
                if (date.isAfter(LocalDate.now()))  this.setDisable(true);
                // Si la fecha es anterior al año 1920
                if(date.isBefore(LocalDate.of(1920, Month.JANUARY, 1))){
                    this.setDisable(true);
                }   
            }
        }); 
        gridPane.add(birthdayLabel, 0,2);
        gridPane.add(birthday,1,2);
        
        // Nacionalidad
        Label nationalityLabel = new Label("Lugar de nacimiento:");
        nationalityTF = new TextField();
        // El textField solo admite letras y espacios
        nationalityTF.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        gridPane.add(nationalityLabel, 0,3);
        gridPane.add(nationalityTF, 1, 3);
        
        // Lugar de residencia
        Label residencePlaceLabel = new Label("Lugar de residencia:");
        residencePlaceTF = new TextField();
        // El textField solo admite letras y espacios
        residencePlaceTF.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        gridPane.add(residencePlaceLabel, 0,4);
        gridPane.add(residencePlaceTF, 1, 4);
        
        // ¿Visita al medico todo los años?
        Label doctorsVisitLabel = new Label("¿Visita al medico todo los años?:");
        // Botones Si y No
        yesRB = new RadioButton("Si");
        noRB = new RadioButton("No");
        radioGroup = new ToggleGroup();
        // Agregamos los botones al grupo creado
        yesRB.setToggleGroup(radioGroup);
        noRB.setToggleGroup(radioGroup);
        HBox radioGroupHBox = new HBox(yesRB, noRB);
        radioGroupHBox.setSpacing(100);
        gridPane.add(doctorsVisitLabel, 0,6);
        gridPane.add(radioGroupHBox, 1, 6);
        
        // Fecha de la última revisión: 
        Label lastRevisionLabel = new Label("Fecha de la última revisión:");
        lastRevisionDate = new DatePicker();
        lastRevisionDate.setEditable(false);
        // Restringir la fecha en un rango
        lastRevisionDate.setDayCellFactory((DatePicker picker) -> new DateCell() {
             @Override
             public void updateItem(LocalDate date, boolean empty) {
                 super.updateItem(date, empty);
                  // Si la fecha es posterior a la actual
                 if (date.isAfter(LocalDate.now()))  this.setDisable(true);
                 // Si la fecha es anterior al año 2010 no se podrá elegir
                 if(date.isBefore(LocalDate.of(2010, Month.JANUARY, 1))){
                     this.setDisable(true);
                 }   
             }
         }); 
        gridPane.add(lastRevisionLabel, 0,7);
        gridPane.add(lastRevisionDate,1,7);
        
        // Enfermedad crónica
        Label cronicIllnessLabel = new Label("¿Tiene alguna enfermedad crónica?: ");
        // Botones Si y No
        cronicYesRB = new RadioButton("Si");
        cronicNoRB = new RadioButton("No");
        cronicRadioGroup = new ToggleGroup(); // Creamos grupo para los botones
        // Agregamos los botones al grupo creado
        cronicYesRB.setToggleGroup(cronicRadioGroup);
        cronicNoRB.setToggleGroup(cronicRadioGroup);
        HBox cronicRadioGroupHBox = new HBox(cronicYesRB, cronicNoRB);
        cronicRadioGroupHBox.setSpacing(100);
        // TextField para indicar cual
        cronicIllnessTF = new TextField();
        cronicIllnessTF.setPromptText("¿Cuál?");
        // El textField solo admite letras y espacios
        cronicIllnessTF.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        gridPane.add(cronicIllnessLabel, 0,9);
        gridPane.add(cronicRadioGroupHBox, 1,9);
        gridPane.add(cronicIllnessTF, 2, 9);
        // Darle acción a los botones si y no - Si es si, se activa el textfield, si es no, se desactiva y completa con Ninguna
        cronicNoRB.setOnAction(event ->DisableTextField(cronicNoRB,cronicIllnessTF ));
        cronicYesRB.setOnAction(event ->EnableTextField(cronicYesRB,cronicIllnessTF ));
        
        // Enfermedad  hereditaria
        Label hereditaryIllnessLabel = new Label("¿Tiene alguna enfermedad hereditaria?: ");
        // Botones Si y No
        hereditaryYesRB = new RadioButton("Si");
        hereditaryNoRB = new RadioButton("No");
        hereditaryRadioGroup = new ToggleGroup();
        hereditaryYesRB.setToggleGroup(hereditaryRadioGroup);
        hereditaryNoRB.setToggleGroup(hereditaryRadioGroup);
        HBox hereditaryRadioGroupHBox = new HBox(hereditaryYesRB, hereditaryNoRB);
        hereditaryRadioGroupHBox.setSpacing(100);
        // TextField para indicar cual
        hereditaryIllnessTF = new TextField();
        // El textField solo admite letras y espacios
        hereditaryIllnessTF.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        hereditaryIllnessTF.setPromptText("¿Cuál?"); // Por defecto
        gridPane.add(hereditaryIllnessLabel, 0,10);
        gridPane.add(hereditaryRadioGroupHBox, 1, 10);
        gridPane.add(hereditaryIllnessTF, 2, 10);
        // Darle acción a los botones si y no - Si es si, se activa el textfield, si es no, se desactiva y completa con Ninguna
        hereditaryNoRB.setOnAction(event ->DisableTextField(hereditaryNoRB,hereditaryIllnessTF ));
        hereditaryYesRB.setOnAction(event ->EnableTextField(hereditaryYesRB,hereditaryIllnessTF ));
        
        // Toma alguna medicación
        Label medicamentLabel = new Label("¿Toma alguna medicación?:");
        // Botones Si y No
        medicamentYesRB = new RadioButton("Si");
        medicamentNoRB = new RadioButton("No");
        medicamentRadioGroup = new ToggleGroup(); // Creamos grupo para los botones
        // Agregamos los botones al grupo creado
        medicamentYesRB.setToggleGroup(medicamentRadioGroup);
        medicamentNoRB.setToggleGroup(medicamentRadioGroup);
        HBox medicamentRadioGroupHBox = new HBox(medicamentYesRB, medicamentNoRB);
        medicamentRadioGroupHBox.setSpacing(100);
        // TextField para indicar cual
        medicamentTF = new TextField();
        // El textField solo admite letras y espacios
        medicamentTF.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        medicamentTF.setPromptText("¿Cuál?"); // Por defecto
        gridPane.add(medicamentLabel, 0,11);
        gridPane.add(medicamentRadioGroupHBox, 1, 11);
        gridPane.add(medicamentTF, 2, 11);
        // Darle acción a los botones si y no - Si es si, se activa el textfield, si es no, se desactiva y completa con Ninguna
        medicamentNoRB.setOnAction(event ->DisableTextField(medicamentNoRB,medicamentTF ));
        medicamentYesRB.setOnAction(event ->EnableTextField(medicamentYesRB,medicamentTF ));

        
        // Botón ENVIAR ENCUESTA
        sendButton = new Button("ENVIAR");
        lastGridPane.add(sendButton, 4, 7);
        //sendButton.setOnAction(event -> ConfirmationTab());
    }

    // Método para cuando se seleccione hombre o mujer aparezca una imagen
    private void SexImage(ImageView sexImage, ChoiceBox sexChoiceBox){        
        
        Image man = new Image("img/hombre.png");
        Image woman = new Image("img/mujer.png");        
        
        switch ((String)sexChoiceBox.getValue()) {
            case "Sin seleccionar":
                sexImage.setImage(null);
                break;
            case "Hombre":
                sexImage.setImage(man);
                break;
            case "Mujer":
                sexImage.setImage(woman);
                break;            
        }
    }

    // Método para poner en rojo los bordes de los campos que no han sido rellenos
    private boolean UnfilledFields(){
        boolean error = false;
        // Sexo 
        if ("Sin seleccionar".equals(sexChoiceBox.getValue())){
            sexChoiceBox.setStyle("-fx-border-color: red;");
            error=true;
        }
        else sexChoiceBox.setStyle("-fx-border-color: none;");   
        
        // Fecha de nacimiento
        if (birthday.getValue() == null){
            birthday.setStyle("-fx-border-color: red;");
            error=true;
        }
        else birthday.setStyle("-fx-border-color: none;");  
        
        // Lugar de nacimiento
        if (nationalityTF.getText().isEmpty()){
            nationalityTF.setStyle("-fx-border-color: red;");
            error=true;
        }
        else nationalityTF.setStyle("-fx-border-color: none;");
        
        // Lugar de residencia
        if (residencePlaceTF.getText().isEmpty()){
            residencePlaceTF.setStyle("-fx-border-color: red;");
            error=true;
        }
        else residencePlaceTF.setStyle("-fx-border-color: none;");
        
        // Visita al medico todos los años
        if (!yesRB.isSelected() && !noRB.isSelected()){
            yesRB.setStyle("-fx-border-color: red;");
            noRB.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            yesRB.setStyle("-fx-border-color: none;");
            noRB.setStyle("-fx-border-color: none;");
        }
        
        // Fecha ultima revisión
        if (lastRevisionDate.getValue() == null) {
            lastRevisionDate.setStyle("-fx-border-color: red;");
            error=true;
        }
        else lastRevisionDate.setStyle("-fx-border-color: none;");
        
        // Enfermedad crónica
        if (cronicIllnessTF.getText().isEmpty() ){
            cronicIllnessTF.setStyle("-fx-border-color: red;");
            error=true;
        }
        else cronicIllnessTF.setStyle("-fx-border-color: none;");
        if (!cronicYesRB.isSelected() && !cronicNoRB.isSelected()){
            cronicYesRB.setStyle("-fx-border-color: red;");
            cronicNoRB.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            cronicYesRB.setStyle("-fx-border-color: none;");
            cronicNoRB.setStyle("-fx-border-color: none;");
        }
        
        // Enfermedad hereditaria
        if (hereditaryIllnessTF.getText().isEmpty()){
            hereditaryIllnessTF.setStyle("-fx-border-color: red;");
            error=true;
        }
        else hereditaryIllnessTF.setStyle("-fx-border-color: none;");
        if (!hereditaryYesRB.isSelected() && !hereditaryNoRB.isSelected()){
            hereditaryYesRB.setStyle("-fx-border-color: red;");
            hereditaryNoRB.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            hereditaryYesRB.setStyle("-fx-border-color: none;");
            hereditaryNoRB.setStyle("-fx-border-color: none;");
        }

        return error;
    }
    
    // Método para que salga una ventana emergente al presionar enviar encuesta
    private void SendErrorTab(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Salud - Error");
        alert.setContentText("Todos los campos deben ser cumplimentados para el correcto envio de la encuesta.");
        alert.showAndWait();
    }
    
    // Método para informat del envio de la encuesta
    private void InformationTab(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Salud - Información de envio.");
        alert.setContentText("Su encuesta ha sido enviada satisfactoriamente.");
        alert.showAndWait();
    }
    
    // Método para limpiar los campos una vez se haya enviado la encuesta
    private void FieldsCleaning(){
            sexChoiceBox.setValue("Sin seleccionar");
            nationalityTF.clear();
            birthday.setValue(null);
            nationalityTF.clear();
            residencePlaceTF.clear();
            yesRB.setSelected(false);
            noRB.setSelected(false);
            lastRevisionDate.setValue(null);
            cronicYesRB.setSelected(false);
            cronicNoRB.setSelected(false);
            cronicIllnessTF.clear();
            hereditaryYesRB.setSelected(false);
            hereditaryNoRB.setSelected(false);
            hereditaryIllnessTF.clear();
            medicamentYesRB.setSelected(false);
            medicamentNoRB.setSelected(false);
            medicamentTF.clear();
            medicine1.setSelected(false);
            medicine2.setSelected(false);
            medicine3.setSelected(false);
            medicine4.setSelected(false);
            serviceQuality.setText("Sin evaluar");
            serviceSlider.setValue(0);
    }
    
    // Método para confirmar que deseamos enviar los datos y se genera csv
    private void ConfirmationTab(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Salud - Confirmación de envio.");
        alert.setContentText("¿Está seguro de que desea enviar los datos de la encuesta? Revise todos "
                + "los campos antes de proceder con el envio.");
        Optional<ButtonType> action = alert.showAndWait();
        // Si aceptamos aceptar, crearemos el file CSV
        if (action.get() == ButtonType.OK) {
            CsvFile();
        }
    }
  
    // Método para generar el fichero CSV
    private void CsvFile(){
        
        if (!UnfilledFields()){
           String fileName = " Enc_Salud.csv";  // Nombre del archivo .csv
            
          try {
            // Obtiene la ruta atual en tipo texto
            String currentPath = Paths.get("").toAbsolutePath().normalize().toString();
            String csvFolder = "/src/csvFolder";  // Nombre de la carpeta que vamos a crear para guardar el archivo creado
            String csvPath = currentPath + csvFolder;   // Ruta del archivo
            File newFolder = new File(csvPath); // Crea el directorio en la ruta dada por csvPath
            newFolder.mkdir(); // Crea el directorio si no está ya creado
            
            // Obtener la fecha actual con el siguiente formato indicado:
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy_HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            // Inicialización de la clase que escribirá en nuestro txt
            File statText = new File(csvPath + "/" + fileName);
            /* Aplicamos el booleano TRUE en FileOutputStream, para de esta manera indicar que queremos hacer append. Esto se
            traduce a que si se hace un envio de una segunda encuesta, no se sobreescriba el csv, sino que se 
            adjunte al final del documento.
            */
            FileOutputStream fileOS = new FileOutputStream(statText, true); // Escribe el texto
            OutputStreamWriter outputSW = new OutputStreamWriter(fileOS); // Fllujo de caracteres a bytes
               // Lo que vamos a escribir en el documento .csv
               try (Writer writer = new BufferedWriter(outputSW)) {
                   // Lo que vamos a escribir en el documento .csv
                   writer.write(dateTimeFormatter.format(now)+";"+sexChoiceBox.getValue()+";"+birthday.getValue()+";"+nationalityTF.getText()+";" + residencePlaceTF.getText() + ";" +
                           CheckRadiobutton(yesRB) + ";" + lastRevisionDate.getValue() + ";" + CheckRadiobutton(cronicYesRB)+";"+
                           cronicIllnessTF.getText()  + ";" + CheckRadiobutton(hereditaryYesRB)+";"+hereditaryIllnessTF.getText()+";"+
                           CheckRadiobutton(medicamentYesRB)+";"+medicamentTF.getText()+";" + ";\n");
                   writer.close();// Cerramos el writer
               }
            
            InformationTab(); // Ventana de información de envio de encuesta
            FieldsCleaning(); // Limpiar campos una vez enviada la encuesta
            
            } catch (IOException e) {
                System.err.println("An error has ocurred writing to the file. Error:  " + e);
            }
        }else{
            SendErrorTab();
        }
    }
    
    // Método para la obtención del radiobutton marcado
    private String CheckRadiobutton(RadioButton radioButton){
        
        String selectedButton;  // Botón seleccionado
        
        if (radioButton.isSelected()){
            selectedButton= "Si";
        }else{
            selectedButton ="No";
        }
        
        return selectedButton;
    }
    
    // Método para cuando presionemos un radiobutton "No" se desactive el textfield
    private void DisableTextField(RadioButton radioButton, TextField textField){
        
        if (radioButton.isSelected()){
            textField.setText("Ninguna");
            textField.setDisable(true);
        }
    }
    
    // Método para cuando presionemos un radiobutton "Si" se active el textfield
    private void EnableTextField(RadioButton radioButton, TextField textField){
        
        if (radioButton.isSelected()){
            textField.setText("");
            textField.setDisable(false);
        }
    }
   
    // Clase creada para controlar que solo se introduzcan letras y espacios
    class ManejadorFilter implements EventHandler<KeyEvent> {
      
        @Override
        public void handle(KeyEvent event) {
            if (Character.isDigit(event.getCharacter().charAt(0))) {
                event.consume();
            }
        }
    } 
    
    
}
