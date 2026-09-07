package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigFile {

	Properties properties;
	private final String path = "./Config.properties";
	
	public ReadConfigFile() throws FileNotFoundException {

		/*
		 * properties = new Properties();
		 * 
		 * // Relative path: same folder as the JAR File configFile = new
		 * File("Config.properties"); FileInputStream fis = new
		 * FileInputStream(configFile);
		 * 
		 * 
		 * try { properties.load(fis); //
		 * properties.load(getClass().getClassLoader().getResourceAsStream(
		 * "Config.properties")); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } }
		 */

		properties = new Properties();
		File configFile = new File(path); // relative path

		try (FileInputStream fis = new FileInputStream(configFile)) {
			properties.load(fis);
			// properties.load(getClass().getClassLoader().getResourceAsStream("Config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	
	public String getexecution_env() {
		String env = properties.getProperty("execution_env");
		if (env != null) {
			return env;
		} else {
			throw new RuntimeException("env is not specified in config file");

		}
	}

	public String getURL() {
		String url = properties.getProperty("url");
		if (url != null) {
			return url;
		} else {
			throw new RuntimeException("Url is not specified in config file");

		}
	}

	public String getaccountcode() {
		String accountcode = properties.getProperty("accountcode");
		if (accountcode != null) {
			return accountcode;
		} else {
			throw new RuntimeException("accountcode is not specified in config file");

		}
	}

	public String getusername() {
		String username = properties.getProperty("username");
		if (username != null) {
			return username;
		} else {
			throw new RuntimeException("username is not specified in config file");

		}
	}

	public String getpassword() {
		String password = properties.getProperty("password");
		if (password != null) {
			return password;
		} else {
			throw new RuntimeException("password is not specified in config file");

		}
	}

	public String getentityname() {
		String entityname = properties.getProperty("entityname");
		if (entityname != null) {
			return entityname;
		} else {
			throw new RuntimeException("entityname is not specified in config file");

		}
	}

	public String getentityindex() {
		String entityindex = properties.getProperty("entityindex");
		if (entityindex != null) {
			return entityindex;
		} else {
			throw new RuntimeException("entityindex is not specified in config file");

		}
	}


	public String getsnapshotdate() {
		String snapshotdate = properties.getProperty("snapshotdate");
		if (snapshotdate != null) {
			return snapshotdate;
		} else {
			throw new RuntimeException("snapshotdate is not specified in config file");

		}
	}

	public String getInvoiceNum() {
		String InvoiceNum = properties.getProperty("InvoiceNum");
		if (InvoiceNum != null) {
			return InvoiceNum;
		} else {
			throw new RuntimeException("InvoiceNum is not specified in config file");

		}
	}

	public String getTotalAmount() {
		String TotalAmount = properties.getProperty("TotalAmount");
		if (TotalAmount != null) {
			return TotalAmount;
		} else {
			throw new RuntimeException("TotalAmount is not specified in config file");

		}

	}

	public String getPartyName() {
		String PartyName = properties.getProperty("PartyName");
		if (PartyName != null) {
			return PartyName;
		} else {
			throw new RuntimeException("PartyName is not specified in config file");

		}
	}

	public String getPartyGST() {
		String PartyGST = properties.getProperty("PartyGST");
		if (PartyGST != null) {
			return PartyGST;
		} else {
			throw new RuntimeException("PartyGST is not specified in config file");

		}
	}

	public String getGSTAmt() {
		String GSTAmt = properties.getProperty("GSTAmt");
		if (GSTAmt != null) {
			return GSTAmt;
		} else {
			throw new RuntimeException("GSTAmt is not specified in config file");

		}
	}

	public String getTDSpercent() {
		String TDSpercent = properties.getProperty("TDSpercent");
		if (TDSpercent != null) {
			return TDSpercent;
		} else {
			throw new RuntimeException("TDSpercent is not specified in config file");

		}
	}

	public String getSnapshotselection() {
		String Snapshotselection = properties.getProperty("Snapshotselection");
		if (Snapshotselection != null) {
			return Snapshotselection;
		} else {
			throw new RuntimeException("Snapshotselection is not specified in config file");

		}
	}

	public String getBulkuploadfilepath() {
		String Bulkuploadfilepath = properties.getProperty("Bulkuploadfilepath");
		if (Bulkuploadfilepath != null) {
			return Bulkuploadfilepath;
		} else {
			throw new RuntimeException("Bulkuploadfilepath is not specified in config file");

		}
	}

	public String getExcelfilepath() {
		String Excelfilepath = properties.getProperty("Excelfilepath");
		if (Excelfilepath != null) {
			return Excelfilepath;
		} else {
			throw new RuntimeException("Excelfilepath is not specified in config file");

		}
	}

	public String getInventoryExcelPath() {
		String InventoryExcelPath = properties.getProperty("InventoryExcelPath");
		if (InventoryExcelPath != null) {
			return InventoryExcelPath;
		} else {
			throw new RuntimeException("InventoryExcelPath is not specified in config file");

		}
	}
	

	public String getReimbursementExcelImportPath() {
		String ReimbursementExcelPath = properties.getProperty("ReimbursementExcelImportpath");
		if (ReimbursementExcelPath != null) {
			return ReimbursementExcelPath;
		} else {
			throw new RuntimeException("ReimbursementExcelImportpath is not specified in config file");

		}
	}

	public String getaddbank() {
		String addbank = properties.getProperty("addbank");
		if (addbank != null) {
			return addbank;
		} else {
			throw new RuntimeException("addbank is not specified in config file");

		}
	}

	public String getselectbanktype() {
		String selectbanktype = properties.getProperty("selectbanktype");
		if (selectbanktype != null) {
			return selectbanktype;
		} else {
			throw new RuntimeException("selectbanktype is not specified in config file");

		}
	}

	public String getbankaccountname() {
		String bankaccountname = properties.getProperty("bankaccountname");
		if (bankaccountname != null) {
			return bankaccountname;
		} else {
			throw new RuntimeException("bankaccountname is not specified in config file");

		}
	}

	public String getbankaccountnumber() {
		String bankaccountnumber = properties.getProperty("bankaccountnumber");
		if (bankaccountnumber != null) {
			return bankaccountnumber;
		} else {
			throw new RuntimeException("bankaccountnumber is not specified in config file");

		}
	}

	public String getbankledger() {
		String bankledger = properties.getProperty("bankledger");
		if (bankledger != null) {
			return bankledger;
		} else {
			throw new RuntimeException("bankledger is not specified in config file");

		}
	}

	public String getSelectbank() {
		String Selectbank = properties.getProperty("Selectbank");
		if (Selectbank != null) {
			return Selectbank;
		} else {
			throw new RuntimeException("Selectbank is not specified in config file");

		}
	}

	public String getExportsfields() {
		String Exportsfields = properties.getProperty("Exportsfields");
		if (Exportsfields != null) {
			return Exportsfields;
		} else {
			throw new RuntimeException("Exportsfields is not specified in config file");

		}
	}

	public String getTimeperiod() {
		String Timeperiod = properties.getProperty("Timeperiod");
		if (Timeperiod != null) {
			return Timeperiod;
		} else {
			throw new RuntimeException("Timeperiod is not specified in config file");

		}
	}

	public String getAccountName() {
		String AccountName = properties.getProperty("AccountName");
		if (AccountName != null) {
			return AccountName;
		} else {
			throw new RuntimeException("AccountName is not specified in config file");

		}
	}

	public String getMobileNumber() {
		String MobileNumber = properties.getProperty("MobileNumber");
		if (MobileNumber != null) {
			return MobileNumber;
		} else {
			throw new RuntimeException("MobileNumber is not specified in config file");

		}
	}

	public String getGSTNumber() {
		String GSTNumber = properties.getProperty("GSTNumber");
		if (GSTNumber != null) {
			return GSTNumber;
		} else {
			throw new RuntimeException("GSTNumber is not specified in config file");

		}
	}

	public String getEntityselection() {
		String Entityselection = properties.getProperty("Entityselection");
		if (Entityselection != null) {
			return Entityselection;
		} else {
			throw new RuntimeException("Entityselection is not specified in config file");

		}
	}

	public String getEntitynameforupdate() {
		String Entitynameforupdate = properties.getProperty("Entitynameforupdate");
		if (Entitynameforupdate != null) {
			return Entitynameforupdate;
		} else {
			throw new RuntimeException("Entitynameforupdate is not specified in config file");

		}
	}

	public String getUserselection() {
		String Userselection = properties.getProperty("Userselection");
		if (Userselection != null) {
			return Userselection;
		} else {
			throw new RuntimeException("Userselection is not specified in config file");

		}
	}
	
	public String getselectbankforexport() {
		String bankselectionforexport = properties.getProperty("selectbankforexport");
		if (bankselectionforexport != null) {
			return bankselectionforexport;
		} else {
			throw new RuntimeException("bankselectionforexport is not specified in config file");

		}
	}


}
