package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;



public class Main extends Application {

    private TextField nameTf , phoneTf , addressTf;
    private RadioButton RDsmall , RDlarge , RDmedium;
    private RadioButton RDthin , RDthick;
    private CheckBox pepperoniCB , sausageCB , linguicaCB ,olivesCB , mushroomsCB , tomatoesSB , anchoviesCB;




    @Override
    public void start(Stage primaryStage) throws Exception{
        Label textHeading = new Label("Order Your Pizza Now!");
        HBox paneTop = new HBox(20,textHeading);
        paneTop.setPadding(new Insets(20));

        Label  namelb = new Label("Name: ");
        nameTf = new TextField();
        nameTf.setPrefColumnCount(20);
        nameTf.setPromptText("Enter the customer's name here");
        nameTf.setMaxWidth(Double.MAX_VALUE);

        Label phoneLb = new Label("Phone Number: " + " ");
        phoneLb.setPrefWidth(100);
        phoneTf = new TextField();
        phoneTf.setPrefColumnCount(20);
        phoneTf.setPromptText("Enter the customer's phone number here");
        phoneTf.setMaxWidth(Double.MAX_VALUE);

        Label addressLb = new Label("Address: ");
        addressLb.setPrefWidth(100);
        addressTf = new TextField();
        addressTf.setPrefColumnCount(20);
        addressTf.setPromptText("Enter the customer's address here");

        VBox labelField=new VBox(13,namelb, phoneLb, addressLb);
        VBox textField=new VBox(5, nameTf, phoneTf, addressTf);
        HBox pane=new HBox(labelField,textField);

        Label lSize = new Label("Size ");
        RDsmall = new RadioButton("Small ");
        RDmedium = new RadioButton("Medium");
        RDlarge = new RadioButton("Large");
        RDmedium.setSelected(true);
        ToggleGroup groupSize = new ToggleGroup();
        RDsmall.setToggleGroup(groupSize);
        RDmedium.setToggleGroup(groupSize);
        RDlarge.setToggleGroup(groupSize);


        VBox paneSize = new VBox(lSize , RDsmall , RDmedium, RDlarge);
        paneSize.setSpacing(10);

        Label lCurst = new Label("Crust");
        RDthin = new RadioButton("Thin");
        RDthick = new RadioButton("Thick");
        RDthin.setSelected(true);
        ToggleGroup groupCrust = new ToggleGroup();
        RDthin.setToggleGroup(groupCrust);
        RDthick.setToggleGroup(groupCrust);

        VBox paneCrust = new VBox(lCurst ,RDthin , RDthick);
        paneCrust.setSpacing(10);

        Label lTopping  = new Label("Toppings");
        pepperoniCB = new CheckBox("Pepperoni");
        sausageCB = new CheckBox("Sausage");
        linguicaCB = new CheckBox("Linguica");
        olivesCB = new CheckBox("Olives");
        mushroomsCB = new CheckBox("Mushrooms");
        tomatoesSB = new CheckBox("Tomatoes");
        anchoviesCB = new CheckBox("Anchovies");

        FlowPane paneToppings = new FlowPane(Orientation.VERTICAL , pepperoniCB , sausageCB , linguicaCB , olivesCB , mushroomsCB , tomatoesSB , anchoviesCB);
        paneToppings.setPadding(new Insets(10,0,10,0));
        paneToppings.setHgap(20);
        paneToppings.setVgap(10);
        paneToppings.setPrefWrapLength(100);

        VBox paneTopping = new VBox(lTopping , paneToppings);

        HBox paneOrder = new HBox(50 , paneSize , paneCrust ,
                paneTopping);

        VBox paneCenter = new VBox(20, pane, paneOrder);
        paneCenter.setPadding(new Insets(0,10, 0, 10));

        Button btnOK = new Button("OK");
        btnOK.setPrefWidth(80);
        btnOK.setOnAction(e -> btnOK_Click() );
        Button btnCancel = new Button("Cancel");
        btnCancel.setPrefWidth(80);
        btnCancel.setOnAction(e ->
                primaryStage.close()
        );
        Region spacer = new Region();
        HBox paneBottom = new HBox(10, spacer, btnOK, btnCancel);
        paneBottom.setHgrow(spacer, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 10, 20, 10));
        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);
        Scene scene = new Scene(paneMain);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pizza Order");
        primaryStage.show();


    }




    private void btnOK_Click() {
        String msg = "Customer:\n\n";
        msg += "\t" + nameTf.getText() + "\n";
        msg += "\t" + addressTf.getText() + "\n";
        msg += "\t" + phoneTf.getText() + "\n\n";
        msg += "You have ordered a ";
        if (RDsmall.isSelected())
            msg += "small ";
        if (RDmedium.isSelected())
            msg += "medium ";
        if (RDlarge.isSelected())
            msg += "large ";
        if (RDthin.isSelected())
            msg += "thin crust pizza with ";
        if (RDthick.isSelected())
            msg += "thick crust pizza with ";

        String toppings = "";
        toppings = buildToppings(pepperoniCB, toppings);
        toppings = buildToppings(sausageCB, toppings);
        toppings = buildToppings(linguicaCB, toppings);
        toppings = buildToppings(olivesCB,  toppings);
        toppings = buildToppings(tomatoesSB, toppings);
        toppings = buildToppings(mushroomsCB, toppings);
        toppings = buildToppings(anchoviesCB, toppings);


        if (toppings.equals(""))
            msg += "no toppings.";
        else
            msg += "the following toppings:\n"
                    + toppings;

        Alert alert = new Alert(Alert.AlertType.INFORMATION ,"Order Details ");
        alert.setContentText(msg);
        alert.show();
    }

    public String buildToppings(CheckBox chk, String msg)
    {
        if (chk.isSelected())
        {
            if (!msg.equals(""))
            {
                msg += ", ";
            }
            msg += chk.getText();
        }
        return msg;
    }

    public void btnCancel_Click()
    {

    }




    public static void main(String[] args) {
        launch(args);
    }
}
