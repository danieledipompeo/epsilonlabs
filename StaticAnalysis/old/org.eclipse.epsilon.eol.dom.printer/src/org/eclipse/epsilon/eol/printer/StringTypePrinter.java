package org.eclipse.epsilon.eol.printer;

import org.eclipse.epsilon.eol.metamodel.*;

public class StringTypePrinter extends PrimitiveTypePrinter{

	@Override
	public String print(EolElement e, EolElementPrinterFactory f) {
		return "String";
	}

	@Override
	public boolean appliesTo(EolElement dom) {
		// TODO Auto-generated method stub
		return dom instanceof StringType;
	}

}
