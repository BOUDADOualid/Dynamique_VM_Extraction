//package org.back.selenium.naviguateur;
//
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class JobNavigateur {
//	public static WebDriver driver = null;
//	static Logger logger = LoggerFactory.getLogger(JobNavigateur.class);
//
////	
////	public static Capabilities getWebDriverProfil() {
////		FirefoxProfile profile = new FirefoxProfile();
////		profile.setPreference("browser.download.dir", "/home/exploit/Bureau/downloadpath/");
////		profile.setPreference("browser.download.folderList", 2);
////		profile.setPreference("browser.download.manager.showWhenStarting", false);
////
////		profile.setPreference("browser.helperApps.neverAsk.openFile",
////				"text/csv,application/x-msexcel ,application/x-csv,application/zip,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
////
////		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
////				"text/csv,application/x-msexcel,,application/x-csv,application/zip,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
////
////		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
////		return profile;
////	}
////
////	public static WebDriver getWebDriverInstance() {
////		if (driver != null) {
////			if (driver.toString().contains("null")) {
////				System.out.print("All Browser windows are closed ");
////				driver = new FirefoxDriver(getWebDriverProfil());
////			} else {
////				return driver;
////			}
////		} else {
////			driver = new FirefoxDriver(getWebDriverProfil());
////		}
////		return driver;
////	}
//
//	public static void DownaloadDataFromGmail(String path) {
//		driver = new FirefoxDriver();
//		logger.info("démarre page http://mail.auchan.com");
//		
//		try {
//			WebElement query = null;
//			if (driver.findElement(By.id("username")).isDisplayed()) {
//				query = (WebElement) new WebDriverWait(driver, 30L)
//						.until(ExpectedConditions.elementToBeClickable(By.id("username")));
//
//				query.sendKeys(new CharSequence[] { "GFR5294002" });
//				Thread.sleep(3000L);
//				logger.info("Nom utilisateur");
//
//			}
//			if (driver.findElement(By.id("password")).isDisplayed()) {
//				query = (WebElement) new WebDriverWait(driver, 30L)
//						.until(ExpectedConditions.elementToBeClickable(By.id("password")));
//
//				query.sendKeys(new CharSequence[] { "AuchanAtos@2015" });
//				Thread.sleep(3000L);
//				logger.info("Mot de passe");
//				query.submit();
//			}
//
//			logger.info("Scssué authentification");
//
//			Thread.sleep(10000L);
//
//			driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
//			if (driver.findElement(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#label/ESOPE+ATHENA']"))
//					.isDisplayed()) {
//				query = (WebElement) new WebDriverWait(driver, 30L).until(ExpectedConditions.elementToBeClickable(
//						By.xpath("//a[@href='https://mail.google.com/mail/u/0/#label/ESOPE+ATHENA']")));
//
//				Thread.sleep(5000L);
//				query.click();
//
//			}
//			Thread.sleep(10000L);
//			driver.navigate().refresh();
//			logger.info("Reload page");
//			driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
//			query = (WebElement) new WebDriverWait(driver, 30L)
//					.until(ExpectedConditions.elementToBeClickable(By.className("bog")));
//			query.click();
//			driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//			logger.info("début de téléchargement");
//			if (driver.findElement(By.xpath(
//					"//div[@aria-label='Télécharger la pièce jointe Vue pour Export Actions Encours(Actions Groupe ESOPE-ESOPE _ Vue pour Export).CSV.zip']"))
//					.isDisplayed()) {
//				query = (WebElement) new WebDriverWait(driver, 30L).until(ExpectedConditions.elementToBeClickable(By
//						.xpath("//div[@aria-label='Télécharger la pièce jointe Vue pour Export Actions Encours(Actions Groupe ESOPE-ESOPE _ Vue pour Export).CSV.zip']")));
//
//				query.click();
//			}
//			Thread.sleep(20000L);
//			if (driver.findElement(By.xpath(
//					"//div[@aria-label='Télécharger la pièce jointe Actions reÃ§ues sur la journÃ©e(Groupe ESOPE-Vue pour export).CSV.zip']"))
//					.isDisplayed()) {
//				query = (WebElement) new WebDriverWait(driver, 30L).until(ExpectedConditions.elementToBeClickable(By
//						.xpath("//div[@aria-label='Télécharger la pièce jointe Actions reÃ§ues sur la journÃ©e(Groupe ESOPE-Vue pour export).CSV.zip']")));
//
//				query.click();
//			}
//			Thread.sleep(5000L);
//
//			logger.info("fin de téléchargement");
//
//			driver.quit();
//		} catch (Exception e) {
//			e.printStackTrace();
//
//			driver.quit();
//
//		} finally {
//			driver.quit();
//		}
//	}
//}
