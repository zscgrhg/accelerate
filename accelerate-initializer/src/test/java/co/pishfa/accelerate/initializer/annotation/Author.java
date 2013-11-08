package co.pishfa.accelerate.initializer.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import co.pishfa.accelerate.initializer.model.InitAnnotation;

/**
 * 
 * @author Taha Ghasemi <taha.ghasemi@gmail.com>
 * 
 */
@InitAnnotation(co.pishfa.accelerate.initializer.model.Author.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

	String name() default "@type.name";

}
