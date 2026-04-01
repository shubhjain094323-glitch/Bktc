package pages;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

public class RandomData {

	Faker faker = new Faker();

	public DateAndTime getdate() {
		// DateAndTime date = faker.date();

		return faker.date();

	}

	public String getinvoicenum() {
		// int invoicenum = faker.number().numberBetween(10, 10000);
		return String.valueOf(faker.number().numberBetween(1, 1000));

	}

	public String gettotalamt() {

		// int totalamt = faker.number().numberBetween(100, 50000);
		return String.valueOf(faker.number().numberBetween(100, 100000));
	}

	public String getpartyname() {
		return faker.company().name() + "pvt ltd";
	}

	public String getpartygstin() {
		// return String.valueOf(faker.number().)
		return "27" + faker.regexify("[A-Z]{5}[0-9]{4}[A-Z]") + "1Z5";
	}

	public String getGSTamt() {
		return String.valueOf(faker.number().numberBetween(20, 100));
	}

	public String getAccountnum() {
		return faker.number().digits(7);
	}

	public String getAccountname() {
		return faker.name().firstName();
	}

	public String getphonenum() {
		return "9" + faker.number().digits(9);
	}

}
