package controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Model.Account;
import Model.LoggedAccount;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import service.AccountService;

@Controller
public class LogInController {
	
	@Autowired
	private AccountService accountService;
	

	
	
	@Autowired
	private ViewState viewState;
	
    @FXML
    private JFXButton logInButton;

    @FXML
    private JFXTextField logInTextField;

    @FXML
    private JFXTextField passwordTextField;
    
    @FXML
    public void initalize()
    {
    	
    	logInButton.addEventHandler(KeyEvent.KEY_PRESSED, (Event event)-> logInButtonClicked());
    }

    @FXML
    public void logInButtonClicked() {
    	
    	if(!logInTextField.equals(null) && !passwordTextField.equals(null))
    	{
    		Account account = accountService.searchForAccountByLoginAndPassword(logInTextField.getText(), passwordTextField.getText());
    		if(account!=null)
    		{
    			LoggedAccount.setAccount(account);
    			viewState.displayMainView();
    		}
    	}
    	

    }

}
