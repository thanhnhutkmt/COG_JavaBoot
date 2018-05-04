/**
 * 
 */
package com.caveofprogramming.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.caveofprogramming.model.entity.SiteUser;

/**
 * @author java_dev
 *
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, SiteUser> {
	@Override
	public void initialize(PasswordMatch constraintAnnotation) {
	}

	@Override
	public boolean isValid(SiteUser user, ConstraintValidatorContext context) {
		String plainPassword = user.getPlainPassword();
		String repeatPassword = user.getRepeatPassword();
		
//		if(plainPassword == null || plainPassword.length() == 0) {
//			return true;
//		}
//		
//		if (plainPassword == null || !plainPassword.equals(repeatPassword)) {
//			return false;
//		}
//		return true;
		
//		System.out.println("plainPassword is " + ((plainPassword == null) ? "null" : plainPassword) +
//		"\nrepeatPassword is " + ((repeatPassword == null) ? "null" : repeatPassword)
//		);
		return (plainPassword == null) || (repeatPassword == null) 
				|| plainPassword.equals(repeatPassword);
	}
}
