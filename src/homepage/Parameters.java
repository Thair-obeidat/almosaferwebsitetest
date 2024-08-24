package homepage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Parameters {
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String AlmosaferURL = "https://www.almosafer.com/en?ncr=1";
	String ExpectedDefaultLanguage = "en";
	String ExpectedDefaultCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";
	boolean ExpectedQitafLogo = true;
	String ExpectedResaultForCheckhotel = "false";
	LocalDate todayDate = LocalDate.now();
	int Today = todayDate.getDayOfMonth();
	int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
	int ThedayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth();
	String[] EnglishCities = { "Dubai", "Jeddah", "Riyadh" };
	String[] ArabicCities = { "دبي", "جدة" };
	int RandomEnglishCities = rand.nextInt(EnglishCities.length);
	int RandomArabicCities = rand.nextInt(ArabicCities.length);
	boolean expectedResults = true;
	int randomselect = rand.nextInt(2);
	boolean finishedexpected = true;




public void RandomlyConvertTheLanguage () {
		String[] URLS = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int RandomIndex = rand.nextInt(URLS.length);
		driver.get(URLS[RandomIndex]);
	}
public void GeneralSetup () {
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(AlmosaferURL);


}
}
