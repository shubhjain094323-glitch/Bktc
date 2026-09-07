package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;
import utils.RandomData;
import utils.ReadConfigFile;

public class Updatedetails {

	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	ReadConfigFile config;
	Actions actions;
	Robot robot;
	RandomData rd;

	String entityName;

	public Updatedetails(WebDriver driver) throws FileNotFoundException, AWTException {

		this.driver = driver;
		PageFactory.initElements(driver, this);

		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		config = new ReadConfigFile();
		actions = new Actions(driver);
		robot = new Robot();
		rd = new RandomData();
		entityName = rd.getpartyname();
	}

	@FindBy(xpath = "//li[@class='hamburger-menu']//div[@id='dropdown-basic']")
	WebElement Hamburgermenu;

	@FindBy(xpath = "//a[@href='/profile-management/entities']//span[@class='menu-name']")
	WebElement clickon_ProfileManagement;

	@FindBy(xpath = "//span[@class='icon-edit tabbing-color p-1']")
	WebElement Clickon_EditIcon;

	@FindBy(xpath = "//input[@name='full_name']")
	WebElement edit_accountName;

	@FindBy(xpath = "//input[@name='phone_number']")
	WebElement edit_phoneNumber;

	@FindBy(xpath = "//input[@name='gst_no']")
	WebElement edit_gstNumber;

	@FindBy(xpath = "//button[normalize-space()='Update']")
	WebElement Clickon_update;

	@FindBy(xpath = "//tbody/tr/td[1]")
	List<WebElement> listof_Entities;

	@FindBy(xpath = "//a[normalize-space()='Single Entity']")
	WebElement clickonCreate_singleEntity;

	@FindBy(xpath = "//input[@name='entity_name']")
	WebElement Enter_EntityName;

	@FindBy(xpath = "(//div[@class='Select type-select __control css-e56m7-control'])[2]")
	WebElement Clickon_StateDropdown;

	@FindBy(xpath = "//div[@class='Select type-select __option css-18we6dg-option']")
	List<WebElement> listof_states;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement Clickon_save;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement clickon_yes;

	@FindBy(xpath = "//span[normalize-space()='Entities']")
	WebElement clickon_Entities;

	@FindBy(xpath = "//button[normalize-space()='Delete Entity']")
	WebElement clickon_deleteEntity;

	@FindBy(xpath = "//button[normalize-space()='Yes']")
	WebElement clickon_YesForDelete;

	@FindBy(xpath = "//a[text()='Users']")
	WebElement clickon_user;

	@FindBy(xpath = "//a[@class='btn btn-success apply_btn_new']")
	WebElement clickon_adduser;

	@FindBy(xpath = "//input[@name='full_name']")
	WebElement addname;

	@FindBy(xpath = "//input[@name='username']")
	WebElement addusername;

	@FindBy(xpath = "//input[@name='email']")
	WebElement addemail;

	@FindBy(xpath = "//input[@type='checkbox']")
	List<WebElement> listof_entitiescheckbox;

	@FindBy(xpath = "//span[normalize-space()='Assign Permissions']")
	WebElement clickon_assignPermission;

	@FindBy(xpath = "//div[@class='pmgt-menu-list mt-3']//div")
	List<WebElement> entitiesfor_permission;

	@FindBy(xpath = "//div[@id='left-tabs-example-tabpane-entity_tab_1465']//div[@class='col-lg-4']//div[@class='Select type-select __value-container css-1hwfws3']")
	WebElement clickon_userroledropdown;

	@FindBy(xpath = "//div[@class='Select type-select __option css-18we6dg-option']")
	List<WebElement> listof_userroles;

	@FindBy(xpath = "//div[contains(@class,'Toastify__toast-body')]")
	WebElement toastmsg;

	public void updateaccountdetails() throws InterruptedException {

		Hamburgermenu.click();

		clickon_ProfileManagement.click();
		Thread.sleep(1000);

		Clickon_EditIcon.click();

		edit_accountName.clear();
		edit_accountName.sendKeys(rd.getAccountname());
		Thread.sleep(1000);

		edit_phoneNumber.clear();
		Thread.sleep(1000);
		edit_phoneNumber.sendKeys(rd.getphonenum());
		Thread.sleep(1000);

		edit_gstNumber.clear();
		edit_gstNumber.sendKeys(rd.getpartygstin());
		Thread.sleep(1000);

		Clickon_update.click();
		// Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOf(toastmsg));

	}

	public void Create_entity() throws InterruptedException {

		Hamburgermenu.click();

		clickon_ProfileManagement.click();
		// Thread.sleep(1500);

		clickonCreate_singleEntity.click();

		js.executeScript("window.scrollBy(0,-300)");

		// Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(Enter_EntityName));

		// Enter_EntityName.sendKeys(rd.getpartyname());

		Enter_EntityName.sendKeys(entityName);

		System.out.println("Entity Name is " + entityName);

		Clickon_StateDropdown.click();

		Thread.sleep(2000);

		boolean stateFound = false;

		// System.out.println(listof_states.size());

		for (WebElement states : listof_states) {

			String stateName = states.getText().trim();

			if (stateName.equalsIgnoreCase("Maharashtra")) {

				states.click();
				stateFound = true;
				break;
			}
		}

		if (!stateFound) {
			System.out.println("Invalid State");
		}

		js.executeScript("window.scrollBy(0, 700)");

		Clickon_save.click();

		clickon_yes.click();

		wait.until(ExpectedConditions.invisibilityOf(toastmsg));

	}

	public void entityupdate() throws InterruptedException {

		// Entity Update
		// Hamburgermenu.click();

		// clickon_ProfileManagement.click();
		// Thread.sleep(1500);

		js.executeScript("window.scrollBy(0,-300)");

		wait.until(ExpectedConditions.elementToBeClickable(clickon_Entities)).click();

		Clickon_StateDropdown.click();

		// clickon_Entities.click();

		for (WebElement entities : listof_Entities) {
			String entity = entities.getText().trim();
			System.out.println("Entities " + entity);
			Thread.sleep(1000);
			// js.executeScript("window.scrollBy(0, 800)");
			if (entity.equalsIgnoreCase(entityName)) {
				entities.click();
				break;
			}

		}
		Thread.sleep(2000);

		js.executeScript("window.scrollBy(0, -300)");

		boolean stateFound = false;

		// System.out.println(listof_states.size());

		for (WebElement updatedstates : listof_states) {

			String stateName = updatedstates.getText().trim();

			if (stateName.equalsIgnoreCase("Goa")) {

				updatedstates.click();
				stateFound = true;
				break;
			}
		}

		if (!stateFound) {
			System.out.println("Invalid State");
		}

		js.executeScript("window.scrollBy(0, 800)");

		WebElement updateEntity = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Update']")));
		updateEntity.click();

		driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();

		wait.until(ExpectedConditions.invisibilityOf(toastmsg));

	}

	public void delete_entity() throws InterruptedException {

		Thread.sleep(1500);

		js.executeScript("window.scrollBy(0,-500)");

		wait.until(ExpectedConditions.elementToBeClickable(clickon_Entities)).click();

		for (WebElement entities : listof_Entities) {
			String entity = entities.getText().trim();
			System.out.println(entity);
			Thread.sleep(1000);
			js.executeScript("window.scrollBy(0, 400)");
			if (entity.equalsIgnoreCase(entityName)) {
				entities.click();
				break;
			}

		}

		js.executeScript("window.scrollBy(0, 800)");

		clickon_deleteEntity.click();

		Thread.sleep(1500);

		clickon_YesForDelete.click();

		wait.until(ExpectedConditions.invisibilityOf(toastmsg));

	}

	public void Create_user() throws InterruptedException {

		Hamburgermenu.click();

		clickon_ProfileManagement.click();
		Thread.sleep(1500);

		clickon_user.click();

		clickon_adduser.click();

		addname.sendKeys(rd.getName());

		addusername.sendKeys(rd.getUserName());

		addemail.sendKeys(rd.getemail());

		Thread.sleep(2000);

		System.out.println(listof_entitiescheckbox.size());

		for (int i = 0; i < 1; i++) {
			listof_entitiescheckbox.get(i).click();
		}
		Thread.sleep(1000);

		clickon_assignPermission.click();

		Thread.sleep(1000);

		for (int i = 0; i < 1; i++) {
			entitiesfor_permission.get(i).click();
		}
		Thread.sleep(1000);

		clickon_userroledropdown.click();
		Thread.sleep(1000);

		for (WebElement roles : listof_userroles) {

			String role = roles.getText().trim();

			if (role.equalsIgnoreCase("Power User")) {
				roles.click();
				break;
			}
		}

		wait.until(ExpectedConditions.invisibilityOf(toastmsg));

	}

	public void userupdate() throws InterruptedException {

		Thread.sleep(1500);

		driver.findElement(By.xpath("//a[@id='uncontrolled-tab-example-tab-/profile-management/user']")).click();

		js.executeScript("window.scrollBy(0, 500)");

		String userselect = config.getUserselection();
		System.out.println("Selected user is =" + userselect);

		WebElement clickonuser = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//table[@class='table table-bordered']//div[text() = '" + userselect + "']")));
		System.out.println(clickonuser);
		clickonuser.click();
		Thread.sleep(1500);

		WebElement updateuser = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']")));
		updateuser.click();

		wait.until(ExpectedConditions.invisibilityOf(toastmsg));

		// System.out.println("User updated successfully");
	}

	public String getToastMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(toastmsg)).getText().trim();
	}

}
