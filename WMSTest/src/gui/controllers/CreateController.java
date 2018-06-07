package gui.controllers;

import JavaClasses.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class CreateController {

    @FXML
    public TextField rackTypeTexField;

    @FXML
    public TextField spaceRackType;

    @FXML
    public TextField heightRackType;

    @FXML
    public TextField numShelvesRackType;

    @FXML
    public TextField lengthRackType;

    @FXML
    public TextField widthRackType;

    @FXML
    public Button addRackTypeButton;

    @FXML
    public Button addRackButton;

    @FXML
    public ListView<Sector> sectorListView;

    @FXML
    public ListView<RackType> rackTypeListView;
    @FXML
    public ListView<Rack> rackListView;
    private ObservableList<RackType> rackTypeList = FXCollections.observableArrayList();
    private ObservableList<Sector> sectorList = FXCollections.observableArrayList();
    private MainController mainController;
    private ObservableList<Rack> rackList = FXCollections.observableArrayList();

    @FXML
    private TextField productName;

    @FXML
    private TextField productGroup;

    @FXML
    private TextField productMaker;

    @FXML
    private TextField widthProduct;

    @FXML
    private TextField lengthProduct;

    @FXML
    private TextField heightProduct;

    @FXML
    private TextField weightProduct;

    @FXML
    private TextField groupName;

    @FXML
    private TextField contractorName;

    @FXML
    private TextField nip;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField street;

    @FXML
    private TextField numOfBuilding;

    @FXML
    private TextField postcode;

    @FXML
    private TextField city;

    @FXML
    private CheckBox isProvider;

    @FXML
    private CheckBox isClient;

    private Management management;


    public void initialize() {
        management = Management.getInstance();
        isProvider.setSelected(true);
        isClient.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue)
                isProvider.setSelected(false);
            else
                isProvider.setSelected(true);
        }));

        isProvider.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                isClient.setSelected(false);
            else
                isClient.setSelected(true);
        });

        ListProperty<Sector> listProperty = new SimpleListProperty<>();
        listProperty.set(sectorList);
        sectorListView.itemsProperty().bindBidirectional(listProperty);
        sectorList.addAll(management.getSectors());

        ListProperty<Rack> racks = new SimpleListProperty<>();
        racks.set(rackList);
        rackListView.itemsProperty().bindBidirectional(racks);

        ListProperty<RackType> rackTypes = new SimpleListProperty<>();
        rackTypes.set(rackTypeList);
        rackTypeListView.itemsProperty().bindBidirectional(rackTypes);
        rackTypeList.addAll(management.getRackType());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void createProduct() {
        Product product = new Product();
        product.setName(productName.getText());
        product.setGroup(productGroup.getText());
        product.setMaker(productMaker.getText());
        product.setWidth(Integer.parseInt(widthProduct.getText()));
        product.setHeight(Integer.parseInt(heightProduct.getText()));
        product.setLength(Integer.parseInt(lengthProduct.getText()));
        product.setWeight(Integer.parseInt(weightProduct.getText()));
        management.addProduct(product);
        productName.setText("");
        productGroup.setText("");
        productMaker.setText("");
        widthProduct.setText("");
        heightProduct.setText("");
        lengthProduct.setText("");
        weightProduct.setText("");

    }

    public void createGroup() {
        ProductGroup productGroup = new ProductGroup();
        productGroup.setName(groupName.getText());
        management.addGroup(productGroup);
        groupName.setText("");
    }

    public void createContractor() {
        Contractor contractor;
        if (isClient.isSelected())
            contractor = new Client();
        else
            contractor = new Provider();

        contractor.setName(contractorName.getText());
        contractor.setNip(nip.getText());
        contractor.setPhone(phone.getText());
        contractor.setEmail(email.getText());
        contractor.setStreet(street.getText());
        contractor.setBuildingNumber(numOfBuilding.getText());
        contractor.setPostCode(postcode.getText());
        contractor.setCity(city.getText());
        management.addContractor(contractor);

        contractorName.setText("");
        nip.setText("");
        phone.setText("");
        email.setText("");
        street.setText("");
        numOfBuilding.setText("");
        postcode.setText("");
        city.setText("");
    }

    public void back() {
        mainController.loadMenuScreen();
    }

    public void addSector() {
        sectorList.clear();
        management.addSector();
        sectorList.addAll(management.getSectors());
    }

    public void addRack() {
        management.addRack(sectorListView.getSelectionModel().getSelectedItem().toString(), rackTypeTexField.getText());
        showRacks();
    }

    public void addRackType() {

        RackType rackType = new RackType();
        rackType.setWidth(Integer.parseInt(widthRackType.getText()));
        rackType.setHeight(Integer.parseInt(heightRackType.getText()));
        rackType.setLength(Integer.parseInt(lengthRackType.getText()));
        rackType.setShelfNumber(Integer.parseInt(numShelvesRackType.getText()));
        rackType.setSpace(Integer.parseInt(spaceRackType.getText()));

        management.addRackType(rackType);
        rackTypeList.clear();
        rackTypeList.addAll(management.getRackType());
    }

    public void setRackType() {
        rackTypeTexField.setText(String.valueOf(rackTypeListView.getSelectionModel().getSelectedItem().getId()));
    }

    public void showRacks() {
        rackList.clear();
        rackList.addAll(management.getRack(sectorListView.getSelectionModel().getSelectedItem()));
    }
}
