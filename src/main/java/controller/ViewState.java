package controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

@Component
@Configurable
public class ViewState {
	
	@Autowired
	private ApplicationContext ctx;
	
	
	private final ReadOnlyObjectWrapper<Parent> currentState = new ReadOnlyObjectWrapper<Parent>();
	private Parent logInView;
	private Parent mainScreen;
	
	public ReadOnlyObjectWrapper<Parent> getReadOnlyObjectWrapperCurrentState()
	{
		return currentState;
	}
	public Parent getCurrentState()
	{
		return currentState.get();
	}
	public void setCurrentState(Parent parent)
	{
		currentState.set(parent);
	}
	
	
	public ViewState()
	{
	}
	
	public void displayLogInView()
	{
		if(logInView == null)
		{
			try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogIn.fxml"));
			loader.setController(ctx.getBean(LogInController.class));
			logInView = loader.load();
			} catch (IOException e) {
				// fatal .. 
				e.printStackTrace();
			}
		}
		setCurrentState(logInView);
		
	}
	
	public void displayMainView() {
		if(mainScreen == null)
		{
			try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScreen.fxml"));
			loader.setController(ctx.getBean(MainController.class));
			mainScreen = loader.load();
			} catch (IOException e) {
				// fatal .. 
				e.printStackTrace();
			}
		}
		setCurrentState(mainScreen);
		
	}
	

}
