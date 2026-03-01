package com.qa.opencart.contants;

import java.util.List;

public class AppConstants {

	
	public static final int DEFAULT_SHORT_WAIT=5;
	public static final int DEFAULT_MEDIUM_WAIT=10;
	public static final int DEFAULT_LARGE_WAIT=20;

	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_FRACTION_URL="route=account/login";

	public static final String ACC_PAGE_TITLE="My Account";
	public static final String ACC_PAGE_FRACTION_URL="route=account/login";
	
	public static final int APP_PAGE_HEADER_COUNT=4;
	public static final CharSequence REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	public static final String REGISTER_SHEET_NAME = "register";

	
	public static List<String> expected_acc_page_header_list=List.of("My Account",
			"My Orders",
			"My Affiliate Account",
			"Newsletter");

	
}
