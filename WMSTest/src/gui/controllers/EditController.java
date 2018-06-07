package gui.controllers;


import JavaClasses.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;


public class EditController {

    @FXML
    public Label label1;

    @FXML
    public Label label2;

    @FXML
    public Label label3;

    @FXML
    public Label label4;

    @FXML
    public Label label5;
    public Label label00;
    public Label label01;
    public Label label02;
    public Label label03;
    public Label label04;
    public TextField textField1;
    public TextField textField2;
    public TextField textField3;
    public TextField textField4;
    public TextField textField5;
    public Button editButton;

    private Management management = Management.getInstance();

    @FXML
    private ListView<Object> editListView;

    private ObservableList<Object> editableObjects;

    private MainController mainController;

    @FXML
    private ToggleGroup toggleEdit;

    @FXML
    private ToggleButton productToggle;

    @FXML
    private ToggleButton productCardToggle;

    @FXML
    private ToggleButton contractorToggle;

    @FXML
    private TextField searchTextField;

    @FXML
    private Label editableObject;
    private Node[] nodes;

    @FXML
    public void openProductList() {
    }

    @FXML
    public void openProductCardList() {
    }

    @FXML
    public void openContractorList() {
    }

    @FXML
    public void cancel() {
        mainController.loadMenuScreen();
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setOnAction() {
    }


    public void initialize() {

        nodes = new Node[]{label2, label3, label4, label5, textField2, textField3, textField4, textField5};
        ListProperty<Object> objects = new SimpleListProperty<>();
        editableObjects = FXCollections.observableArrayList();
        objects.set(editableObjects);
        editListView.itemsProperty().bindBidirectional(objects);
        productToggle.setSelected(true);
        addAllToList();

        searchTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            editableObjects.clear();
            addAllToList();
        }));

        toggleEdit.selectedToggleProperty().addListener(((observable, oldValue, newValue) -> {
            addAllToList();
            clearLabel();
        }));

    }

    private void clearLabel() {
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
    }

    public void showMore() {
        try {
            Object object = editListView.getSelectionModel().getSelectedItem();
            if (object instanceof Product) {
                Product product = (Product) object;
                label1.setText(product.getId());
                label2.setText(product.getName());
                label3.setText(product.getGroup());
                label4.setText(product.getMaker());
                label5.setText(product.getWidth() + "x" + product.getHeight() + "x" +
                        product.getLength() + ", weight: " + product.getWeight());
            } else if (object instanceof Contractor) {
                Contractor contractor = (Contractor) object;
                label2.setText(contractor.getName());
                label1.setText(contractor.getNip());
                label3.setText(contractor.getPhone());
                label4.setText(contractor.getEmail());
                label5.setText(contractor.getStreet() + " st. " + contractor.getBuildingNumber() +
                        " " + contractor.getPostCode() + " " + contractor.getCity());
            } else {
                ProductCard productCard = (ProductCard) object;
                label1.setText(String.valueOf(productCard.getId()));
                label2.setText(productCard.getName());
                label3.setText(productCard.getPlaceId());
                label4.setText("");
                label5.setText("");
            }
        } catch (NullPointerException e) {
        }
    }

    private void addAllToList() {
        clearLabel();
        editableObjects.clear();
        if (productToggle.isSelected()) {

            editableObjects.addAll(management.getProduct(searchTextField.getText()));
            label00.setText("ID");
            label01.setText("Name");
            label02.setText("Group");
            label03.setText("Maker");
            label04.setText("Parameters");
        } else if (contractorToggle.isSelected()) {

            editableObjects.addAll(management.getClient(searchTextField.getText()));
            editableObjects.addAll(management.getProvider(searchTextField.getText()));
            label01.setText("Name");
            label00.setText("NIP");
            label02.setText("Phone");
            label03.setText("E-mail");
            label04.setText("Address");
        } else if (productCardToggle.isSelected()) {

            editableObjects.addAll(management.getProductCard(searchTextField.getText()));
            label00.setText("ID");
            label01.setText("Name");
            label02.setText("Place ID");
            label03.setText("");
            label04.setText("");
        }
    }

    public void editMode() {
        if (editButton.getText().equals("Edit")) {
            editButton.setText("Finish");
            for (int i = 0; i < nodes.length / 2; i++)
                ((TextField) nodes[i + 4]).setText(((Label) nodes[i]).getText());
        } else {
            editButton.setText("Edit");
            Object object = editListView.getSelectionModel().getSelectedItem();
            if (object instanceof Product) {
                management.update(new Product(textField2.getText(), ((Product) object).getId(),
                        textField4.getText(), ((Product) object).getWidth(), ((Product) object).getHeight(),
                        ((Product) object).getLength(), ((Product) object).getWeight(), textField3.getText()));
                addAllToList();
            } else if (object instanceof ProductCard) {
                management.update(new ProductCard(((ProductCard) object).getId(), ((ProductCard) object).getProductId(), textField3.getText(), textField2.getText()));
                addAllToList();
            } else if (object instanceof Provider) {
                Provider provider = new Provider();
                provider.setNip(label1.getText());
                provider.setName(textField2.getText());
                provider.setPhone(textField3.getText());
                provider.setEmail(textField4.getText());
                provider.setStreet(((Provider) object).getStreet());
                provider.setPostCode(((Provider) object).getPostCode());
                provider.setBuildingNumber(((Provider) object).getBuildingNumber());
                provider.setCity(((Provider) object).getCity());
                management.update(provider);
                addAllToList();
            } else if (object instanceof Client) {
                management.update(new Client(label2.getText(), ((Client) object).getNip(), label3.getText(),
                        ((Client) object).getStreet(), ((Client) object).getBuildingNumber(),
                        ((Client) object).getPostCode(), ((Client) object).getCity(), ((Client) object).getEmail()));
                addAllToList();
            }
        }
        setVisible();

    }

    private void setVisible() {
        for (Node node : nodes)
            node.setVisible(!node.isVisible());
        if (label4.getText().isEmpty()) {
            textField4.setVisible(false);
            textField5.setVisible(false);
        }
    }

    public void remove() {
        Object object = editListView.getSelectionModel().getSelectedItem();
        if (object instanceof Product)
            management.remove((Product) object);
        else if (object instanceof ProductCard)
            management.remove((ProductCard) object);
        else if (object instanceof Provider)
            management.remove((Provider) object);
        else if (object instanceof Client)
            management.remove((Client) object);
        editableObjects.clear();
        addAllToList();
    }
}
