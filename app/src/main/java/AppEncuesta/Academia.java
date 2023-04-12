/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppEncuesta;
//forzar cambio
//import AppEncuesta.Control.ManejadorFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author lobo
 */
public class Academia extends BorderPane
{    
    
    public TextField textName = new TextField();
    public ChoiceBox choiceEdades = new ChoiceBox();
    public ChoiceBox choiceHermanos = new ChoiceBox();
    public ChoiceBox choiceEstudios = new ChoiceBox();
    public RadioButton bottomBecasSi = new RadioButton();
    public RadioButton bottomBecasNo = new RadioButton();
    public Slider PuntosEstudio = new Slider(); 
    public Slider AyudaBeca = new Slider();
    public RadioButton EstudioSi = new RadioButton(); 
    public RadioButton EstudioNo = new RadioButton();
    public RadioButton dispSi = new RadioButton();
    public RadioButton dispNo = new RadioButton();
    public TextField TextDispositivo = new TextField();
    
    
    
    @SuppressWarnings({"unchecked", "unchecked", "unchecked"})
    public Academia()   
    {
    
        super();   
        
        this.setId("academia");
        // Grid y parametros declaración y configuración de espacio
        GridPane grid = new GridPane();
        grid.setHgap(13);
        grid.setVgap(22);
        grid.setPadding(new Insets(25, 25, 25, 25));
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(2);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(30);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(0);
        ColumnConstraints c4 = new ColumnConstraints();
        c4.setPercentWidth(35);
        grid.getColumnConstraints().addAll(c1, c2, c3,c4);
        grid.setAlignment(Pos.CENTER);
        this.setCenter(grid);
               
        HBox hBox = new HBox();   
        hBox.setPadding(new Insets(30, 0, 5, 240));
        hBox.setAlignment(Pos.TOP_LEFT);
        this.setTop(hBox);
        
        // imagen para la cabecera
        Image image = new Image("img/academica.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
        
        Label encuestaAcademia;
        encuestaAcademia = new Label("Encuesta Académica");
        encuestaAcademia.setId("academicaTitle");
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.getChildren().add(imageView);
        hBox.getChildren().add(encuestaAcademia);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(40, 0, 0, 0));
        hBox.setSpacing(45);
        this.setTop(hBox);
        
        // Pregunta Nombre
        Label Nombre = new Label("Nombre:");
        grid.add(Nombre, 1, 1);
        textName = new TextField();
        textName.setPromptText("Introduce el nombre");
        textName.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        grid.add(textName, 3, 1);
      
        // Pregunta introducir edad
        Label Edad = new Label("Edad:");
        grid.add(Edad, 1, 2);
        choiceEdades = new ChoiceBox();
        choiceEdades.getItems().addAll("Sin seleccionar", "menos de 16" ,"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", " + de 26");
        choiceEdades.setValue("Sin seleccionar");
        grid.setAlignment(Pos.CENTER);
        grid.add(choiceEdades, 3, 2);

        
        //Pregunta conocer numero de hermanos
        Label NumHermanos = new Label("¿Cuántos hermanos tienes?");
        grid.add(NumHermanos, 1, 4);
        choiceHermanos = new ChoiceBox();
        choiceHermanos.getItems().addAll("Sin seleccionar","1", "2", "3", "4", "+ de 4");
        choiceHermanos.setValue("Sin seleccionar");
        grid.setAlignment(Pos.CENTER);
        grid.add(choiceHermanos, 3, 4);

        
        //Pregunta becas verdadero o falso
        HBox botonesBecas = new HBox();
        Label RecibioBecas = new Label("¿Recibio becas de estudio?");
        grid.add(RecibioBecas, 1, 6);
        ToggleGroup Becas = new ToggleGroup();
        bottomBecasSi = new RadioButton("Si");
        bottomBecasNo = new RadioButton("No");
        bottomBecasSi.setPadding(new Insets(0,30,0,0));
        bottomBecasNo.setPadding(new Insets(0,0,0,30));
        bottomBecasSi.setToggleGroup(Becas);
        bottomBecasNo.setToggleGroup(Becas);
        botonesBecas.getChildren().addAll(bottomBecasSi, bottomBecasNo);
        grid.add(botonesBecas, 3, 6);

        
        // Pregunta sobre lo aprendido
        Label PuntuaAprendizaje = new Label("Puntue con más de 0 la utilidad de su aprendizaje:");
        grid.add(PuntuaAprendizaje, 1, 8);
        PuntosEstudio = new Slider(0, 10, 0);
        PuntosEstudio.setMajorTickUnit(1.0);
        PuntosEstudio.setMinorTickCount(7);
        PuntosEstudio.setShowTickLabels(true);
        grid.add(PuntosEstudio, 3, 8);
        
        // Pregunta Ayuda becas por puntos
        Label AyudaBecas = new Label("Puntue con más de 0 la ayuda que representan las becas:");
        grid.add(AyudaBecas, 1, 9);
        AyudaBeca = new Slider(0, 10, 0);
        AyudaBeca.setMajorTickUnit(1.0);
        AyudaBeca.setMinorTickCount(7);
        AyudaBeca.setShowTickLabels(true);
        grid.add(AyudaBeca, 3, 9);
        
        // Pregunta para conocer si realiza estudios en centros privados
        HBox botonesPrivado = new HBox();
        Label EstudiosPrivados = new Label("¿Realizo estudios en centros privados?");
        grid.add(EstudiosPrivados, 1, 11);
        ToggleGroup EstudioPrivado = new ToggleGroup();
        EstudioSi = new RadioButton("Si");
        EstudioNo = new RadioButton("No");
        EstudioSi.setPadding(new Insets(0,30,0,0));
        EstudioNo.setPadding(new Insets(0,0,0,30));
        EstudioSi.setToggleGroup(EstudioPrivado);
        EstudioNo.setToggleGroup(EstudioPrivado);
        botonesPrivado.getChildren().addAll(EstudioSi, EstudioNo);
        grid.add(botonesPrivado, 3, 11);
        
        // Pregunta para conocer si tiene dispositivos para el estudio 
        HBox botonesDisp = new HBox();
        Label DispositivosEstudio = new Label("¿Cuenta con dispositivos para el estudio?");
        grid.add(DispositivosEstudio, 1, 12);
        ToggleGroup DispotivosEstudios = new ToggleGroup();
        dispSi = new RadioButton("Si");
        dispNo = new RadioButton("No");
        dispSi.setPadding(new Insets(0,30,0,0));
        dispNo.setPadding(new Insets(0,0,0,30));
        dispSi.setToggleGroup(DispotivosEstudios);
        dispNo.setToggleGroup(DispotivosEstudios);
        botonesDisp.getChildren().addAll(dispSi, dispNo);
        grid.add(botonesDisp, 3, 12);
        
        //Pregunta dispositivos estudio
        Label DispositivoEstudio = new Label("¿Qué dispositivos?");
        grid.add(DispositivoEstudio, 1, 13);
        TextDispositivo = new TextField();
        TextDispositivo.setPromptText("Introduce los dispositivos separados por comas");
        grid.add(TextDispositivo,3, 13);
        
        //control para el introduccion de datos no permitiendo numeros ( comentar grupo propteger contra copypaste)
        TextDispositivo.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
   
        //Desactivar campo de texto dependiendo de si esta marcado si o no en los radiobutton
        dispSi.setOnAction(event ->ActiveTextField(dispSi,TextDispositivo ));
        dispNo.setOnAction(event ->BlockTextField(dispNo,TextDispositivo ));
  
        
        // boton enviar cuesitonario
        Button send = new Button("ENVIAR");
        grid.add(send, 4,16);
        send.setOnAction(event -> PreviosConfirmation());
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
    
    // Método desactivar el textfield
    private void BlockTextField(RadioButton radioButton, TextField textField){
        
        if (radioButton.isSelected()){
            textField.setText("No dispone de dispositivos.");
            textField.setDisable(true);
        }
    }
    
    // Método para activar el textfield
    private void ActiveTextField(RadioButton radioButton, TextField textField){
        
        if (radioButton.isSelected()){
            textField.setText("");
            textField.setDisable(false);
        }
    }
    
    // metodo que muestra una alerta de envio de encuesta advirtiendo
    
     private void PreviosConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Academica- Confirmar envio.");
        alert.setContentText("¿Está seguro de que desea enviar los datos de la encuesta?  Revise todos "
                + "los campos antes de proceder con el envio.");
        Optional<ButtonType> action = alert.showAndWait();
        // paso previo crear csv
       if (action.get() == ButtonType.OK) {
           CsvFile();
       } 
    }
      // Método para generar el fichero CSV
     
    private void CsvFile(){
        
        if (!UnfilledFields()){
           String fileName = " Enc_Academica.csv";  // Nombre del archivo .csv
            
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
            /*Aplicamos el booleano TRUE en FileOutputStream, para de esta manera indicar que queremos hacer append. Esto se
            traduce a que si se hace un envio de una segunda encuesta, no se sobreescriba el csv, sino que se 
            adjunte al final del documento.
            */
            FileOutputStream fileOS = new FileOutputStream(statText, true); // Escribe el texto
            OutputStreamWriter outputSW = new OutputStreamWriter(fileOS); // Fllujo de caracteres a bytes
               // Lo que vamos a escribir en el documento .csv
               try (Writer writer = new BufferedWriter(outputSW)) {
                   // Lo que vamos a escribir en el documento .csv
                   writer.write(dateTimeFormatter.format(now) + ";" + textName.getText()+ ";" + choiceEdades.getValue() + ";" + choiceHermanos.getValue() + ";" + choiceEstudios.getValue() + ";" +
                           CheckRadiobutton(bottomBecasSi) + ";" + PuntosEstudio.getValue() + ";" + AyudaBeca.getValue() + ";" +
                           CheckRadiobutton(EstudioSi) + ";" + CheckRadiobutton(dispSi)  + ";" + TextDispositivo.getText() + ";\n");
                   // Cerramos el writer
               }
            
            FinalConfirmation(); // Ventana de información de envio de encuesta
            FieldsCleaning(); // Limpiar campos una vez enviada la encuesta
            
            } catch (IOException e) {
                System.err.println("An error has ocurred writing to the file. Error:  " + e);
            }
        }else{
            SendErrorTab();
        }
    } 
     
    // Método para que salga una ventana emergente al presionar enviar encuesta
    private void SendErrorTab(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Académica - Error");
        alert.setContentText("Todos los campos deben ser cumplimentados para el correcto envio de la encuesta.");
        alert.showAndWait();
    }
     // alerta final para el envio de la encuesta
     
     private void FinalConfirmation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Académica - Información de envio.");
        alert.setContentText("Su encuesta ha sido enviada satisfactoriamente.");
        alert.showAndWait();
    }
     
     // Metodo que limpia los campos de la encuesta
    @SuppressWarnings("unchecked")
    private void FieldsCleaning(){
            textName.clear();
            choiceEdades.setValue("Sin seleccionar");
            choiceHermanos.setValue("Sin seleccionar");
            choiceEstudios.setValue("Seleccione una");
            bottomBecasSi.setSelected(false);
            bottomBecasNo.setSelected(false);
            PuntosEstudio.setValue(0);
            AyudaBeca.setValue(0);
            EstudioSi.setSelected(false);
            EstudioNo.setSelected(false);
            dispSi.setSelected(false);
            dispNo.setSelected(false);
            TextDispositivo.clear();     
    }
    
     
    //Método para obtener el radiobutton 
    private String CheckRadiobutton(RadioButton radioButton){
        
        String selectedButton;  //Botón seleccionado
        
        if (radioButton.isSelected()){
            selectedButton= "Si";
        }else{
            selectedButton ="No";
        }
        
        return selectedButton;
    }
    
  
    
    // Método para poner en rojo los bordes de los campos que no han sido rellenos
    private boolean UnfilledFields(){
        boolean error = false;
        // nombre
        if (textName.getText().isEmpty()){
            textName.setStyle("-fx-border-color: red;");
            error=true;
        }
        else textName.setStyle("-fx-border-color: none;");
        
        //edad
        if ("Sin seleccionar".equals(choiceEdades.getValue())){
            choiceEdades.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choiceEdades.setStyle("-fx-border-color: none;");
        
        //hermanos
        if ("Sin seleccionar".equals(choiceHermanos.getValue())){
            choiceHermanos.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choiceHermanos.setStyle("-fx-border-color: none;");  
        
        //estudios
        if ("Seleccione una".equals(choiceEstudios.getValue())){
            choiceEstudios.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choiceEstudios.setStyle("-fx-border-color: none;");
        
        
        // recibe becas si o no
        if (!bottomBecasSi.isSelected() && !bottomBecasNo.isSelected()){
            bottomBecasSi.setStyle("-fx-border-color: red;");
            bottomBecasNo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            bottomBecasSi.setStyle("-fx-border-color: none;");
            bottomBecasNo.setStyle("-fx-border-color: none;");
        }
       
        
        // Calidad estudios
        if (PuntosEstudio.getValue() == 0){
            PuntosEstudio.setStyle("-fx-border-color: red;");
            error=true;
        }
        else PuntosEstudio.setStyle("-fx-border-color: none;");
        
        // Ayuda Becas
        if (AyudaBeca.getValue() == 0){
            AyudaBeca.setStyle("-fx-border-color: red;");
            error=true;
        }
        else AyudaBeca.setStyle("-fx-border-color: none;");
        
        // estudios privados si o no
        
        if (!EstudioSi.isSelected() && !EstudioNo.isSelected()){
            EstudioSi.setStyle("-fx-border-color: red;");
            EstudioNo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            EstudioSi.setStyle("-fx-border-color: none;");
            EstudioNo.setStyle("-fx-border-color: none;");
        }
         
         // dispositivos si o no
         
          if (!dispSi.isSelected() && !dispNo.isSelected()){
            dispSi.setStyle("-fx-border-color: red;");
            dispNo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            dispSi.setStyle("-fx-border-color: none;");
            dispNo.setStyle("-fx-border-color: none;");
        }
          
          // nombre dispositivos
        if (TextDispositivo.getText().isEmpty()){
            TextDispositivo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else TextDispositivo.setStyle("-fx-border-color: none;");
       
        
        return error;
    }

    
    
}
