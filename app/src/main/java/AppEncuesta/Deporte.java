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
public class Deporte extends BorderPane {

 public RadioButton BottomPracticasSI = new RadioButton();
 public RadioButton BottomPracticasNo = new RadioButton();
 public TextField TextCual = new TextField();
 public ChoiceBox BoxMarcas = new ChoiceBox();
 public TextField TextCualMarca = new TextField();
 public RadioButton BottomOtrasMarcasSI = new RadioButton(); 
 public RadioButton BottomOtrasMarcasNo = new RadioButton();
 public Slider ProductosComprados = new Slider(); 
 public ChoiceBox BoxPopu = new ChoiceBox();
 public ChoiceBox BoxCambioZapatos = new ChoiceBox();
 public ChoiceBox BoxCalidad = new ChoiceBox();
 public Button sendButton = new Button();
 
 @SuppressWarnings("unchecked")
    public Deporte() {
        super();
        this.setId("deporte");
         // Grid y parametros declaración y configuración de espacio
        GridPane grid = new GridPane();
        grid.setHgap(13);
        grid.setVgap(22);
        grid.setPadding(new Insets(25, 25, 25, 25));
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(2);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(25);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(6);
        ColumnConstraints c4 = new ColumnConstraints();
        c4.setPercentWidth(35);
        grid.getColumnConstraints().addAll(c1, c2, c3,c4);
        grid.setAlignment(Pos.CENTER);
        this.setCenter(grid);
        
                
        HBox hBox = new HBox();   
        hBox.setPadding(new Insets(30, 0, 5, 240));
        hBox.setAlignment(Pos.TOP_LEFT);
        this.setTop(hBox);
        
       //Imagen del titulo
        Image image = new Image("img/deportes.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
        
        //Titulo
        Label encuestaDeporte;
        encuestaDeporte = new Label("Encuesta de Deporte");
        encuestaDeporte.setId("deporteTitle");
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.getChildren().add(imageView);
        hBox.getChildren().add(encuestaDeporte);
        hBox.setAlignment(Pos.CENTER);    // Alineación arriba a la izquierda
        hBox.setPadding(new Insets(40, 0, 0, 0));
        hBox.setSpacing(45);
        this.setTop(hBox);
        
        // Practicas deporte?

        Label Practicas = new Label("¿Practicas deporte?");
        grid.add(Practicas, 1, 1);
        ToggleGroup PracticasRadioBoton = new ToggleGroup();
        BottomPracticasSI = new RadioButton("Si");
        BottomPracticasNo = new RadioButton("No");
        BottomPracticasSI.setToggleGroup(PracticasRadioBoton);
        BottomPracticasNo.setToggleGroup(PracticasRadioBoton);
        grid.add(BottomPracticasSI, 2, 1);
        grid.add(BottomPracticasNo, 3, 1);
        
        //en caso afirmativo cual
        Label SiPracticas = new Label("¿Cuál?");
        grid.add(SiPracticas,1,2);
        TextCual = new TextField();
        grid.add(TextCual,3,2);
        TextCual.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        BottomPracticasSI.setOnAction(event ->EnableTextField(BottomPracticasSI,TextCual));
        BottomPracticasNo.setOnAction(event ->DisableTextField(BottomPracticasNo,TextCual));
        
        //Marca de ropa favorita
        Label MarcaRopa = new Label("Marca de ropa deportiva favorita: ");
        grid.add(MarcaRopa,1,3);
        BoxMarcas = new ChoiceBox();
        BoxMarcas.getItems().addAll("...","Adidas","Under Armour","Reebok","Puma","Asics");
        BoxMarcas.setValue("...");
        grid.setAlignment(Pos.CENTER);
        grid.add(BoxMarcas, 3, 3);
        
        //Usas otras marcas?
        Label OtrasMarcas = new Label("¿Usas otras marcas de ropa?");
        grid.add(OtrasMarcas,1,4);
        ToggleGroup MarcasGrupo = new ToggleGroup();
        BottomOtrasMarcasSI = new RadioButton("Si");
        BottomOtrasMarcasNo = new RadioButton("No");
        BottomOtrasMarcasSI.setToggleGroup(MarcasGrupo);
        BottomOtrasMarcasNo.setToggleGroup(MarcasGrupo);
        grid.add(BottomOtrasMarcasSI, 2, 4);
        grid.add(BottomOtrasMarcasNo, 3, 4);
        
        //Cuales otras
        Label CualesOtras = new Label("¿Cuáles?");
        grid.add(CualesOtras,1,5);
        TextCualMarca = new TextField();
        grid.add(TextCualMarca,3,5);
        TextCualMarca.addEventFilter(KeyEvent.KEY_TYPED, new ManejadorFilter());
        BottomOtrasMarcasSI.setOnAction(event ->EnableTextField(BottomOtrasMarcasSI,TextCualMarca));
        BottomOtrasMarcasNo.setOnAction(event ->DisableTextField(BottomOtrasMarcasNo,TextCualMarca));
        
        //Cuantos productos compras por año
        Label CantidaComprados = new Label("¿Cuantos Productos deportivos compras al año?");
        grid.add(CantidaComprados, 1, 6);
        ProductosComprados = new Slider(1, 10, 0);
        ProductosComprados.setMajorTickUnit(1.0);
        ProductosComprados.setMinorTickCount(7);
        ProductosComprados.setShowTickLabels(true);
        grid.add(ProductosComprados, 3, 6);
        
        //Cada cuanto cambia sus zapatillas
        Label CambioZapas = new Label("¿Cada cuanto cambias sus zapatillas?");
        grid.add(CambioZapas,1,7);
        BoxCambioZapatos = new ChoiceBox();
        BoxCambioZapatos.getItems().addAll("...","2 meses","6 meses","Cada año","Cada 2 años","Cuando se me rompen");
        BoxCambioZapatos.setValue("...");
        grid.setAlignment(Pos.CENTER);
        grid.add(BoxCambioZapatos, 3, 7);
        
        //Calidad precio de tus marcas tipicas
        Label Calidad = new Label("¿Calidad-precio de las marcas que usas?");
        grid.add(Calidad,1,8);
        BoxCalidad = new ChoiceBox();
        BoxCalidad.getItems().addAll("...","Muy buena","Buena","Normal","Mala","Muy mala");
        BoxCalidad.setValue("...");
        grid.setAlignment(Pos.CENTER);
        grid.add(BoxCalidad, 3, 8);
        
        //Cres que las marcas son mejores por se populares
        Label Populares = new Label("¿Es mejor una marca por ser popular?");
        grid.add(Populares,1,9);
        BoxPopu = new ChoiceBox();
        BoxPopu.getItems().addAll("...","Si","No","No sé");
        BoxPopu.setValue("...");
        grid.add(BoxPopu, 3, 9);
        
        //boton de enviar
        sendButton = new Button("ENVIAR");
        grid.add(sendButton, 4, 10);
        sendButton.setOnAction(event -> ConfirmationTab());
    }

     // Método para cuando presionemos un radiobutton "No" se desactive el textfield
    private void DisableTextField(RadioButton radioButton, TextField textField){
        
        if (radioButton.isSelected()){
            textField.setText("Ninguno");
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
    
    // Método para poner en rojo los bordes de los campos que no han sido rellenos
    private boolean UnfilledFields(){
        boolean error = false;
        
        //Si Cual deporte practicas vacio
        if (TextCual.getText().isEmpty()){
            TextCual.setStyle("-fx-border-color: red;");
            error=true;
        }
        else TextCual.setStyle("-fx-border-color: none;");
        
        // Marca sin elegir
        if ("...".equals(BoxMarcas.getValue())){
            BoxMarcas.setStyle("-fx-border-color: red;");
            error=true;
        }
        else BoxMarcas.setStyle("-fx-border-color: none;");
        
        //TextCualMarca
        if (TextCualMarca.getText().isEmpty()){
            TextCualMarca.setStyle("-fx-border-color: red;");
            error=true;
        }
        else TextCualMarca.setStyle("-fx-border-color: none;");
        
        // RadioBoton de practics deporte
        if (!BottomPracticasSI.isSelected() && !BottomPracticasNo.isSelected()){
            BottomPracticasSI.setStyle("-fx-border-color: red;");
            BottomPracticasNo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            BottomPracticasSI.setStyle("-fx-border-color: none;");
            BottomPracticasNo.setStyle("-fx-border-color: none;");
        }
        
        //RedioBoton de otras marcas
        if (!BottomOtrasMarcasSI.isSelected() && !BottomOtrasMarcasNo.isSelected()){
            BottomOtrasMarcasSI.setStyle("-fx-border-color: red;");
            BottomOtrasMarcasNo.setStyle("-fx-border-color: red;");
            error=true;
        }
        else {
            BottomOtrasMarcasSI.setStyle("-fx-border-color: none;");
            BottomOtrasMarcasNo.setStyle("-fx-border-color: none;");
        }
        
        //BoxPopu
        if ("...".equals(BoxPopu.getValue())){
            BoxPopu.setStyle("-fx-border-color: red;");
            error=true;
        }
        else BoxPopu.setStyle("-fx-border-color: none;");
     
        //BoxCambioZapatos
        if ("...".equals(BoxCambioZapatos.getValue())){
            BoxCambioZapatos.setStyle("-fx-border-color: red;");
            error=true;
        }
        else BoxCambioZapatos.setStyle("-fx-border-color: none;");

        //BoxCalidad
        if ("...".equals(BoxCalidad.getValue())){
            BoxCalidad.setStyle("-fx-border-color: red;");
            error=true;
        }
        else BoxCalidad.setStyle("-fx-border-color: none;");
 
        return error;
    }
    
    // Método para confirmar que deseamos enviar los datos y se genera csv
    private void ConfirmationTab(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Deporte - Confirmación de envio.");
        alert.setContentText("¿Está seguro de que desea enviar los datos de la encuesta? Revise todos "
                + "los campos antes de proceder con el envio.");
        Optional<ButtonType> action = alert.showAndWait();
        // Si aceptamos aceptar, crearemos el file CSV
        if (action.get() == ButtonType.OK) {
            CsvFile();
        }
    }
     // Método para limpiar los campos una vez se haya enviado la encuesta
 @SuppressWarnings("unchecked")
        public void LimpiarForm(){
            BoxMarcas.setValue("...");
            TextCual.clear();
            BottomPracticasSI.setSelected(false);
            BottomPracticasNo.setSelected(false);
            ProductosComprados.setValue(0);
            BottomOtrasMarcasSI.setSelected(false);
            BottomOtrasMarcasNo.setSelected(false);
            BoxPopu.setValue("...");
            BoxCambioZapatos.setValue("...");
            BoxCalidad.setValue("...");
        }
        
    // Método para generar el fichero CSV
    private void CsvFile(){
        
        if (!UnfilledFields()){
           String fileName = " Enc_Deporte.csv";  // Nombre del archivo .csv
            
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
            adjunte al final del documento.*/
            FileOutputStream fileOS = new FileOutputStream(statText, true); // Escribe el texto
            OutputStreamWriter outputSW = new OutputStreamWriter(fileOS); // Fllujo de caracteres a bytes
               // Lo que vamos a escribir en el documento .csv
               try (Writer writer = new BufferedWriter(outputSW)) {
                   // Lo que vamos a escribir en el documento .csv
                   writer.write(dateTimeFormatter.format(now) + ";" + CheckRadiobutton(BottomPracticasSI)+ ";" + TextCual.getText() + ";" + BoxMarcas.getValue() + ";" +
                           CheckRadiobutton(BottomOtrasMarcasSI) + ";" + TextCualMarca.getText() + ";" + BoxCambioZapatos.getValue() + ";" +
                           BoxCalidad.getValue() + ";" + BoxPopu.getValue() + ";\n");
                   writer.close();// Cerramos el writer
               }
            
            InformationTab(); // Ventana de información de envio de encuesta
            LimpiarForm(); // Limpiar campos una vez enviada la encuesta
            
            } catch (IOException e) {
                System.err.println("An error has ocurred writing to the file. Error:  " + e);
            }
        }
        else{
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
    // Método para informat del envio de la encuesta
    private void InformationTab(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Deporte - Información de envio.");
        alert.setContentText("Su encuesta ha sido enviada satisfactoriamente.");
        alert.showAndWait();
    }
    
    private void SendErrorTab(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Encuesta de Deporte - Error");
        alert.setContentText("Todos los campos deben ser cumplimentados para el correcto envio de la encuesta.");
        alert.showAndWait();
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
