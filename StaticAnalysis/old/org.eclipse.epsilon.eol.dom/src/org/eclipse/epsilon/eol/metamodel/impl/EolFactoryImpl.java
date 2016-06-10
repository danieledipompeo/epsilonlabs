/**
 */
package org.eclipse.epsilon.eol.metamodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.epsilon.eol.metamodel.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EolFactoryImpl extends EFactoryImpl implements EolFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EolFactory init() {
		try {
			EolFactory theEolFactory = (EolFactory)EPackage.Registry.INSTANCE.getEFactory(EolPackage.eNS_URI);
			if (theEolFactory != null) {
				return theEolFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EolFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EolFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case EolPackage.TEXT_REGION: return createTextRegion();
			case EolPackage.TEXT_POSITION: return createTextPosition();
			case EolPackage.PROGRAM: return createProgram();
			case EolPackage.IMPORT: return createImport();
			case EolPackage.BLOCK: return createBlock();
			case EolPackage.AND_OPERATOR_EXPRESSION: return createAndOperatorExpression();
			case EolPackage.BOOLEAN_EXPRESSION: return createBooleanExpression();
			case EolPackage.DIVIDE_OPERATOR_EXPRESSION: return createDivideOperatorExpression();
			case EolPackage.ENUMERATION_LITERAL_EXPRESSION: return createEnumerationLiteralExpression();
			case EolPackage.EQUALS_OPERATOR_EXPRESSION: return createEqualsOperatorExpression();
			case EolPackage.GREATER_THAN_OPERATOR_EXPRESSION: return createGreaterThanOperatorExpression();
			case EolPackage.GREATER_THAN_OR_EQUAL_TO_OPERATOR_EXPRESSION: return createGreaterThanOrEqualToOperatorExpression();
			case EolPackage.IMPLIES_OPERATOR_EXPRESSION: return createImpliesOperatorExpression();
			case EolPackage.INTEGER_EXPRESSION: return createIntegerExpression();
			case EolPackage.LESS_THAN_OPERATOR_EXPRESSION: return createLessThanOperatorExpression();
			case EolPackage.LESS_THAN_OR_EQUAL_TO_OPERATOR_EXPRESSION: return createLessThanOrEqualToOperatorExpression();
			case EolPackage.METHOD_CALL_EXPRESSION: return createMethodCallExpression();
			case EolPackage.MINUS_OPERATOR_EXPRESSION: return createMinusOperatorExpression();
			case EolPackage.MULTIPLY_OPERATOR_EXPRESSION: return createMultiplyOperatorExpression();
			case EolPackage.NAME_EXPRESSION: return createNameExpression();
			case EolPackage.NEGATIVE_OPERATOR_EXPRESSION: return createNegativeOperatorExpression();
			case EolPackage.NOT_EQUALS_OPERATOR_EXPRESSION: return createNotEqualsOperatorExpression();
			case EolPackage.NOT_OPERATOR_EXPRESSION: return createNotOperatorExpression();
			case EolPackage.OR_OPERATOR_EXPRESSION: return createOrOperatorExpression();
			case EolPackage.PLUS_OPERATOR_EXPRESSION: return createPlusOperatorExpression();
			case EolPackage.PROPERTY_CALL_EXPRESSION: return createPropertyCallExpression();
			case EolPackage.REAL_EXPRESSION: return createRealExpression();
			case EolPackage.STRING_EXPRESSION: return createStringExpression();
			case EolPackage.VARIABLE_DECLARATION_EXPRESSION: return createVariableDeclarationExpression();
			case EolPackage.XOR_OPERATOR_EXPRESSION: return createXorOperatorExpression();
			case EolPackage.OPERATION_DEFINITION: return createOperationDefinition();
			case EolPackage.ASSIGNMENT_STATEMENT: return createAssignmentStatement();
			case EolPackage.BREAK_STATEMENT: return createBreakStatement();
			case EolPackage.BREAK_ALL_STATEMENT: return createBreakAllStatement();
			case EolPackage.CONTINUE_STATEMENT: return createContinueStatement();
			case EolPackage.DELETE_STATEMENT: return createDeleteStatement();
			case EolPackage.FOR_STATEMENT: return createForStatement();
			case EolPackage.IF_STATEMENT: return createIfStatement();
			case EolPackage.RETURN_STATEMENT: return createReturnStatement();
			case EolPackage.SWITCH_STATEMENT: return createSwitchStatement();
			case EolPackage.WHILE_STATEMENT: return createWhileStatement();
			case EolPackage.MODEL_DECLARATION_STATEMENT: return createModelDeclarationStatement();
			case EolPackage.FOL_METHOD_CALL_EXPRESSION: return createFOLMethodCallExpression();
			case EolPackage.EXPRESSION_STATEMENT: return createExpressionStatement();
			case EolPackage.SWITCH_CASE_DEFAULT_STATEMENT: return createSwitchCaseDefaultStatement();
			case EolPackage.SWITCH_CASE_EXPRESSION_STATEMENT: return createSwitchCaseExpressionStatement();
			case EolPackage.SET_EXPRESSION: return createSetExpression();
			case EolPackage.BAG_EXPRESSION: return createBagExpression();
			case EolPackage.SEQUENCE_EXPRESSION: return createSequenceExpression();
			case EolPackage.ORDERED_SET_EXPRESSION: return createOrderedSetExpression();
			case EolPackage.MAP_EXPRESSION: return createMapExpression();
			case EolPackage.TYPE: return createType();
			case EolPackage.ANY_TYPE: return createAnyType();
			case EolPackage.COLLECTION_TYPE: return createCollectionType();
			case EolPackage.BOOLEAN_TYPE: return createBooleanType();
			case EolPackage.INTEGER_TYPE: return createIntegerType();
			case EolPackage.REAL_TYPE: return createRealType();
			case EolPackage.STRING_TYPE: return createStringType();
			case EolPackage.SET_TYPE: return createSetType();
			case EolPackage.BAG_TYPE: return createBagType();
			case EolPackage.SEQUENCE_TYPE: return createSequenceType();
			case EolPackage.ORDERED_SET_TYPE: return createOrderedSetType();
			case EolPackage.MAP_TYPE: return createMapType();
			case EolPackage.EXECUTABLE_ANNOTATION: return createExecutableAnnotation();
			case EolPackage.SIMPLE_ANNOTATION: return createSimpleAnnotation();
			case EolPackage.KEY_VALUE: return createKeyValue();
			case EolPackage.SPECIAL_NAME_EXPRESSION: return createSpecialNameExpression();
			case EolPackage.MODEL_ELEMENT_TYPE: return createModelElementType();
			case EolPackage.NATIVE_TYPE: return createNativeType();
			case EolPackage.MODEL_EXPRESSION: return createModelExpression();
			case EolPackage.ANNOTATION_BLOCK: return createAnnotationBlock();
			case EolPackage.MODEL_DECLARATION_PARAMETER: return createModelDeclarationParameter();
			case EolPackage.THROW_STATEMENT: return createThrowStatement();
			case EolPackage.ABORT_STATEMENT: return createAbortStatement();
			case EolPackage.TRANSACTION_STATEMENT: return createTransactionStatement();
			case EolPackage.FORMAL_PARAMETER_EXPRESSION: return createFormalParameterExpression();
			case EolPackage.EXPR_LIST: return createExprList();
			case EolPackage.EXP_RANGE: return createExpRange();
			case EolPackage.NEW_EXPRESSION: return createNewExpression();
			case EolPackage.SPECIAL_ASSIGNMENT_STATEMENT: return createSpecialAssignmentStatement();
			case EolPackage.MODEL_TYPE: return createModelType();
			case EolPackage.NATIVE_EXPRESSION: return createNativeExpression();
			case EolPackage.ETYPE: return createEType();
			case EolPackage.VOID_TYPE: return createVoidType();
			case EolPackage.PSEUDO_TYPE: return createPseudoType();
			case EolPackage.SELF_TYPE: return createSelfType();
			case EolPackage.SELF_CONTENT_TYPE: return createSelfContentType();
			case EolPackage.ORDERED_COLLECTION_TYPE: return createOrderedCollectionType();
			case EolPackage.UNIQUE_COLLECTION_TYPE: return createUniqueCollectionType();
			case EolPackage.SELF_INNERMOST_TYPE: return createSelfInnermostType();
			case EolPackage.OPERATION_ARG_TYPE: return createOperationArgType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextRegion createTextRegion() {
		TextRegionImpl textRegion = new TextRegionImpl();
		return textRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextPosition createTextPosition() {
		TextPositionImpl textPosition = new TextPositionImpl();
		return textPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Program createProgram() {
		ProgramImpl program = new ProgramImpl();
		return program;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Import createImport() {
		ImportImpl import_ = new ImportImpl();
		return import_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block createBlock() {
		BlockImpl block = new BlockImpl();
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AndOperatorExpression createAndOperatorExpression() {
		AndOperatorExpressionImpl andOperatorExpression = new AndOperatorExpressionImpl();
		return andOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanExpression createBooleanExpression() {
		BooleanExpressionImpl booleanExpression = new BooleanExpressionImpl();
		return booleanExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DivideOperatorExpression createDivideOperatorExpression() {
		DivideOperatorExpressionImpl divideOperatorExpression = new DivideOperatorExpressionImpl();
		return divideOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationLiteralExpression createEnumerationLiteralExpression() {
		EnumerationLiteralExpressionImpl enumerationLiteralExpression = new EnumerationLiteralExpressionImpl();
		return enumerationLiteralExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EqualsOperatorExpression createEqualsOperatorExpression() {
		EqualsOperatorExpressionImpl equalsOperatorExpression = new EqualsOperatorExpressionImpl();
		return equalsOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GreaterThanOperatorExpression createGreaterThanOperatorExpression() {
		GreaterThanOperatorExpressionImpl greaterThanOperatorExpression = new GreaterThanOperatorExpressionImpl();
		return greaterThanOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GreaterThanOrEqualToOperatorExpression createGreaterThanOrEqualToOperatorExpression() {
		GreaterThanOrEqualToOperatorExpressionImpl greaterThanOrEqualToOperatorExpression = new GreaterThanOrEqualToOperatorExpressionImpl();
		return greaterThanOrEqualToOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImpliesOperatorExpression createImpliesOperatorExpression() {
		ImpliesOperatorExpressionImpl impliesOperatorExpression = new ImpliesOperatorExpressionImpl();
		return impliesOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerExpression createIntegerExpression() {
		IntegerExpressionImpl integerExpression = new IntegerExpressionImpl();
		return integerExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LessThanOperatorExpression createLessThanOperatorExpression() {
		LessThanOperatorExpressionImpl lessThanOperatorExpression = new LessThanOperatorExpressionImpl();
		return lessThanOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LessThanOrEqualToOperatorExpression createLessThanOrEqualToOperatorExpression() {
		LessThanOrEqualToOperatorExpressionImpl lessThanOrEqualToOperatorExpression = new LessThanOrEqualToOperatorExpressionImpl();
		return lessThanOrEqualToOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodCallExpression createMethodCallExpression() {
		MethodCallExpressionImpl methodCallExpression = new MethodCallExpressionImpl();
		return methodCallExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MinusOperatorExpression createMinusOperatorExpression() {
		MinusOperatorExpressionImpl minusOperatorExpression = new MinusOperatorExpressionImpl();
		return minusOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiplyOperatorExpression createMultiplyOperatorExpression() {
		MultiplyOperatorExpressionImpl multiplyOperatorExpression = new MultiplyOperatorExpressionImpl();
		return multiplyOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameExpression createNameExpression() {
		NameExpressionImpl nameExpression = new NameExpressionImpl();
		return nameExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NegativeOperatorExpression createNegativeOperatorExpression() {
		NegativeOperatorExpressionImpl negativeOperatorExpression = new NegativeOperatorExpressionImpl();
		return negativeOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotEqualsOperatorExpression createNotEqualsOperatorExpression() {
		NotEqualsOperatorExpressionImpl notEqualsOperatorExpression = new NotEqualsOperatorExpressionImpl();
		return notEqualsOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotOperatorExpression createNotOperatorExpression() {
		NotOperatorExpressionImpl notOperatorExpression = new NotOperatorExpressionImpl();
		return notOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrOperatorExpression createOrOperatorExpression() {
		OrOperatorExpressionImpl orOperatorExpression = new OrOperatorExpressionImpl();
		return orOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlusOperatorExpression createPlusOperatorExpression() {
		PlusOperatorExpressionImpl plusOperatorExpression = new PlusOperatorExpressionImpl();
		return plusOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyCallExpression createPropertyCallExpression() {
		PropertyCallExpressionImpl propertyCallExpression = new PropertyCallExpressionImpl();
		return propertyCallExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RealExpression createRealExpression() {
		RealExpressionImpl realExpression = new RealExpressionImpl();
		return realExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringExpression createStringExpression() {
		StringExpressionImpl stringExpression = new StringExpressionImpl();
		return stringExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationExpression createVariableDeclarationExpression() {
		VariableDeclarationExpressionImpl variableDeclarationExpression = new VariableDeclarationExpressionImpl();
		return variableDeclarationExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XorOperatorExpression createXorOperatorExpression() {
		XorOperatorExpressionImpl xorOperatorExpression = new XorOperatorExpressionImpl();
		return xorOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationDefinition createOperationDefinition() {
		OperationDefinitionImpl operationDefinition = new OperationDefinitionImpl();
		return operationDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentStatement createAssignmentStatement() {
		AssignmentStatementImpl assignmentStatement = new AssignmentStatementImpl();
		return assignmentStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BreakStatement createBreakStatement() {
		BreakStatementImpl breakStatement = new BreakStatementImpl();
		return breakStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BreakAllStatement createBreakAllStatement() {
		BreakAllStatementImpl breakAllStatement = new BreakAllStatementImpl();
		return breakAllStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContinueStatement createContinueStatement() {
		ContinueStatementImpl continueStatement = new ContinueStatementImpl();
		return continueStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeleteStatement createDeleteStatement() {
		DeleteStatementImpl deleteStatement = new DeleteStatementImpl();
		return deleteStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForStatement createForStatement() {
		ForStatementImpl forStatement = new ForStatementImpl();
		return forStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfStatement createIfStatement() {
		IfStatementImpl ifStatement = new IfStatementImpl();
		return ifStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReturnStatement createReturnStatement() {
		ReturnStatementImpl returnStatement = new ReturnStatementImpl();
		return returnStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchStatement createSwitchStatement() {
		SwitchStatementImpl switchStatement = new SwitchStatementImpl();
		return switchStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WhileStatement createWhileStatement() {
		WhileStatementImpl whileStatement = new WhileStatementImpl();
		return whileStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelDeclarationStatement createModelDeclarationStatement() {
		ModelDeclarationStatementImpl modelDeclarationStatement = new ModelDeclarationStatementImpl();
		return modelDeclarationStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FOLMethodCallExpression createFOLMethodCallExpression() {
		FOLMethodCallExpressionImpl folMethodCallExpression = new FOLMethodCallExpressionImpl();
		return folMethodCallExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionStatement createExpressionStatement() {
		ExpressionStatementImpl expressionStatement = new ExpressionStatementImpl();
		return expressionStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchCaseDefaultStatement createSwitchCaseDefaultStatement() {
		SwitchCaseDefaultStatementImpl switchCaseDefaultStatement = new SwitchCaseDefaultStatementImpl();
		return switchCaseDefaultStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchCaseExpressionStatement createSwitchCaseExpressionStatement() {
		SwitchCaseExpressionStatementImpl switchCaseExpressionStatement = new SwitchCaseExpressionStatementImpl();
		return switchCaseExpressionStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetExpression createSetExpression() {
		SetExpressionImpl setExpression = new SetExpressionImpl();
		return setExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BagExpression createBagExpression() {
		BagExpressionImpl bagExpression = new BagExpressionImpl();
		return bagExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceExpression createSequenceExpression() {
		SequenceExpressionImpl sequenceExpression = new SequenceExpressionImpl();
		return sequenceExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderedSetExpression createOrderedSetExpression() {
		OrderedSetExpressionImpl orderedSetExpression = new OrderedSetExpressionImpl();
		return orderedSetExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapExpression createMapExpression() {
		MapExpressionImpl mapExpression = new MapExpressionImpl();
		return mapExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type createType() {
		TypeImpl type = new TypeImpl();
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnyType createAnyType() {
		AnyTypeImpl anyType = new AnyTypeImpl();
		return anyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionType createCollectionType() {
		CollectionTypeImpl collectionType = new CollectionTypeImpl();
		return collectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanType createBooleanType() {
		BooleanTypeImpl booleanType = new BooleanTypeImpl();
		return booleanType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerType createIntegerType() {
		IntegerTypeImpl integerType = new IntegerTypeImpl();
		return integerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RealType createRealType() {
		RealTypeImpl realType = new RealTypeImpl();
		return realType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringType createStringType() {
		StringTypeImpl stringType = new StringTypeImpl();
		return stringType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SetType createSetType() {
		SetTypeImpl setType = new SetTypeImpl();
		return setType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BagType createBagType() {
		BagTypeImpl bagType = new BagTypeImpl();
		return bagType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceType createSequenceType() {
		SequenceTypeImpl sequenceType = new SequenceTypeImpl();
		return sequenceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderedSetType createOrderedSetType() {
		OrderedSetTypeImpl orderedSetType = new OrderedSetTypeImpl();
		return orderedSetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapType createMapType() {
		MapTypeImpl mapType = new MapTypeImpl();
		return mapType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutableAnnotation createExecutableAnnotation() {
		ExecutableAnnotationImpl executableAnnotation = new ExecutableAnnotationImpl();
		return executableAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleAnnotation createSimpleAnnotation() {
		SimpleAnnotationImpl simpleAnnotation = new SimpleAnnotationImpl();
		return simpleAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KeyValue createKeyValue() {
		KeyValueImpl keyValue = new KeyValueImpl();
		return keyValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecialNameExpression createSpecialNameExpression() {
		SpecialNameExpressionImpl specialNameExpression = new SpecialNameExpressionImpl();
		return specialNameExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelElementType createModelElementType() {
		ModelElementTypeImpl modelElementType = new ModelElementTypeImpl();
		return modelElementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NativeType createNativeType() {
		NativeTypeImpl nativeType = new NativeTypeImpl();
		return nativeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelExpression createModelExpression() {
		ModelExpressionImpl modelExpression = new ModelExpressionImpl();
		return modelExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationBlock createAnnotationBlock() {
		AnnotationBlockImpl annotationBlock = new AnnotationBlockImpl();
		return annotationBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelDeclarationParameter createModelDeclarationParameter() {
		ModelDeclarationParameterImpl modelDeclarationParameter = new ModelDeclarationParameterImpl();
		return modelDeclarationParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThrowStatement createThrowStatement() {
		ThrowStatementImpl throwStatement = new ThrowStatementImpl();
		return throwStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbortStatement createAbortStatement() {
		AbortStatementImpl abortStatement = new AbortStatementImpl();
		return abortStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionStatement createTransactionStatement() {
		TransactionStatementImpl transactionStatement = new TransactionStatementImpl();
		return transactionStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalParameterExpression createFormalParameterExpression() {
		FormalParameterExpressionImpl formalParameterExpression = new FormalParameterExpressionImpl();
		return formalParameterExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExprList createExprList() {
		ExprListImpl exprList = new ExprListImpl();
		return exprList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpRange createExpRange() {
		ExpRangeImpl expRange = new ExpRangeImpl();
		return expRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NewExpression createNewExpression() {
		NewExpressionImpl newExpression = new NewExpressionImpl();
		return newExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecialAssignmentStatement createSpecialAssignmentStatement() {
		SpecialAssignmentStatementImpl specialAssignmentStatement = new SpecialAssignmentStatementImpl();
		return specialAssignmentStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelType createModelType() {
		ModelTypeImpl modelType = new ModelTypeImpl();
		return modelType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NativeExpression createNativeExpression() {
		NativeExpressionImpl nativeExpression = new NativeExpressionImpl();
		return nativeExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EType createEType() {
		ETypeImpl eType = new ETypeImpl();
		return eType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VoidType createVoidType() {
		VoidTypeImpl voidType = new VoidTypeImpl();
		return voidType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PseudoType createPseudoType() {
		PseudoTypeImpl pseudoType = new PseudoTypeImpl();
		return pseudoType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelfType createSelfType() {
		SelfTypeImpl selfType = new SelfTypeImpl();
		return selfType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelfContentType createSelfContentType() {
		SelfContentTypeImpl selfContentType = new SelfContentTypeImpl();
		return selfContentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderedCollectionType createOrderedCollectionType() {
		OrderedCollectionTypeImpl orderedCollectionType = new OrderedCollectionTypeImpl();
		return orderedCollectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UniqueCollectionType createUniqueCollectionType() {
		UniqueCollectionTypeImpl uniqueCollectionType = new UniqueCollectionTypeImpl();
		return uniqueCollectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelfInnermostType createSelfInnermostType() {
		SelfInnermostTypeImpl selfInnermostType = new SelfInnermostTypeImpl();
		return selfInnermostType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationArgType createOperationArgType() {
		OperationArgTypeImpl operationArgType = new OperationArgTypeImpl();
		return operationArgType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EolPackage getEolPackage() {
		return (EolPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EolPackage getPackage() {
		return EolPackage.eINSTANCE;
	}

} //EolFactoryImpl
