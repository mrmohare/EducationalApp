/**
 * 
 */
package com.educationalApp.constants;

/**
 * @author Megharaj
 *
 */
public class EduAppConstants {

	public static final String FORGOT_PASSWORD_ENDPOINT="/forgotPassword/{email}";
	public static final String SIGNUP_ENDPOINT = "/signup";
	public static final String[] UNAUTHENTICATED_ENDPOINTS = {"/authenticate","/signup", "/forgotPassword/{email}","/login"};
}
