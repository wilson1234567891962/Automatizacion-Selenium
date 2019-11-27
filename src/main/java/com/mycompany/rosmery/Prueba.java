/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rosmery;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Prueba {

    static Logger logger = Logger.getLogger(Prueba.class);
    public static WebDriver webDriver;
    public static WebElement webElement;
    public static boolean state;

    public static void main(String[] args) throws InterruptedException {
        startConfiguration();
        Thread.sleep(5000);
        runJavaScript("window.scrollBy(0,300)");
        switchFrame(0);
        webElement = webDriver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[6]/td[1]"));
        webElement.click();
        validatePopUp("Los campos del formulario no se han diligenciado");
        if (state) {
            System.out.println("Este test paso");
        } else {
            System.out.println("Este test fallo");
        }
        webDriver.close();
    }

    /**
     * Metodo para cerrar, aceptar y leer el texto de un pop up
     * @param comparator 
     */
    private static void validatePopUp(String comparator) {
        Alert alert = webDriver.switchTo().alert();
        String message = alert.getText();
        state= message.contains(comparator);
        alert.accept();
    }

    /**
     * Metodo para ejecutar javascript en la pagina web
     * @param command 
     */
    private static void runJavaScript(String command) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript(command);
    }

    /**
     * Metodo para navegar entre frames de la pagina web
     * @param position
     */
    private static void switchFrame(int position) {
        webDriver.switchTo().defaultContent(); 
        webDriver.switchTo().frame(position);
    }

    /**
     * Metodo para setear la configuracion de selenium
     */
    private static void startConfiguration() {
        String name= System.getProperty("os.name");
        if(name.toLowerCase().trim().startsWith("mac")){
             System.setProperty("webdriver.chrome.driver", "chromedriver");
        }else{
             System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(""); // Con este metodo abrimos la url de la pagina
    }
}
