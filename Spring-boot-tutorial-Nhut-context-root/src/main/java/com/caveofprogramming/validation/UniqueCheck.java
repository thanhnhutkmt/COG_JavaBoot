/**
 * 
 */
package com.caveofprogramming.validation;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

/**
 * @author java_dev
 *
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy=UniqueCheckValidator.class)
@Documented
public @interface UniqueCheck {
	String message() default "Not unique";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {}; 
}
