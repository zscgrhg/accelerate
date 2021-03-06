package co.pishfa.accelerate.initializer.api;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;

/**
 * Builds an object graph from an xml file. The main purpose is to initialize the database from an xml file. The
 * initializer can be created once and then be used for reading multiple xml files (the anchors are shared). This class
 * is not thread-safe.
 * 
 * @author Taha Ghasemi
 * 
 */
public interface Initializer {

	// Special elements or attributes
	static final String ELEMENT_LOAD = "load";
	static final String ELEMENT_INCLUDE = "include";
	static final String ELEMENT_VARS = "variables";

	static final String ATTR_IN_PARENT = "_in-parent_";
	static final String ATTR_ACTION = "_action_";
	static final String ATTR_ANCHOR = "_anchor_";
	// TODO is not supported yet
	static final String ATTR_CHILD_ANCHOR = "_child-anchor_";

	// context variables
	static final String CONTEXT_ANCHORS = "anchors";
	static final String CONTEXT_PARENTS = "parents";
	static final String CONTEXT_THIS = "this";
	static final String CONTEXT_ENTITY = "entity";

	// constants that can be used in defining the key property
	static final String PROPERTY_ALL = "*";
	static final String PROPERTY_EMPTY = "-";

	/**
	 * Reads data from specified resource.
	 * 
	 * @return a map from first level element names to the list of defined entities with that name
	 */
	Map<String, List<Object>> read(String resourceName) throws Exception;

	/**
	 * Reads data from the given inputStream. The encoding is detected from xml declaration. Note if autoClose is false,
	 * you are responsible for closing this reader.
	 * 
	 * @return a map from first level element names to the list of defined entities with that name
	 */
	Map<String, List<Object>> read(InputStream input, boolean autoClose) throws Exception;

	/**
	 * Reads data from the given xml element.
	 * 
	 * @return a map from first level element names to the list of defined entities with that name
	 */
	Map<String, List<Object>> read(Element root) throws Exception;

	/**
	 * Reads data from a set of annotations defined in the provided class.
	 * 
	 * @return a map from first level data classes to the list of defined entities for that data class
	 */
	Map<Class<?>, List<Object>> read(Class<?> data);

	/**
	 * 
	 * @return all anchors by their name
	 */
	Map<String, Object> getAnchores();

	/**
	 * Puts the given entity with specific anchor name into the anchors. If the anchorName starts with ":", the alias of
	 * entity class will be appended at the beginning of the anchor name
	 * 
	 * @return the previous object with the same anchorName, if any.
	 */
	Object putObject(String anchorName, Object entity);

	/**
	 * @param anchorName
	 *            the entity anchor name.
	 * @return the entity with provided anchorName. Null if no such entity exists.
	 */
	Object getObject(String anchorName);

	/**
	 * @param anchorName
	 *            the entity anchor name. Can be relative name such as 'name' which resolves based on the provided
	 *            entityClass.
	 * @return the entity with provided anchorName. Null if no such entity exists.
	 */
	<T> T getObject(String anchorName, Class<T> entityClass);

	/**
	 * @return the entity which corresponds to the given data class. Null if no such entity exists.
	 */
	Object getObject(Class<?> dataClass);

	/**
	 * @return the entity which corresponds to the given data class. Null if no such entity exists.
	 */
	<T> T getObject(Class<?> dataClass, Class<T> entityClass);

}