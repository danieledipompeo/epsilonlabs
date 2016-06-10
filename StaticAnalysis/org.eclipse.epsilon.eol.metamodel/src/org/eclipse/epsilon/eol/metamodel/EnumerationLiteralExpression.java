/**
 */
package org.eclipse.epsilon.eol.metamodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration Literal Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epsilon.eol.metamodel.EnumerationLiteralExpression#getEnumeration <em>Enumeration</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.metamodel.EnumerationLiteralExpression#getLiteral <em>Literal</em>}</li>
 *   <li>{@link org.eclipse.epsilon.eol.metamodel.EnumerationLiteralExpression#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epsilon.eol.metamodel.EolPackage#getEnumerationLiteralExpression()
 * @model
 * @generated
 */
public interface EnumerationLiteralExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Enumeration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enumeration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enumeration</em>' containment reference.
	 * @see #setEnumeration(NameExpression)
	 * @see org.eclipse.epsilon.eol.metamodel.EolPackage#getEnumerationLiteralExpression_Enumeration()
	 * @model containment="true" required="true"
	 * @generated
	 */
	NameExpression getEnumeration();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.metamodel.EnumerationLiteralExpression#getEnumeration <em>Enumeration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enumeration</em>' containment reference.
	 * @see #getEnumeration()
	 * @generated
	 */
	void setEnumeration(NameExpression value);

	/**
	 * Returns the value of the '<em><b>Literal</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Literal</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Literal</em>' containment reference.
	 * @see #setLiteral(NameExpression)
	 * @see org.eclipse.epsilon.eol.metamodel.EolPackage#getEnumerationLiteralExpression_Literal()
	 * @model containment="true" required="true"
	 * @generated
	 */
	NameExpression getLiteral();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.metamodel.EnumerationLiteralExpression#getLiteral <em>Literal</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Literal</em>' containment reference.
	 * @see #getLiteral()
	 * @generated
	 */
	void setLiteral(NameExpression value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' containment reference.
	 * @see #setModel(ModelExpression)
	 * @see org.eclipse.epsilon.eol.metamodel.EolPackage#getEnumerationLiteralExpression_Model()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ModelExpression getModel();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.eol.metamodel.EnumerationLiteralExpression#getModel <em>Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' containment reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(ModelExpression value);

} // EnumerationLiteralExpression
