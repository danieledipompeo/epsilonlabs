package org.eclipse.epsilon.eol.printer;

import org.eclipse.epsilon.eol.metamodel.*;

public class IntegerTypePrinter extends PrimitiveTypePrinter{

	@Override
	public String print(EolElement e, EolElementPrinterFactory f) {
		return "Integer";
	}

	@Override
	public boolean appliesTo(EolElement dom) {
		// TODO Auto-generated method stub
		return dom instanceof IntegerType;
	}

}
