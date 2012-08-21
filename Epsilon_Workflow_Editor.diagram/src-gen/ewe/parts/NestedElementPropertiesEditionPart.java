/**
 * Generated with Acceleo
 */
package ewe.parts;

// Start of user code for imports
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;



// End of user code

/**
 * 
 * 
 */
public interface NestedElementPropertiesEditionPart {

	/**
	 * @return the name
	 * 
	 */
	public String getName();

	/**
	 * Defines a new name
	 * @param newValue the new name to set
	 * 
	 */
	public void setName(String newValue);




	/**
	 * Init the attributes
	 * @param current the current value
	 * @param containgFeature the feature where to navigate if necessary
	 * @param feature the feature to manage
	 */
	public void initAttributes(ReferencesTableSettings settings);

	/**
	 * Update the attributes
	 * @param newValue the attributes to update
	 * 
	 */
	public void updateAttributes();

	/**
	 * Adds the given filter to the attributes edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToAttributes(ViewerFilter filter);

	/**
	 * Adds the given filter to the attributes edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToAttributes(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the attributes table
	 * 
	 */
	public boolean isContainedInAttributesTable(EObject element);




	/**
	 * Init the taskelements
	 * @param current the current value
	 * @param containgFeature the feature where to navigate if necessary
	 * @param feature the feature to manage
	 */
	public void initTaskelements(ReferencesTableSettings settings);

	/**
	 * Update the taskelements
	 * @param newValue the taskelements to update
	 * 
	 */
	public void updateTaskelements();

	/**
	 * Adds the given filter to the taskelements edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addFilterToTaskelements(ViewerFilter filter);

	/**
	 * Adds the given filter to the taskelements edition editor.
	 * 
	 * @param filter
	 *            a viewer filter
	 * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
	 * 
	 */
	public void addBusinessFilterToTaskelements(ViewerFilter filter);

	/**
	 * @return true if the given element is contained inside the taskelements table
	 * 
	 */
	public boolean isContainedInTaskelementsTable(EObject element);





	/**
	 * Returns the internationalized title text.
	 * 
	 * @return the internationalized title text.
	 * 
	 */
	public String getTitle();

	// Start of user code for additional methods
	
	// End of user code

}
