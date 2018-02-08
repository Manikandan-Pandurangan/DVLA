package com.dvla;


import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class dvlaLogin {
	
	@Test(dataProvider="inputData")
	public void dvla_VehicleCheck(String regnNumber, String company, String colour){
		//Driver Setup
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "/Users/Jokrish/Documents/System_Tools/Selenium/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
		System.out.println("Input    - Regn Number :"+regnNumber+ "\t Company : "+company +" \t Colour  :"+colour);
		
		// Populate Regn number and click Continue button
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/main/div/div[1]/div/section[1]/p/a")).click();
		driver.findElement(By.id("Vrm")).sendKeys(regnNumber);
		driver.findElement(By.name("Continue")).click();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		boolean exists = driver.findElements( By.xpath(".//*[@id='pr3']/div/h1") ).size() != 0;
		
		// Assert Vehicle details - Get Colour,Make,Regn number from web response and assert with input 
		if(exists)
		{
			System.out.println("Good News !!!   Vehicle Found  :");
			String color = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[3]/span[2]/strong")).getText();
			String make = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[2]/span[2]/strong")).getText();
			String number = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[1]/span[2]")).getText();
			System.out.println("Data from DVLA    :  Vehicle Number:    "+number+"\t Make  : "+make+"\t Colour  :"+color);
			Assert.assertEquals(number, regnNumber);
			Assert.assertEquals(make, company);
			Assert.assertEquals(color, colour);
			System.out.println("=====Test Passed   :  "
	                + "It's a Valid Vehicle. Both Colur and Make are correct for the given Vehicle====");	
		}
		else
			System.out.println("**** Test Failed : Invalid Vehicle data *****"); // Negative sceneario
	
	//	driver.quit();	// Close and Quit browser
	}	
		
	

	@DataProvider(name="inputData")
	public Object[][] passData()
	{
	Excel_config obj1 = new Excel_config("/Users/Jokrish/Documents/Test/DVLA_Test_Data.xlsx");//Excel sheet path
	int row = obj1.getRowCount(0);//Get total rows
	int column = 3;// Total number of columns of the Excel sheet - Regn Number , Colour, Make
	Object[][] data = new Object[row][column];
	for (int i=0;i<row;i++)
	{
		for(int j=0;j<column;j++)	
			{
				data[i][j]=obj1.getData(0, i, j);
			}
	    }
	return data;
	}
}




	
