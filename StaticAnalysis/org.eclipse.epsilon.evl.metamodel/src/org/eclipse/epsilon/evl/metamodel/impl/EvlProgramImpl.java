/**
 */
package org.eclipse.epsilon.evl.metamodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.epsilon.eol.metamodel.Import;
import org.eclipse.epsilon.eol.metamodel.ModelDeclarationStatement;
import org.eclipse.epsilon.eol.metamodel.NameExpression;
import org.eclipse.epsilon.eol.metamodel.OperationDefinition;

import org.eclipse.epsilon.evl.metamodel.Context;
import org.eclipse.epsilon.evl.metamodel.EvlPackage;
import org.eclipse.epsilon.evl.metamodel.EvlProgram;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Program</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epsilon.evl.metamodel.impl.EvlProgramImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.epsilon.evl.metamodel.impl.EvlProgramImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.eclipse.epsilon.evl.metamodel.impl.EvlProgramImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epsilon.evl.metamodel.impl.EvlProgramImpl#getModelDeclarations <em>Model Declarations</em>}</li>
 *   <li>{@link org.eclipse.epsilon.evl.metamodel.impl.EvlProgramImpl#getContexts <em>Contexts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EvlProgramImpl extends EvlElementImpl implements EvlProgram {
	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<Import> imports;

	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationDefinition> operations;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected NameExpression name;

	/**
	 * The cached value of the '{@link #getModelDeclarations() <em>Model Declarations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelDeclarations()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelDeclarationStatement> modelDeclarations;

	/**
	 * The cached value of the '{@link #getContexts() <em>Contexts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContexts()
	 * @generated
	 * @ordered
	 */
	protected EList<Context> contexts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EvlProgramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EvlPackage.Literals.EVL_PROGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Import> getImports() {
		if (imports == null) {
			imports = new EObjectContainmentEList<Import>(Import.class, this, EvlPackage.EVL_PROGRAM__IMPORTS);
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationDefinition> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<OperationDefinition>(OperationDefinition.class, this, EvlPackage.EVL_PROGRAM__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameExpression getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(NameExpression newName, NotificationChain msgs) {
		NameExpression oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EvlPackage.EVL_PROGRAM__NAME, oldName, newName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(NameExpression newName) {
		if (newName != name) {
			NotificationChain msgs = null;
			if (name != null)
				msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EvlPackage.EVL_PROGRAM__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EvlPackage.EVL_PROGRAM__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EvlPackage.EVL_PROGRAM__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelDeclarationStatement> getModelDeclarations() {
		if (modelDeclarations == null) {
			modelDeclarations = new EObjectContainmentEList<ModelDeclarationStatement>(ModelDeclarationStatement.class, this, EvlPackage.EVL_PROGRAM__MODEL_DECLARATIONS);
		}
		return modelDeclarations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Context> getContexts() {
		if (contexts == null) {
			contexts = new EObjectContainmentEList<Context>(Context.class, this, EvlPackage.EVL_PROGRAM__CONTEXTS);
		}
		return contexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EvlPackage.EVL_PROGRAM__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case EvlPackage.EVL_PROGRAM__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case EvlPackage.EVL_PROGRAM__NAME:
				return basicSetName(null, msgs);
			case EvlPackage.EVL_PROGRAM__MODEL_DECLARATIONS:
				return ((InternalEList<?>)getModelDeclarations()).basicRemove(otherEnd, msgs);
			case EvlPackage.EVL_PROGRAM__CONTEXTS:
				return ((InternalEList<?>)getContexts()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EvlPackage.EVL_PROGRAM__IMPORTS:
				return getImports();
			case EvlPackage.EVL_PROGRAM__OPERATIONS:
				return getOperations();
			case EvlPackage.EVL_PROGRAM__NAME:
				return getName();
			case EvlPackage.EVL_PROGRAM__MODEL_DECLARATIONS:
				return getModelDeclarations();
			case EvlPackage.EVL_PROGRAM__CONTEXTS:
				return getContexts();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EvlPackage.EVL_PROGRAM__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends Import>)newValue);
				return;
			case EvlPackage.EVL_PROGRAM__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends OperationDefinition>)newValue);
				return;
			case EvlPackage.EVL_PROGRAM__NAME:
				setName((NameExpression)newValue);
				return;
			case EvlPackage.EVL_PROGRAM__MODEL_DECLARATIONS:
				getModelDeclarations().clear();
				getModelDeclarations().addAll((Collection<? extends ModelDeclarationStatement>)newValue);
				return;
			case EvlPackage.EVL_PROGRAM__CONTEXTS:
				getContexts().clear();
				getContexts().addAll((Collection<? extends Context>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EvlPackage.EVL_PROGRAM__IMPORTS:
				getImports().clear();
				return;
			case EvlPackage.EVL_PROGRAM__OPERATIONS:
				getOperations().clear();
				return;
			case EvlPackage.EVL_PROGRAM__NAME:
				setName((NameExpression)null);
				return;
			case EvlPackage.EVL_PROGRAM__MODEL_DECLARATIONS:
				getModelDeclarations().clear();
				return;
			case EvlPackage.EVL_PROGRAM__CONTEXTS:
				getContexts().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EvlPackage.EVL_PROGRAM__IMPORTS:
				return imports != null && !imports.isEmpty();
			case EvlPackage.EVL_PROGRAM__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case EvlPackage.EVL_PROGRAM__NAME:
				return name != null;
			case EvlPackage.EVL_PROGRAM__MODEL_DECLARATIONS:
				return modelDeclarations != null && !modelDeclarations.isEmpty();
			case EvlPackage.EVL_PROGRAM__CONTEXTS:
				return contexts != null && !contexts.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EvlProgramImpl
