package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Model.DataFormation;
import Model.Employee;
import Model.LoggedAccount;
import Model.Raport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import service.EmployeeService;


@Controller
public class MainController {
	
		@Autowired
		private EmployeeService employeeService;
		
	 	@FXML
	    private TableView<Employee> tableView;

	    @FXML
	    private TableColumn<Employee,String> nameColumn;

	    @FXML
	    private TableColumn<Employee,String> surnameColumn;
	    
	    @FXML
	    private TableColumn<Employee, Boolean> monthColumn;
	    
	    @FXML
	    private JFXButton closeProgramButton;

	    @FXML
	    private JFXTextField searchForEmployeeTextField;

	    @FXML
	    private JFXButton messageAdministratorButton;

	    @FXML
	    private RadioButton errorCheckBox;

	    @FXML
	    private AnchorPane rightAnchorPane;

	    @FXML
	    private Label userLoginLabel;
	    
	    @FXML
	    private JFXButton directoryChooserButton;
	    
	    @FXML
	    private JFXTextField nameOfRaportTextField;

	    @FXML
	    private JFXButton printButton;

	    @FXML
	    public void closeProgramButtonPressed(ActionEvent event) {
	    	Button button = (Button) event.getSource();
	    	Stage stage = (Stage) button.getScene().getWindow();
	    	stage.close();
	    }
	    
	    
	    @FXML
	    public void directoryChooserButtonClicked(){
	    	
	    	DirectoryChooser directoryChooser = new DirectoryChooser();
	
	    	File selectedDirectory = directoryChooser.showDialog(new Stage());
	    	if(selectedDirectory!=null){
	    		String tmp  = selectedDirectory.getAbsolutePath();
	    		tmp.replace("\\", "/");
		    	Raport.absolutePath =  tmp;
	    	}

	    }

	    @FXML
	    public void messageAdministratorButtonPressed() {
	    	System.out.println("Cos robie!");

	    }


	    @FXML
	    public void searchForEmployeeTextField() {
	    	//to do
	    }



	    @FXML
	    public void printButtonPressed() {
	    	
	    	ArrayList<Employee> tmpList= new ArrayList<Employee>(tableView.getItems());
	    	String contentOfList= "";
	    	String stringBoolean="";
	    	for (Employee employee : tmpList) {
	      		if(employee.getValueFromMap()==null)
	    		{
	    			stringBoolean="Brak danych!";
	    		}
	      		else if(employee.getValueFromMap())
	    		{
	    			stringBoolean="Tak";
	    		}
	    		else if(!employee.getValueFromMap())
	    		{
	    			stringBoolean="Nie";
	    		}
	  
	    		contentOfList += employee.getName() + " , " + employee.getSurname() + " , " + DataFormation.translateMonthToString() + ":  " +
	    				stringBoolean + "\n";
	    		stringBoolean="";
			}
	    	try {
	    		if(!Raport.absolutePath.equals("") && !nameOfRaportTextField.getText().equals(""))
	    		{
	    			Raport.nameOfFile=nameOfRaportTextField.getText();
	    			Raport.generateRaport(contentOfList);
	    		}
	    		else
	    		{
	      			Alert alert = new Alert(AlertType.ERROR);
	      			alert.setHeaderText("Błąd!");
	      			alert.setContentText("Popraw dane - nazwa pliku / katalog zapisu!");
	      			alert.showAndWait();
	    		}

			} catch (FileNotFoundException e) {
				e.getMessage();
			}

	    }
	    
	    @FXML
	    public void initialize()
	    {
	    	nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	    	surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
	    	monthColumn.setCellValueFactory(new PropertyValueFactory<>("valueFromMap"));
	    	userLoginLabel.setText(LoggedAccount.getAccount().getLogin());
	    	
	    	errorCheckBox.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> {
	    		if(errorCheckBox.isSelected()){
	    			messageAdministratorButton.setDisable(false);
	    		}
	    		else {
	    			messageAdministratorButton.setDisable(true);
	    		}});


	    	monthColumn.setText(DataFormation.translateMonthToString());

	    	List<Employee> list2 =employeeService.findAllEmployees();
	    	ObservableList<Employee> list = FXCollections.observableArrayList(list2);
	    	tableView.setItems(list);
	
	    	
	    	searchForEmployeeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
				ObservableList<Employee> temporaryList = FXCollections.observableArrayList();
				String pattern = searchForEmployeeTextField.getText();
				tableView.getItems().stream().
				filter(e -> e.getSurname().startsWith(pattern)).collect(Collectors.toCollection(() -> temporaryList));
				tableView.setItems(temporaryList);
				if(searchForEmployeeTextField.getText().isEmpty())
				{
					tableView.setItems(list);
				}
				if(!searchForEmployeeTextField.getText().isEmpty())
				{
					printButton.setDisable(true);
				}
				else {
					printButton.setDisable(false);
				}
			});
	    	
	    }

}
