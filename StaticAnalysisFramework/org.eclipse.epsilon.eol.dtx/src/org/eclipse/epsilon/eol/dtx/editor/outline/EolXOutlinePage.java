package org.eclipse.epsilon.eol.dtx.editor.outline;

import org.eclipse.core.resources.IFile;
import org.eclipse.epsilon.common.dt.editor.AbstractModuleEditor;
import org.eclipse.epsilon.common.dt.editor.outline.ModuleContentOutlinePage;
import org.eclipse.epsilon.common.dt.util.EclipseUtil;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.ast2eol.context.Ast2EolContext;
import org.eclipse.epsilon.eol.ast2eol.context.EolElementCreatorFactory;
import org.eclipse.epsilon.eol.metamodel.EOLElement;
import org.eclipse.epsilon.eol.metamodel.TextRegion;
import org.eclipse.epsilon.eol.visitor.resolution.type.naive.impl.nonImportant.EOLTypeResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.EOLVariableResolver;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;

public class EolXOutlinePage extends ModuleContentOutlinePage{
	
	public EolXOutlinePage(IDocumentProvider documentProvider,
			AbstractModuleEditor editor, ILabelProvider labelProvider) {
		//super(documentProvider, editor, labelProvider);
		super(documentProvider, editor, labelProvider);
		addSelectionChangedListener(this);
	}
	

	@Override
	public Object getOutlineRoot(IModule module) {
		Ast2EolContext context = new Ast2EolContext((EolModule) module);
		EolElementCreatorFactory factory = context.getEolElementCreatorFactory();
		EOLElement eolElement = factory.createEOLElement(module.getAst(), null, context);
		
		EOLVariableResolver variableResolver = new EOLVariableResolver();
		variableResolver.getVariableResolutionContext().setEolModule((EolModule) module);
		//VariableResolutionContext.getInstance().setEolModule((EolModule) module);
		variableResolver.run(eolElement);
		
		EOLTypeResolver typeResolver = new EOLTypeResolver();
		typeResolver.getTypeResolutionContext().setEolModule((EolModule) module);
		//TypeResolutionContext.getInstanace().setEolModule((EolModule) module);
		typeResolver.run(eolElement);
				
		return new EOLOutlineElement(eolElement);
	}
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (!linkWithEditorAction.isChecked()) {
			return;
		}
		try {
			
			EOLElement selected = ((EOLOutlineElement)((IStructuredSelection)event.getSelection()).getFirstElement()).getEolElement();
			IDocument doc = editor.getDocumentProvider().getDocument(editor.getEditorInput());
			
			TextRegion region = selected.getRegion();
			
			int startOffset = doc.getLineOffset(region.getStart().getLine()-1) + region.getStart().getColumn();
			int endOffset = doc.getLineOffset(region.getEnd().getLine()-1) + region.getEnd().getColumn();

			FileEditorInput fileInputEditor = (FileEditorInput) editor.getEditorInput();
			IFile file = fileInputEditor.getFile();
			

			EclipseUtil.openEditorAt(file, region.getStart().getLine(), 
					region.getStart().getColumn(), endOffset - startOffset, false);

			
		} catch (Exception e) {
			
		}
	}
	
	@Override
	protected IContentProvider createContentProvider() {
		return new EOLElementContentProvider();
	}

}
