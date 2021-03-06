package co.pishfa.accelerate.initializer.api;

import java.util.Map;

import co.pishfa.accelerate.initializer.model.InitEntityMetadata;

/**
 * A Receiver for {@link Initializer} events.
 * 
 * @author Taha Ghasemi
 * 
 */
public interface InitListener {
	/**
	 * Called when a new entity is created. In this stage, inner children of this entity are not processed but
	 * attributes of this entity is set.
	 * 
	 */
	public void entityCreated(InitEntityMetadata initEntity, Object entityObj);

	/**
	 * Called when the processing of entity and its children are completed.
	 */
	public void entityFinished(InitEntityMetadata initEntity, Object entityObj);

	/**
	 * Only used in incremental mode or in the load section, to find existing entities with the given properties and
	 * values. Non of values are null.
	 * 
	 */
	public Object findEntity(InitEntityMetadata initEntity, Map<String, Object> propValues);
}