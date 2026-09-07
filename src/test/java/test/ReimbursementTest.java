package test;

import java.awt.AWTException;
import java.io.FileNotFoundException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTestUpdated;
import pages.Reimbursement;

public class ReimbursementTest extends BaseTestUpdated {

	Reimbursement rb;

	@BeforeClass(alwaysRun = true)
	public void init() throws Exception {
		try {
			rb = new Reimbursement(driver);
		} catch (FileNotFoundException | AWTException e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "regression" })
	public void Reimbursement_ExcelImport() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		rb.reimbursement_ExcelImport();

	}

	@Test(groups = { "regression" })
	public void Delete_Reimbursement() throws InterruptedException {
		rb.Delete_Reimbursement();
	}

}
