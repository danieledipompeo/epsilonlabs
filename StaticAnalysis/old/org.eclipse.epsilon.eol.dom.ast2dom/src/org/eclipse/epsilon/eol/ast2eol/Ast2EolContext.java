package org.eclipse.epsilon.eol.ast2eol;

import java.util.HashMap;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.metamodel.*;
import org.eclipse.epsilon.eol.metamodel.impl.EolFactoryImpl;

public class Ast2EolContext {

	EolElementCreatorFactory eolElementCreatorFactory;
	EolFactory eolFactory;
	//LinkedList<MetaModel> metaModels;
	HashMap<EolElement, AST> trace = new HashMap<EolElement, AST>();
	
	public Ast2EolContext()
	{
		eolElementCreatorFactory = new EolElementCreatorFactory();
		eolFactory = new EolFactoryImpl();
		//metaModels = new LinkedList<MetaModel>();
	}
	
	public Ast2EolContext(EolElementCreatorFactory eolElementCreatorFactory)
	{
		this.eolElementCreatorFactory = eolElementCreatorFactory;
		eolFactory = new EolFactoryImpl();
	}
	
	public EolFactory getEolFactory()
	{
		return eolFactory;
	}
	
	public EolElementCreatorFactory getEolElementCreatorFactory()
	{
		return eolElementCreatorFactory;
	}
	
	public HashMap<EolElement, AST> getTrace() {
		return trace;
	}
	
	
	/*
	public LinkedList<MetaModel> getMetaModels()
	{
		return metaModels;
	}
	
	public MetaModel createMetaModel()
	{
		return new MetaModel();
	}
	
	public boolean containsModel(String metaModelName)
	{
		boolean result = false;
		for(MetaModel metamodel: metaModels)
		{
			if (metamodel.getMetaModelName().equals(metaModelName)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public MetaModel getMetaModel(String metaModelName)
	{
		MetaModel result = null;
		for(MetaModel metaModel: metaModels)
		{
			if(metaModel.getMetaModelName().equals(metaModelName))
			{
				result = metaModel;
			}
		}
		return result;
	}
	
	public String resolveType(String metaModelName, String metaModelElement)
	{
		//System.err.println("R");
		String result = "";
		if(containsModel(metaModelName))
		{
			MetaModel mm = getMetaModel(metaModelName);
			result = mm.getTypeForMetaClass(metaModelElement);
		}
		else {
			result = "unfound";
		}
		return result;
	}
		*/
}
