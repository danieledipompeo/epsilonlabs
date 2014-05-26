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

import org.eclipse.epsilon.eol.metamodel.ModelElementType;

import org.eclipse.epsilon.evl.metamodel.Context;
import org.eclipse.epsilon.evl.metamodel.EvlPackage;
import org.eclipse.epsilon.evl.metamodel.PostBlock;
import org.eclipse.epsilon.evl.metamodel.PreBlock;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epsilon.evl.metamodel.impl.ContextImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.epsilon.evl.metamodel.impl.ContextImpl#getPre <em>Pre</em>}</li>
 *   <li>{@link org.eclipse.epsilon.evl.metamodel.impl.ContextImpl#getPost <em>Post</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContextImpl extends GuardedElementImpl implements Context {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ModelElementType type;

	/**
	 * The cached value of the '{@link #getPre() <em>Pre</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPre()
	 * @generated
	 * @ordered
	 */
	protected EList<PreBlock> pre;

	/**
	 * The cached value of the '{@link #getPost() <em>Post</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPost()
	 * @generated
	 * @ordered
	 */
	protected EList<PostBlock> post;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EvlPackage.Literals.CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelElementType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(ModelElementType newType, NotificationChain msgs) {
		ModelElementType oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EvlPackage.CONTEXT__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ModelElementType newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EvlPackage.CONTEXT__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EvlPackage.CONTEXT__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EvlPackage.CONTEXT__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PreBlock> getPre() {
		if (pre == null) {
			pre = new EObjectContainmentEList<PreBlock>(PreBlock.class, this, EvlPackage.CONTEXT__PRE);
		}
		return pre;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PostBlock> getPost() {
		if (post == null) {
			post = new EObjectContainmentEList<PostBlock>(PostBlock.class, this, EvlPackage.CONTEXT__POST);
		}
		return post;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EvlPackage.CONTEXT__TYPE:
				return basicSetType(null, msgs);
			case EvlPackage.CONTEXT__PRE:
				return ((InternalEList<?>)getPre()).basicRemove(otherEnd, msgs);
			case EvlPackage.CONTEXT__POST:
				return ((InternalEList<?>)getPost()).basicRemove(otherEnd, msgs);
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
			case EvlPackage.CONTEXT__TYPE:
				return getType();
			case EvlPackage.CONTEXT__PRE:
				return getPre();
			case EvlPackage.CONTEXT__POST:
				return getPost();
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
			case EvlPackage.CONTEXT__TYPE:
				setType((ModelElementType)newValue);
				return;
			case EvlPackage.CONTEXT__PRE:
				getPre().clear();
				getPre().addAll((Collection<? extends PreBlock>)newValue);
				return;
			case EvlPackage.CONTEXT__POST:
				getPost().clear();
				getPost().addAll((Collection<? extends PostBlock>)newValue);
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
			case EvlPackage.CONTEXT__TYPE:
				setType((ModelElementType)null);
				return;
			case EvlPackage.CONTEXT__PRE:
				getPre().clear();
				return;
			case EvlPackage.CONTEXT__POST:
				getPost().clear();
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
			case EvlPackage.CONTEXT__TYPE:
				return type != null;
			case EvlPackage.CONTEXT__PRE:
				return pre != null && !pre.isEmpty();
			case EvlPackage.CONTEXT__POST:
				return post != null && !post.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ContextImpl
