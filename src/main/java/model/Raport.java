package model;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Paragraph;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType; 

public class Raport {
	
	public static String absolutePath="";
	public static String nameOfFile="";
	
	public Raport(String absolutePath, String nameOfFile) {
		Raport.absolutePath = absolutePath;
		Raport.nameOfFile = nameOfFile;
	}




	public static void generateRaport(String content) throws FileNotFoundException
	{
		  if(!absolutePath.equals("") && !nameOfFile.equals(""))
		  {
	      PdfWriter writer = new PdfWriter(absolutePath+"/"+nameOfFile+".pdf");           
	      PdfDocument pdf = new PdfDocument(writer);                   
	      Document document = new Document(pdf);                   
	      Paragraph paragraph1 = new Paragraph(content);                      
	      document.add(paragraph1);                  
	      document.close();
		  }
		  else
		  {
  			Alert alert = new Alert(AlertType.ERROR);
  			alert.setHeaderText("Błąd!");
  			alert.setContentText("Błąd wyboru katalogu zapisu raportu!");
  			alert.showAndWait();
		  }

	}

}
