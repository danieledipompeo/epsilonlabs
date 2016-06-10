package org.eclipse.epsilon.evl.dtx.editor.outline;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.common.dt.editor.AbstractModuleEditor;
import org.eclipse.epsilon.common.dt.editor.outline.ModuleContentOutlinePage;
import org.eclipse.epsilon.common.dt.util.EclipseUtil;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.eol.EolLibraryModule;
import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.metamodel.TextRegion;
import org.eclipse.epsilon.evl.ast2evl.Ast2EvlContext;
import org.eclipse.epsilon.evl.ast2evl.EvlElementCreatorFactory;
import org.eclipse.epsilon.evl.visitor.resolution.impl.EvlVariableResolver;
import org.eclipse.epsilon.evl.visitor.resolution.type.impl.EvlTypeResolver;
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

public class EvlxOutlinePage extends ModuleContentOutlinePage{

	public EvlxOutlinePage(IDocumentProvider documentProvider,
			AbstractModuleEditor editor, ILabelProvider labelProvider) {
		super(documentProvider, editor, labelProvider);
	}
	
	@Override
	public Object getOutlineRoot(IModule module) {
		
		String path = module.getSourceUri().getPath();
		//System.err.println("=============" + path);
		int lastIndexOf = path.lastIndexOf("/");
		//System.out.println("--------------------" + lastIndexOf);
		//System.out.println("=======================" + path.substring(0, lastIndexOf+1));
		String directoryPathString = path.substring(0, lastIndexOf+1);		
		
		
		Ast2EvlContext context = new Ast2EvlContext((EolLibraryModule) module);
		EvlElementCreatorFactory factory = context.getEvlElementCreatorFactory();
		EolElement dom = factory.createDomElement(module.getAst(), null, context);
		
		EvlVariableResolver evlvr = new EvlVariableResolver();
		evlvr.run(dom);
		
		EvlTypeResolver evltr = new EvlTypeResolver((EolLibraryModule) module);
		evltr.run(dom);
		
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
