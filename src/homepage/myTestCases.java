package homepage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases extends Parameters {


	@BeforeTest
	public void mySetup() {
		GeneralSetup();
		WebElement GreenButton = driver.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"));
				GreenButton.click();
	}

	@Test(priority = 1)
	public void CheckTheDefaultLanguageIsEnglish() {
		String ActualDefaultLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualDefaultLanguage, ExpectedDefaultLanguage);

	}

	@Test(priority = 2)
	public void CheckdefaultCurrency() {
		String ActualDefaultCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();;
		Assert.assertEquals(ActualDefaultCurrency, ExpectedDefaultCurrency);
	}

	@Test(priority = 3)
	public void ExpectedContactNumber() {
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	@Test(priority = 4)
	public void CheckQitafLogo() {
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		WebElement theLogo = theFooter.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		boolean ActualQitafLogo = theLogo.isDisplayed();
		Assert.assertEquals(ActualQitafLogo, ExpectedQitafLogo);
	}

	@Test(priority = 5)
	public void Checkhotelisselected() {
		String ActualResaultForCheckhotel = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
		Assert.assertEquals(ActualResaultForCheckhotel, ExpectedResaultForCheckhotel);
	}

	@Test(priority = 6)
	public void CheckDepatureDate() {

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
		RandomlyConvertTheLanguage();
	}

	@Test(priority = 8)
	public void FillHotelTab() {

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
		WebElement Selector = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select select = new Select(Selector);
		select.selectByIndex(randomselect);
		WebElement searchbutton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchbutton.click();
	}

	@Test(priority = 10)
	public void CheckThePageFullyLoaded() throws InterruptedException {
		Thread.sleep(10000);
		String resault = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
		boolean finishedactual = resault.contains("وجدنا") || resault.contains("found");
		Assert.assertEquals(finishedactual, finishedexpected);

	}
	@Test(priority = 11)
	public void SortItem() {
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
