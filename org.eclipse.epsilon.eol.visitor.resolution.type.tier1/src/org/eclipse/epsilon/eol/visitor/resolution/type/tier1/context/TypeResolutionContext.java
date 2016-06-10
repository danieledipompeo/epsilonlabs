package org.eclipse.epsilon.eol.visitor.resolution.type.tier1.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.analysis.model.driver.IMetamodelDriver;
import org.eclipse.epsilon.analysis.model.driver.IMetamodelManager;
import org.eclipse.epsilon.analysis.model.driver.plainxml.PlainXMLIMetamodelDriver;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.metamodel.EOLElement;
import org.eclipse.epsilon.eol.metamodel.EOLLibraryModule;
import org.eclipse.epsilon.eol.metamodel.Expression;
import org.eclipse.epsilon.eol.metamodel.NameExpression;
import org.eclipse.epsilon.eol.metamodel.Statement;
import org.eclipse.epsilon.eol.metamodel.Type;
import org.eclipse.epsilon.eol.metamodel.VariableDeclarationExpression;
import org.eclipse.epsilon.eol.problem.LogBook;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.operationDefinitionUtil.OperationDefinitionManager;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.util.TypeUtil;

public class TypeResolutionContext {
	
	protected EolModule eolModule = null;
	
	//instance
	protected static TypeResolutionContext instance = null;
	
	//supported drivers
	protected final String[] supportedDrivers = {"EMF", "XML"};
	
	//current program
	protected EOLLibraryModule currentProgram = null;
	
	//current eol element
//	protected EOLElement currentEOLElement = null;
	
	//imetamodel manager
	protected IMetamodelManager iMetamodelManager = new IMetamodelManager();
	
	//type registry
	protected TypeRegistry typeRegistry = new TypeRegistry();
	
	//expression to registry map
	protected HashMap<Expression, HashSet<Type>> expressionTypeRegistry = new HashMap<Expression, HashSet<Type>>();
	
	//metamodel related keywords
	protected ArrayList<String> metamodelRelatedKeywords = new ArrayList<String>();
	
	//current statement
	protected Statement currentStatement = null;
	
	//property call stacks
	protected Stack<String> propertyCallStack = new Stack<String>();

	protected ArrayList<String> EOLReservedKeywords = new ArrayList<String>();
	
	protected boolean handlingBranchCondition = false;
	
	public void setEolModule(EolModule eolModule) {
		this.eolModule = eolModule;
	}
	
	public EolModule getEolModule() {
		return eolModule;
	}
	
	public String getDirectory(String path)
	{
		int lastIndexOf = path.lastIndexOf("/");
		return path.substring(0, lastIndexOf+1);
	}
	
	public String getParentFolderDirectory()
	{
		return getDirectory(eolModule.getSourceFile().getAbsolutePath());
	}
	
	public void setCurrentStatement(Statement currentStatement) {
		this.currentStatement = currentStatement;
	}
	
	public Statement getCurrentStatement() {
		return currentStatement;
	}
	
	public void clearPropertyCallStack()
	{
		propertyCallStack.clear();
	}
	
	public void pushProperty(String string)
	{
		propertyCallStack.push(string);
	}
	
	public void popProperty()
	{
		propertyCallStack.pop();
	}
	
	public String getProperty()
	{
		return propertyCallStack.pop();
	}
	
	public ArrayList<String> getPropertyCallChain()
	{
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < propertyCallStack.size(); i++)
		{
			result.add(propertyCallStack.get(0));
		}
		return result;
	}
	
	
	protected TypeResolutionContext()
	{}
	
	public void generateKeyWordsFromModelDeclarations()
	{
		metamodelRelatedKeywords.addAll(iMetamodelManager.generateKeyWordsFromModelDeclarations());
	}
	
	public ArrayList<String> getMetamodelRelatedKeywords() {
		return metamodelRelatedKeywords;
	}
	
	public static TypeResolutionContext getInstanace()
	{
		if (instance == null) {
			instance = new TypeResolutionContext();
			instance.initialiseEOLReservedKeywords();
		}
		return instance;
	}
	
	public static TypeResolutionContext getInstance(boolean initialise)
	{
		if (initialise) {
			instance = new TypeResolutionContext();
			OperationDefinitionManager.getInstance(true);
			LogBook.getInstance();
			return instance;
		}
		else {
			return getInstanace();
		}
	}
	
	public TypeUtil getTypeUtil() {
		return TypeUtil.getInstance();
	}
	
	public void addIMetamodelDriver(IMetamodelDriver iMetamodelDriver)
	{
		iMetamodelManager.addIMetamodelDriver(iMetamodelDriver);
	}
	
	public String[] getSupportedDrivers() {
		return supportedDrivers;
	}
	public IMetamodelManager getiMetamodelManager() {
		return iMetamodelManager;
	}
	
	public OperationDefinitionManager getOperationDefinitionManager() {
		return OperationDefinitionManager.getInstance();
	}
	
	public void copyLocation(EOLElement created, EOLElement targetLocation)
	{
		created.setRegion(EcoreUtil.copy(targetLocation.getRegion()));
	}
	
	public void setAssets(EOLElement obj, EOLElement container)
	{
		obj.setRegion(EcoreUtil.copy(container.getRegion()));
		obj.setContainer(container);
	}
	
	public TypeRegistry getTypeRegistry() {
		return typeRegistry;
	}
	
	public void registerNameExpression(Expression expression, Type type)
	{
		if (expression instanceof NameExpression) {
			NameExpression _expression = (NameExpression) expression;
			if (_expression.getResolvedContent() != null) {
				if (_expression.getResolvedContent() instanceof VariableDeclarationExpression) {
					VariableDeclarationExpression vde = (VariableDeclarationExpression) _expression.getResolvedContent();
					typeRegistry.assignType(vde, type);
				}
			}
		}
	}
	
	public void registerExpressionWithTypes(Expression expression, HashSet<Type> types)
	{
		expressionTypeRegistry.put(expression, types);
	}
	
	public HashSet<Type> getTypesOfExpression(Expression expression)
	{
		return expressionTypeRegistry.get(expression);
	}
	
	public ArrayList<String> getEOLReservedKeywords() {
		return EOLReservedKeywords;
	}
	
	public boolean isEOLReservedKeywords(String str)
	{
		return EOLReservedKeywords.contains(str);
	}
	
	protected void initialiseEOLReservedKeywords()
	{
		EOLReservedKeywords.add("import");
		
		EOLReservedKeywords.add("Any");
		EOLReservedKeywords.add("Boolean");
		EOLReservedKeywords.add("Integer");
		EOLReservedKeywords.add("Real");
		EOLReservedKeywords.add("String");
		EOLReservedKeywords.add("Bag");
		EOLReservedKeywords.add("OrderedSet");
		EOLReservedKeywords.add("Set");
		EOLReservedKeywords.add("Sequenece");
		EOLReservedKeywords.add("Map");
		
		EOLReservedKeywords.add("null");
		EOLReservedKeywords.add("pre");
		EOLReservedKeywords.add("post");
		
		EOLReservedKeywords.add("model");
		EOLReservedKeywords.add("alias");
		EOLReservedKeywords.add("driver");
		EOLReservedKeywords.add("metamodelNSURI");
		EOLReservedKeywords.add("metamodelPath");
		
		EOLReservedKeywords.add("delete");
		EOLReservedKeywords.add("transaction");
		EOLReservedKeywords.add("abort");
		
		EOLReservedKeywords.add("switch");
		EOLReservedKeywords.add("case");
		EOLReservedKeywords.add("default");
		
		EOLReservedKeywords.add("break");
		EOLReservedKeywords.add("breakAll");
		EOLReservedKeywords.add("continue");
		
		EOLReservedKeywords.add("if");
		EOLReservedKeywords.add("else");
		
		EOLReservedKeywords.add("while");
		EOLReservedKeywords.add("for");
		
		EOLReservedKeywords.add("in");
		
		EOLReservedKeywords.add("and");
		EOLReservedKeywords.add("or");
		EOLReservedKeywords.add("implies");
		EOLReservedKeywords.add("xor");
		
		EOLReservedKeywords.add("not");
		
		EOLReservedKeywords.add("new");
		
		EOLReservedKeywords.add("Collection");
		
		EOLReservedKeywords.add("operation");
		EOLReservedKeywords.add("self");
		EOLReservedKeywords.add("_result");
	}
	
	public boolean isMetamodelRelatedKeywords(String str)
	{
		boolean openWorld = false;
		for(IMetamodelDriver imd: iMetamodelManager.getiMetamodelDrivers())
		{
			if (imd instanceof PlainXMLIMetamodelDriver) {
				openWorld = true;
			}
		}
		if (openWorld) {
			if (isXMLKeyword(str)) {
				return true;
			}
		}
		return metamodelRelatedKeywords.contains(str);
	}
	
	public boolean isXMLKeyword(String str)
	{
		if (str.startsWith("t_")) {
			return true;
		}
		return false;
	}
	
	public boolean isHandlingBranchCondition() {
		return handlingBranchCondition;
	}
	
	public void setHandlingBranchCondition(boolean bool)
	{
		handlingBranchCondition = bool;
	}
	
}
