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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author lobo
 */
public class Poblacion extends BorderPane
{

    public TextField textSector = new TextField();
    public TextField textNacimiento = new TextField();
    public TextField textResides = new TextField();
    public TextField textDesempleo = new TextField();
    public ChoiceBox choiceEdad = new ChoiceBox();
    public ChoiceBox choicePersonasHogar = new ChoiceBox();
    public ChoiceBox choiceServiciosHogar = new ChoiceBox();
    public ChoiceBox choiceDispoElect = new ChoiceBox();
    public ChoiceBox choiceNivelEducativo = new ChoiceBox();
    public ChoiceBox choiceSigueEstudiando = new ChoiceBox();
    public ChoiceBox choiceTrabajasPorSemana = new ChoiceBox();
    public ChoiceBox choiceSalario = new ChoiceBox();
    public RadioButton radioBtnTrabajandoSi = new RadioButton("Si");
    public RadioButton radioBtnTrabajandoNo = new RadioButton("No");
    public RadioButton radioBtnEstudioTrabajoSi = new RadioButton("Si");
    public RadioButton radioBtnEstudioTrabajoNo = new RadioButton("No");
    public RadioButton radioBtnDesempleoSi = new RadioButton("Si");
    public RadioButton radioBtnDesempleoNo = new RadioButton("No");  
    public CheckBox checkBoxEstudiando = new CheckBox();


    
    @SuppressWarnings("unchecked")
    public Poblacion () 
    {
        super();       
        // Declaracion para GRID
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(25, 25, 25, 25));
        this.setCenter(grid);
        this.setId("poblacion");
        

        // Añadir el titulo con la imagen
        HBox hBox = new HBox();        
        Image image = new Image("img/poblacion.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
        Label encuestaPoblacion = new Label("Encuesta de Poblacion");
        encuestaPoblacion.setId("poblacionTitle");
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.getChildren().add(imageView);
        hBox.getChildren().add(encuestaPoblacion);
        hBox.setAlignment(Pos.CENTER);    // Alineación arriba a la izquierda
        hBox.setPadding(new Insets(40, 0, 0, 0));
        hBox.setSpacing(45);
        this.setTop(hBox);
       
        // Eleccion para la edad
        Label Edad = new Label("Edad:");
        grid.add(Edad, 1, 1);
        choiceEdad.getItems().addAll("Menos de 15", "Entre 15 y 18", "Entre 19 y 35", "Entre 36 y 60", "Más de 60");
        grid.setAlignment(Pos.CENTER);
        grid.add(choiceEdad, 2, 1);
        
        
        // Cuadro de texto para el Lugar de nacimiento
        Label LugarNacimiento = new Label("Lugar de nacimiento:");
        textNacimiento.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        grid.add(LugarNacimiento, 1, 2);
        grid.add(textNacimiento, 2, 2);
        
        // Cuadro de texto para el Lugar que reside
        Label LugarResides = new Label("Lugar en el que resides:");
        textResides.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        grid.add(LugarResides, 3, 2);
        grid.add(textResides, 4, 2);
        
        // Eleccion para personas que viven en el hogar
        Label PersonasHogar = new Label("¿Cuántas personas viven en su hogar?");
        grid.add(PersonasHogar, 1, 4);
        choicePersonasHogar.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        grid.add(choicePersonasHogar, 2, 4);
        
        // Eleccion para servicios del hogar
        Label ServiciosHogar = new Label("¿Qué servicios tiene en su hogar?");
        grid.add(ServiciosHogar, 1, 5);
        choiceServiciosHogar.getItems().addAll("Agua potable", "Electricidad", "Gas", "Internet", "Televisión por cable");
        grid.add(choiceServiciosHogar, 2, 5);
        
        // Eleccion para dispositivos electrónicos
        Label DispositivosElect = new Label("¿Qué Dispositivos electrónicos hay en su hogar?");
        grid.add(DispositivosElect, 1, 6);
        choiceDispoElect.getItems().addAll("Computadora de escritorio", "Computadora portátil", "Móvil", "Tablet", "Televisión");
        grid.add(choiceDispoElect, 2, 6);
        
        // Eleccion para nivel educativo
        Label NivelEducativo = new Label("¿Cuál es su nivel educativo alcanzado?");
        grid.add(NivelEducativo, 1, 7);
        choiceNivelEducativo.getItems().addAll("Primaria", "Secundaria", "Bachillerato", "Grado", "Posgrado", "Ninguno");
        grid.add(choiceNivelEducativo, 2, 7);
        
        // Eleccion si sigue estudiando
        grid.add(checkBoxEstudiando, 0, 8);
        Label SigueEstudiando = new Label("¿Actualmente estas estudiando?");
        grid.add(SigueEstudiando, 1, 8);
        choiceSigueEstudiando.getItems().addAll("Primaria", "Secundaria", "Bachillerato", "Grado", "Posgrado", "Ninguno");
        checkBoxEstudiando.setOnAction(e -> desactivarChoice(checkBoxEstudiando, choiceSigueEstudiando));
        choiceSigueEstudiando.setValue("Ninguno");
        choiceSigueEstudiando.setDisable(true);
        grid.add(choiceSigueEstudiando, 2, 8);
                
        //Eleccion para si sigue trabajando
        Label SigueTrabajando = new Label("¿Actualmente trabajas?");
        grid.add(SigueTrabajando, 1, 9);
        ToggleGroup GrupoTrabajando = new ToggleGroup();
        radioBtnTrabajandoSi.setToggleGroup(GrupoTrabajando);
        radioBtnTrabajandoNo.setToggleGroup(GrupoTrabajando);
        grid.add(radioBtnTrabajandoSi, 2, 9);
        grid.add(radioBtnTrabajandoNo, 3, 9);
        
        // Texto para indicar en qué sector
        Label Sector = new Label("¿En qué sector?");
        textSector.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        grid.add(Sector, 4, 9);
        grid.add(textSector, 5, 9);
        
        // Seleccion para elegir cuantas horas trabaja por semana
        Label TrabajasPorSemana = new Label("¿Cuántas horas trabajas por semana?");
        grid.add(TrabajasPorSemana, 1, 11);
        choiceTrabajasPorSemana.getItems().addAll("Menos de 10 horas", "Entre 10 y 20 horas", "Entre 20 y 30 horas", "Entre 30 y 40 horas", "Entre 40 y 50 horas", "Más de 50 horas");
        grid.add(choiceTrabajasPorSemana, 2, 11);
        
        //Eleccion para si realizo algun estudio, curso capacitacion para realizar este trabajo
        Label EstudioTrabajo = new Label("¿Realizó algún estudio, curso o capacitación para realizar ese trabajo?");
        grid.add(EstudioTrabajo, 1, 12);
        ToggleGroup GrupoEstudioTrabajo = new ToggleGroup();
        radioBtnEstudioTrabajoSi.setToggleGroup(GrupoEstudioTrabajo);
        radioBtnEstudioTrabajoNo.setToggleGroup(GrupoEstudioTrabajo);
        grid.add(radioBtnEstudioTrabajoSi, 2, 12);
        grid.add(radioBtnEstudioTrabajoNo, 3, 12);
        
        // Seleccion para indicar el salario
        Label Salario = new Label("Indique su salario:");
        grid.add(Salario, 1, 13);
        choiceSalario.getItems().addAll("Menos de 10.000€", "Entre 10.000€ y 15.000€", "Entre 15.000€ y 20.000€", "Entre 20.000€ y 50.000€", "Entre 50.000€ y 100.000€", "Más de 100.000€");
        grid.add(choiceSalario, 2, 13);
 
                
        //Seleccion y texto si recibio alguna pension, jubilacion o seguro de desempleo
        Label Desempleo = new Label("¿Recibes alguna pensión, jubilación o seguro de desempleo?");
        grid.add(Desempleo, 1, 14);
        ToggleGroup GrupoDesempleo = new ToggleGroup();
        radioBtnDesempleoSi.setToggleGroup(GrupoDesempleo);
        radioBtnDesempleoNo.setToggleGroup(GrupoDesempleo);
        grid.add(radioBtnDesempleoSi, 2, 14);
        radioBtnDesempleoSi.setOnAction(e -> activarText(radioBtnDesempleoSi, textDesempleo));
        grid.add(radioBtnDesempleoNo, 3, 14);
        radioBtnDesempleoNo.setOnAction(e -> desactivarText(radioBtnDesempleoNo, textDesempleo));
        textDesempleo.setPromptText("Indique la cantidad en números");
        //textDesempleo.addEventFilter(KeyEvent.KEY_TYPED, new NumberFilter());
        grid.add(textDesempleo, 2, 15);
        
        //Implementado el metodo para activar y desactivar todos los campos relacionados con los trabajos
        radioBtnTrabajandoSi.setOnAction(e -> activarTrabajos(radioBtnTrabajandoSi, textSector, choiceTrabajasPorSemana,radioBtnEstudioTrabajoSi, radioBtnEstudioTrabajoNo, choiceSalario));
        radioBtnTrabajandoNo.setOnAction(e -> desactivarTrabajos(radioBtnTrabajandoNo, textSector, choiceTrabajasPorSemana,radioBtnEstudioTrabajoSi, radioBtnEstudioTrabajoNo, choiceSalario));

        
        //Boton para enviar
        Button btnEnviar = new Button("ENVIAR");
        grid.add(btnEnviar, 4, 17);
        btnEnviar.setOnAction(e -> ConfirmationTab());
    }
    
    //Metodo para mostrar una alerta
    public void mostrarAlertError(String message) 
    {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Revise bien el formulario " + "`" + message + "´" + " no es un numero.\n¿Recibes alguna pensión, jubilación o seguro de desempleo?");
            alert.showAndWait();
    }
    //Metodo para comprobar si un campo tiene un numero o no y mostrar un error
    public boolean compruebaNumero(TextField input, String message)
    {
        try {
            int cantidadDesempleo = Integer.parseInt(input.getText());
            System.out.println("Cantidad: " + cantidadDesempleo);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: " +  message + " no es un numero");
            mostrarAlertError(message);
            return false;
        }
    }
    
    // Método para informat del envio de la encuesta
    private void InformationTab(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Poblacion - Información de envio.");
        alert.setContentText("Su encuesta ha sido enviada satisfactoriamente.");
        alert.showAndWait();
    }
    

    
    // Método para limpiar los campos una vez se haya enviado la encuesta
    @SuppressWarnings("unchecked")
    private void FieldsCleaning(){
            choiceEdad.setValue(null);
            textNacimiento.clear();
            textResides.clear();
            choicePersonasHogar.setValue(null);
            choiceServiciosHogar.setValue(null);
            choiceDispoElect.setValue(null);
            choiceNivelEducativo.setValue("Ninguno");
            choiceSigueEstudiando.setValue("Ninguno");
            checkBoxEstudiando.setSelected(false);
            radioBtnTrabajandoSi.setSelected(false);
            radioBtnTrabajandoNo.setSelected(false);
            textSector.clear();
            choiceTrabajasPorSemana.setValue(null);
            radioBtnEstudioTrabajoSi.setSelected(false);
            radioBtnEstudioTrabajoNo.setSelected(false);
            choiceSalario.setValue(null);
            radioBtnDesempleoSi.setSelected(false);
            radioBtnDesempleoNo.setSelected(false);
            textDesempleo.setText("");
    }
    
    
    //Metodo para desactivar un Texfield cuando se marque "No"
    public void desactivarText(RadioButton radioButton, TextField textField)
    {
        if(radioButton.isSelected())
        {
            textField.setDisable(true);
            textField.setText("0€");
        }
    }
    //Metodo para activar un TextField cuando se marque "Si"
    public void activarText(RadioButton radioButton, TextField textField)
    {
        if(radioButton.isSelected())
        {
            textField.setDisable(false);
            textField.setText("");
        }
    }
    //Metodo para desactivar y activar un unico checkbox
    public void desactivarChoice(CheckBox checkBox, ChoiceBox choiceBox)
    {
        if(checkBox.isSelected())
        {
            choiceBox.setDisable(false);
        }else
        {
            choiceBox.setDisable(true);
        }
    }
    //Metodo para desactivar todos los campos relacionados con el "Trabajo" cuando se marque "No"
    @SuppressWarnings("unchecked")
    public void desactivarTrabajos(RadioButton radioButton, TextField textField, ChoiceBox choiceBoxHoras, RadioButton radioButtonSi, RadioButton radioButtonNo, ChoiceBox choiceBoxSalario)
    {
        if(radioButton.isSelected())
        {
            textField.setDisable(true);
            choiceBoxHoras.setDisable(true);
            choiceBoxHoras.setValue("0 horas");
            radioButtonSi.setDisable(true);
            radioButtonNo.setDisable(true);
            choiceBoxSalario.setDisable(true);
            choiceBoxSalario.setValue("0 €");
            textField.setText("Ninguno");
            radioButtonNo.setSelected(true);
        }
    }
    //Metodo para activar todos los campos relacionados con el "Trabajo" cuando se marque "Si"
    @SuppressWarnings("unchecked")
    public void activarTrabajos(RadioButton radioButton, TextField textField, ChoiceBox choiceBoxHoras, RadioButton radioButtonSi, RadioButton radioButtonNo, ChoiceBox choiceBoxSalario)
    {
        if(radioButton.isSelected())
        {
            textField.setDisable(false);
            choiceBoxHoras.setDisable(false);
            choiceBoxHoras.setValue(null);
            radioButtonSi.setDisable(false);
            radioButtonNo.setDisable(false);
            choiceBoxSalario.setDisable(false);
            choiceBoxSalario.setValue(null);
            textField.setText("");
            radioButtonNo.setSelected(false);
        }
    }
    
    // Método para la obtención del radiobutton marcado
    private String checkRadiobutton(RadioButton radioButton){
        
        String selectedButton;  // Botón seleccionado
        
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
        // Edad
        if (choiceEdad.getValue() == null){
            choiceEdad.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choiceEdad.setStyle("-fx-border-color: none;");  
        
        // Lugar de nacimiento
        if (textNacimiento.getText().isEmpty()){
            textNacimiento.setStyle("-fx-border-color: red;");
            error=true;
        }
        else textNacimiento.setStyle("-fx-border-color: none;");
        
        // Lugar en el que reside
        if (textResides.getText().isEmpty()){
            textResides.setStyle("-fx-border-color: red;");
            error=true;
        }
        else textResides.setStyle("-fx-border-color: none;");
        
        // Personas viven en el hogar
        if (choicePersonasHogar.getValue() == null){
            choicePersonasHogar.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choicePersonasHogar.setStyle("-fx-border-color: none;");  
        
        // Servicios en el hogar
        if (choiceServiciosHogar.getValue() == null){
            choiceServiciosHogar.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choiceServiciosHogar.setStyle("-fx-border-color: none;"); 
        
        // Dispositivos electronicos
        if (choiceDispoElect.getValue() == null){
            choiceDispoElect.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choiceDispoElect.setStyle("-fx-border-color: none;"); 
        
        // Nivel educativo alcanzado
        if (choiceNivelEducativo.getValue() == null){
            choiceNivelEducativo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choiceNivelEducativo.setStyle("-fx-border-color: none;");  
        
        // Sigues estudiando
        if (choiceSigueEstudiando.getValue() == null){
            choiceSigueEstudiando.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choiceSigueEstudiando.setStyle("-fx-border-color: none;");  
        
        // Sigues trabajando
        if (!radioBtnTrabajandoSi.isSelected() && !radioBtnTrabajandoNo.isSelected()){
            radioBtnTrabajandoSi.setStyle("-fx-border-color: red;");
            radioBtnTrabajandoNo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            radioBtnTrabajandoSi.setStyle("-fx-border-color: none;");
            radioBtnTrabajandoNo.setStyle("-fx-border-color: none;");
        }
        
        // Sector
        if (textSector.getText().isEmpty()){
            textSector.setStyle("-fx-border-color: red;");
            error=true;
        }
        else textSector.setStyle("-fx-border-color: none;");
        
        // Cuantas horas trabajas por semana
        if (choiceTrabajasPorSemana.getValue() == null){
            choiceTrabajasPorSemana.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choiceTrabajasPorSemana.setStyle("-fx-border-color: none;");  
        
        // Realizo algun estudio, curso o capacitacion para realizar ese trabajo
        if (!radioBtnEstudioTrabajoSi.isSelected() && !radioBtnEstudioTrabajoNo.isSelected()){
            radioBtnEstudioTrabajoSi.setStyle("-fx-border-color: red;");
            radioBtnEstudioTrabajoNo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            radioBtnEstudioTrabajoSi.setStyle("-fx-border-color: none;");
            radioBtnEstudioTrabajoNo.setStyle("-fx-border-color: none;");
        }
        
        // Indique su salario
        if (choiceSalario.getValue() == null){
            choiceSalario.setStyle("-fx-border-color: red;");
            error=true;
        }
        else choiceSalario.setStyle("-fx-border-color: none;"); 
        
        // Recibe alguna pension o seguro de desempleo
        if (!radioBtnDesempleoSi.isSelected() && !radioBtnDesempleoNo.isSelected()){
            radioBtnDesempleoSi.setStyle("-fx-border-color: red;");
            radioBtnDesempleoNo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            radioBtnDesempleoSi.setStyle("-fx-border-color: none;");
            radioBtnDesempleoNo.setStyle("-fx-border-color: none;");
        }
        
        // Cantidad de desempleo
        if (textDesempleo.getText().isEmpty()){
            textDesempleo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else textDesempleo.setStyle("-fx-border-color: none;");

        
        return error;
    }
    
    // Método para que salga una ventana emergente al presionar enviar encuesta
    private void SendErrorTab(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Poblacion - Error");
        alert.setContentText("Todos los campos deben ser cumplimentados para el correcto envio de la encuesta.");
        alert.showAndWait();
    }
    
    // Método para confirmar que deseamos enviar los datos y se genera csv
    private void ConfirmationTab(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Poblacion - Confirmación de envio.");
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
           String fileName = " Enc_Poblacion.csv";  // Nombre del archivo .csv
            
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
                   writer.write(dateTimeFormatter.format(now) + ";" + choiceEdad.getValue()+";"+ textNacimiento.getText()+";"
                           +textResides.getText()+";"+ choicePersonasHogar.getValue()+";"+ choiceServiciosHogar.getValue()+";"+choiceDispoElect.getValue()+";"
                           +";"+choiceNivelEducativo.getValue()+";"+choiceSigueEstudiando.getValue()+";"+checkRadiobutton(radioBtnTrabajandoSi)+";"+textSector.getText()+";"
                           +choiceTrabajasPorSemana.getValue()+";"+checkRadiobutton(radioBtnEstudioTrabajoSi)+";"+choiceSalario.getValue()+";"+checkRadiobutton(radioBtnDesempleoSi)+";"
                           +textDesempleo.getText()+";\n");
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
    
    // Clase creada para controlar que solo se introduzcan letras y espacios
    class ManejadorFilter implements EventHandler<KeyEvent> {
      
        @Override
        public void handle(KeyEvent event) {
            if (Character.isDigit(event.getCharacter().charAt(0))) {
                event.consume();
            }
        }
    }
    
    // Clase creada para controlar que solo se introduzca numeros
    class NumberFilter implements EventHandler<KeyEvent> {
      
        @Override
        public void handle(KeyEvent event) {
            if (!Character.isDigit(event.getCharacter().charAt(0))) {
                event.consume();
            }
        }
    }
    
}
