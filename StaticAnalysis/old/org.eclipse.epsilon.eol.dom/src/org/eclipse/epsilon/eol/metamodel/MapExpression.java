/**
 */
package org.eclipse.epsilon.eol.metamodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.metamodel.MapExpression#getKeyValues <em>Key Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epsilon.eol.metamodel.EolPackage#getMapExpression()
 * @model
 * @generated
 */
public interface MapExpression extends LiteralExpression {
	/**
	 * Returns the value of the '<em><b>Key Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.eol.metamodel.KeyValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key Values</em>' containment reference list.
	 * @see org.eclipse.epsilon.eol.metamodel.EolPackage#getMapExpression_KeyValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<KeyValue> getKeyValues();

} // MapExpression
