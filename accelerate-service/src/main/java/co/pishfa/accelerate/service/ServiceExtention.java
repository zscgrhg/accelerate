/**
 * 
 */
package co.pishfa.accelerate.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.jws.WebService;
import javax.ws.rs.Path;

/**
 * @author Taha Ghasemi
 * 
 */
public class ServiceExtention implements Extension {

	private static List<Class<?>> webServices = new ArrayList<>();
	private static List<Class<?>> resources = new ArrayList<>();

	<T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {
		Class<T> javaClass = pat.getAnnotatedType().getJavaClass();
		if (javaClass.isAnnotationPresent(WebService.class)) {
			webServices.add(javaClass);
		}
		if (javaClass.isAnnotationPresent(Path.class)) {
			resources.add(javaClass);
		}
	}

	/**
	 * @return the annotatedEntities
	 */
	public static List<Class<?>> getWebServices() {
		return webServices;
	}

	/**
	 * @return the resources
	 */
	public static List<Class<?>> getResources() {
		return resources;
	}

}
