package org.eclipse.epsilon.etl.dtx.editor.outline;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.common.dt.editor.outline.ModuleContentOutlinePage;
import org.eclipse.epsilon.common.dt.util.EclipseUtil;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.eol.EolLibraryModule;
import org.eclipse.epsilon.eol.dtx.editor.outline.DomOutlineElement;
import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.metamodel.TextRegion;
import org.eclipse.epsilon.etl.ast2etl.Ast2EtlContext;
import org.eclipse.epsilon.etl.ast2etl.EtlElementCreatorFactory;
import org.eclipse.epsilon.etl.dtx.editor.EtlxEditor;
import org.eclipse.epsilon.etl.visitor.resolution.type.impl.EtlTypeResolver;
import org.eclipse.epsilon.etl.visitor.resolution.variable.impl.EtlVariableResolver;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class EtlxOutlinePage extends ModuleContentOutlinePage {

	protected EtlxEditor etlxEditor;
	
	public EtlxOutlinePage(IDocumentProvider documentProvider,
			ITextEditor editor, ILabelProvider labelProvider) {
		super(documentProvider, editor, labelProvider);
		etlxEditor = (EtlxEditor) editor;
	}
	
	@Override
	public Object getOutlineRoot(IModule module) {
		
		Ast2EtlContext context = new Ast2EtlContext((EolLibraryModule) module);
		EtlElementCreatorFactory factory = context.getEtlElementCreatorFactory();
		EolElement dom = factory.createDomElement(module.getAst(), null, context);
		
		EtlVariableResolver etlvr = new EtlVariableResolver();
		etlvr.run(dom);
		
		EtlTypeResolver etltr = new EtlTypeResolver((EolLibraryModule) module);
		etltr.run(dom);
		
		etlxEditor.setEolLibraryModule(dom);
		
		return new DomOutlineElement(dom);
	}
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		
		if (!linkWithEditorAction.isChecked()) return;
		
		try {
			
			EolElement selected = ((DomOutlineElement) ((IStructuredSelection) event
					.getSelection()).getFirstElement()).getDomElement();
		
			
			IDocument doc = editor.getDocumentProvider().getDocument(editor.getEditorInput());
			TextRegion region = selected.getRegion();
			
			int startOffset = doc.getLineOffset(region.getStart().getLine()-1) + region.getStart().getColumn();
			int endOffset = doc.getLineOffset(region.getEnd().getLine()-1) + region.getEnd().getColumn();
			
			FileEditorInput fileInputEditor = (FileEditorInput) editor.getEditorInput();
			IFile file = fileInputEditor.getFile();
			
			
//			EclipseUtil.openEditorAt(new File(file.getLocation().toOSString()), region.getStart().getLine(), 
//					region.getStart().getColumn(), false);

			EclipseUtil.openEditorAt(file, region.getStart().getLine(), 
					region.getStart().getColumn(), endOffset - startOffset, false);

		}
		catch (Exception ex) {
			
		}
	}
	
	@Override
	protected IContentProvider createContentProvider() {
		return new ITreeContentProvider() {
			
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				
			}
			
			@Override
			public void dispose() {
				
			}
			
			@Override
			public boolean hasChildren(Object element) {
				return getChildren(element).length > 0;
			}
			
			@Override
			public Object getParent(Object element) {
				return ((EObject) element).eContainer();
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				return getChildren(inputElement);
			}
			
			@Override
			public Object[] getChildren(Object parentElement) {
				List<EObject> contents = ((DomOutlineElement) parentElement).getDomElement().eContents();
				List<DomOutlineElement> children = new ArrayList<DomOutlineElement>();
				for (EObject content : contents) {
					if (content instanceof EolElement) {
						children.add(new DomOutlineElement((EolElement) content));
					}
				}
				return children.toArray();
			}
		};
	}
	
}
