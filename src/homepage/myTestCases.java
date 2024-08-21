package homepage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	WebDriver driver = new ChromeDriver();
	String AlmosaferURL = "https://www.almosafer.com/en?ncr=1";
	String ExpectedDefaultLanguage = "en";
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(AlmosaferURL);
		driver.findElement(By.cssSelector(".sc-fihHvN.eYrDjb")).click();
	}

	@Test(priority = 1)
	public void CheckTheDefaultLanguageIsEnglish() {
		String ActualDefaultLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualDefaultLanguage, ExpectedDefaultLanguage);

	}

	@Test(priority = 2)
	public void CheckdefaultCurrency() {
		String ExpectedDefaultCurrency = "SAR";
		WebElement Currency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));
		String ActualDefaultCurrency = Currency.getText();
		Assert.assertEquals(ActualDefaultCurrency, ExpectedDefaultCurrency);
	}

	@Test(priority = 3)
	public void ExpectedContactNumber() {
		String ExpectedContactNumber = "+966554400000";
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	@Test(priority = 4)
	public void CheckQitafLogo() {
		boolean ExpectedQitafLogo = true;
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		WebElement theLogo = theFooter.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		boolean ActualQitafLogo = theLogo.isDisplayed();
		Assert.assertEquals(ActualQitafLogo, ExpectedQitafLogo);
	}

	@Test(priority = 5)
	public void Checkhotelisselected() {
		String ExpectedResaultForCheckhotel = "false";
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualResaultForCheckhotel = HotelTab.getAttribute("aria-selected");
		Assert.assertEquals(ActualResaultForCheckhotel, ExpectedResaultForCheckhotel);
	}

	@Test(priority = 6)
	public void CheckDepatureDate() {
		LocalDate todayDate = LocalDate.now();
		int Today = todayDate.getDayOfMonth();
		int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
		int ThedayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth();

		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));

		String ActualDepatureDate = depatureAndArrivalDates.get(0).getText();
		String ActualReturnDate = depatureAndArrivalDates.get(1).getText();

		int ActualDepatureDateAsInt = Integer.parseInt(ActualDepatureDate);
		int ActualreturnDateAsInt = Integer.parseInt(ActualReturnDate);

		Assert.assertEquals(ActualDepatureDateAsInt, Tomorrow);

		Assert.assertEquals(ActualreturnDateAsInt, ThedayAfterTomorrow);

	}

	@Test(priority = 7)
	public void RandomlyChangeTheLanguage() {
		String[] URLS = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int RandomIndex = rand.nextInt(URLS.length);

		driver.get(URLS[RandomIndex]);

	}

	@Test(priority = 8)
	public void FillHotelTab() {
		String[] EnglishCities = { "Dubai", "Jeddah", "Riyadh" };
		String[] ArabicCities = { "دبي", "جدة" };

		int RandomEnglishCities = rand.nextInt(EnglishCities.length);
		int RandomArabicCities = rand.nextInt(ArabicCities.length);

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
		WebElement SearchHotelInputField = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));

		String WebsiteURL = driver.getCurrentUrl();

		if (WebsiteURL.contains("ar")) {
			SearchHotelInputField.sendKeys(ArabicCities[RandomArabicCities]);
		} else {
			SearchHotelInputField.sendKeys(EnglishCities[RandomEnglishCities]);
		}

		WebElement ListOfLocations = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));

		WebElement firstResault = ListOfLocations.findElements(By.tagName("li")).get(1);
		firstResault.click();

	}

	@Test(priority = 9)
	public void RandomlySelectTheNumberOfVistor() {
		WebElement Selector = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));

		Select select = new Select(Selector);

		int randomselect = rand.nextInt(2);
		select.selectByIndex(randomselect);

		WebElement searchbutton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchbutton.click();
	}

	@Test(priority = 10)
	public void CheckThePageFullyLoaded() throws InterruptedException {
		boolean finishedexpected = true;
		Thread.sleep(10000);
		String resault = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
		boolean finishedactual = resault.contains("وجدنا") || resault.contains("found");
		Assert.assertEquals(finishedactual, finishedexpected);

	}
	@Test(priority = 11)
	public void SortItem() {
		boolean expectedResults = true;
		WebElement SortItemButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		SortItemButton.click();
		
		WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

		
		List<WebElement> AllPrices = PricesContainer.findElements(By.className("Price__Value"));
		

		String LowestPrice = AllPrices.get(0).getText();
		String HighestPrice = AllPrices.get(AllPrices.size() - 1).getText();

		
		System.out.println(LowestPrice);
		System.out.println(HighestPrice);

		int lowprice = Integer.parseInt(LowestPrice);
		int highprice = Integer.parseInt(HighestPrice);
		
		boolean ActualResults = lowprice < highprice ;
		
		Assert.assertEquals(ActualResults, expectedResults);
		
		
	}

}
