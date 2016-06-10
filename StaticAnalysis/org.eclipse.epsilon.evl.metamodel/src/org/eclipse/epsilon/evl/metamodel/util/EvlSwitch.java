/**
 */
package org.eclipse.epsilon.evl.metamodel.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.metamodel.EolLibraryModule;

import org.eclipse.epsilon.evl.metamodel.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.epsilon.evl.metamodel.EvlPackage
 * @generated
 */
public class EvlSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EvlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvlSwitch() {
		if (modelPackage == null) {
			modelPackage = EvlPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case EvlPackage.EVL_ELEMENT: {
				EvlElement evlElement = (EvlElement)theEObject;
				T result = caseEvlElement(evlElement);
				if (result == null) result = caseEolElement(evlElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EvlPackage.EVL_PROGRAM: {
				EvlProgram evlProgram = (EvlProgram)theEObject;
				T result = caseEvlProgram(evlProgram);
				if (result == null) result = caseEolLibraryModule(evlProgram);
				if (result == null) result = caseEolElement(evlProgram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EvlPackage.GUARDED_ELEMENT: {
				GuardedElement guardedElement = (GuardedElement)theEObject;
				T result = caseGuardedElement(guardedElement);
				if (result == null) result = caseEvlElement(guardedElement);
				if (result == null) result = caseEolElement(guardedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EvlPackage.CONTEXT: {
				Context context = (Context)theEObject;
				T result = caseContext(context);
				if (result == null) result = caseGuardedElement(context);
				if (result == null) result = caseEvlElement(context);
				if (result == null) result = caseEolElement(context);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EvlPackage.FIX: {
				Fix fix = (Fix)theEObject;
				T result = caseFix(fix);
				if (result == null) result = caseEvlElement(fix);
				if (result == null) result = caseEolElement(fix);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EvlPackage.INVARIANT: {
				Invariant invariant = (Invariant)theEObject;
				T result = caseInvariant(invariant);
				if (result == null) result = caseGuardedElement(invariant);
				if (result == null) result = caseEvlElement(invariant);
				if (result == null) result = caseEolElement(invariant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EvlPackage.CONSTRAINT: {
				Constraint constraint = (Constraint)theEObject;
				T result = caseConstraint(constraint);
				if (result == null) result = caseInvariant(constraint);
				if (result == null) result = caseGuardedElement(constraint);
				if (result == null) result = caseEvlElement(constraint);
				if (result == null) result = caseEolElement(constraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EvlPackage.CRITIQUE: {
				Critique critique = (Critique)theEObject;
				T result = caseCritique(critique);
				if (result == null) result = caseInvariant(critique);
				if (result == null) result = caseGuardedElement(critique);
				if (result == null) result = caseEvlElement(critique);
				if (result == null) result = caseEolElement(critique);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EvlPackage.NAMED_BLOCK: {
				NamedBlock namedBlock = (NamedBlock)theEObject;
				T result = caseNamedBlock(namedBlock);
				if (result == null) result = caseEvlElement(namedBlock);
				if (result == null) result = caseEolElement(namedBlock);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EvlPackage.PRE_BLOCK: {
				PreBlock preBlock = (PreBlock)theEObject;
				T result = casePreBlock(preBlock);
				if (result == null) result = caseNamedBlock(preBlock);
				if (result == null) result = caseEvlElement(preBlock);
				if (result == null) result = caseEolElement(preBlock);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EvlPackage.POST_BLOCK: {
				PostBlock postBlock = (PostBlock)theEObject;
				T result = casePostBlock(postBlock);
				if (result == null) result = caseNamedBlock(postBlock);
				if (result == null) result = caseEvlElement(postBlock);
				if (result == null) result = caseEolElement(postBlock);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvlElement(EvlElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Program</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Program</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvlProgram(EvlProgram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Guarded Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Guarded Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGuardedElement(GuardedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContext(Context object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fix</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fix</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFix(Fix object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invariant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invariant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvariant(Invariant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraint(Constraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Critique</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Critique</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCritique(Critique object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Block</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Block</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedBlock(NamedBlock object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pre Block</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pre Block</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePreBlock(PreBlock object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Post Block</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Post Block</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePostBlock(PostBlock object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEolElement(EolElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Library Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Library Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEolLibraryModule(EolLibraryModule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //EvlSwitch
