//package org.eclipse.epsilon.etl.dtx.analysis.transformation.dependency;
//
//import java.util.ArrayList;
//
//import org.eclipse.core.resources.IFile;
//import org.eclipse.emf.ecore.EAttribute;
//import org.eclipse.emf.ecore.EClass;
//import org.eclipse.emf.ecore.EStructuralFeature;
//import org.eclipse.epsilon.common.dt.util.EclipseUtil;
//import org.eclipse.epsilon.eol.metamodel.EolElement;
//import org.eclipse.epsilon.eol.metamodel.TextRegion;
//import org.eclipse.epsilon.eol.parse.Eol_EolParserRules.elseStatement_return;
//import org.eclipse.epsilon.eol.parse.Eol_EolParserRules.returnStatement_return;
//import org.eclipse.epsilon.etl.dtx.analysis.metamodel.coverage.CoverageUnit;
//import org.eclipse.epsilon.etl.dtx.analysis.metamodel.coverage.StringTableColumn;
//import org.eclipse.epsilon.etl.dtx.editor.EtlxEditor;
//import org.eclipse.epsilon.etl.metamodel.EtlProgram;
//import org.eclipse.epsilon.etl.metamodel.TransformationRule;
//import org.eclipse.epsilon.etl.visitor.coverage.analysis.context.MetaElementContainer;
//import org.eclipse.epsilon.etl.visitor.coverage.analysis.impl.EtlCoverageAnalyser;
//import org.eclipse.jface.action.IAction;
//import org.eclipse.jface.action.IMenuCreator;
//import org.eclipse.jface.action.IMenuManager;
//import org.eclipse.jface.action.IToolBarManager;
//import org.eclipse.jface.action.Separator;
//import org.eclipse.jface.resource.ImageDescriptor;
//import org.eclipse.jface.text.IDocument;
//import org.eclipse.jface.util.IPropertyChangeListener;
//import org.eclipse.jface.viewers.IStructuredContentProvider;
//import org.eclipse.jface.viewers.ITableLabelProvider;
//import org.eclipse.jface.viewers.LabelProvider;
//import org.eclipse.jface.viewers.TableViewer;
//import org.eclipse.jface.viewers.Viewer;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.custom.CTabFolder;
//import org.eclipse.swt.custom.CTabItem;
//import org.eclipse.swt.events.HelpListener;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.TableColumn;
//import org.eclipse.ui.IActionBars;
//import org.eclipse.ui.IEditorPart;
//import org.eclipse.ui.IWorkbench;
//import org.eclipse.ui.IWorkbenchPage;
//import org.eclipse.ui.IWorkbenchWindow;
//import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.part.FileEditorInput;
//import org.eclipse.ui.part.ViewPart;
//
//public class TransformationAnalysis extends ViewPart {
//
//	protected final int CURRENTSCOPE_COLUMN = 0;
//	protected final int METAMODEL_COLUMN = 1;
//	protected final int METAELEMENT_COLUMN = 2;
//	protected final int PROPERTY_COLUMN = 3;
//	protected final int PROPERTYTYPE_COLUMN = 4;
//	protected final int USED_COLUMN = 5;
//	
//	public static final String ID = "org.eclipse.epsilon.etl.dtx.analysis.transformation.dependency";
//	
//	protected TransformationDependencyViewer transformationDependencyViewer;
//	protected EtlCoverageAnalyser coverageAnalyser = new EtlCoverageAnalyser();
//	protected TransformationRule selectedTransformationRule;
//	private TableViewer coverageAnalysisViewer;
//
//	private CTabFolder folder;
//
//
//	public TransformationAnalysis() {
//		super();
//	}
//
//	public EtlxEditor getEditor()
//	{
//		IWorkbench wb = PlatformUI.getWorkbench();
//		IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
//		IWorkbenchPage page = window.getActivePage();
//		IEditorPart editor = page.getActiveEditor();
//		if (editor instanceof EtlxEditor) {
//			return (EtlxEditor) editor;
//		}
//		else {
//			return null;
//		}
//	}
//	@Override
//	public void createPartControl(Composite parent) {
//		
//		EtlxEditor leEditor = getEditor();
//		if (leEditor.getEolLibraryModule() != null) {
//			coverageAnalyser.run(leEditor.getEolLibraryModule());
//		}
//
//		folder = new CTabFolder(parent, SWT.NONE);
//		folder.setSimple(true);
//		folder.setTabPosition(SWT.BOTTOM);
//
//		CTabItem overviewItem = new CTabItem(folder, SWT.NONE);
//		overviewItem.setText("Overview");
//		transformationDependencyViewer = new TransformationDependencyViewer(folder, SWT.NONE, this);
//		overviewItem.setControl(transformationDependencyViewer);
//		
//		CTabItem coverageItem = new CTabItem(folder, SWT.NONE);
//		coverageItem.setText("Coverage Analysis");
//		createCoverageAnalysisTable();
//		coverageItem.setControl(coverageAnalysisViewer.getControl());
//		
//		folder.setSelection(overviewItem);
//		
//		contributeToActionBars();
//	}
//	
//	private void contributeToActionBars() {
//		IActionBars bars = getViewSite().getActionBars();
//		fillLocalPullDown(bars.getMenuManager());
//		fillLocalToolBar(bars.getToolBarManager());
//	}
//	
//	private void fillLocalPullDown(IMenuManager manager)
//	{
//		manager.removeAll();
//		manager.add(new PullDownAction("global", SWT.NONE, null, this));
//		manager.add(new Separator());
//		for(EolElement eolElement: transformationDependencyViewer.getGraphMap().values())
//		{
//			if (eolElement instanceof TransformationRule) {
//				TransformationRule rule = (TransformationRule) eolElement;
//				manager.add(new PullDownAction(rule.getName().getName(), SWT.NONE, rule, this));
//			}
//		}
//	}
//	
//	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(new RefreshTransformationAnalysisViewAction(this));
//	}
//
//
//	@Override
//	public void setFocus() {
//		// TODO Auto-generated method stub
//
//	}
//	
//	public void refreshTransformationDependencyViewer()
//	{
//		transformationDependencyViewer.refresh();
//	}
//	
//	public void refreshTransformationCoverageAnalysisViewer()
//	{
//		EtlxEditor leEditor = getEditor();
//		if (leEditor.getEolLibraryModule() != null) {
//			coverageAnalyser.run(leEditor.getEolLibraryModule());
//		}
//		coverageAnalysisViewer.refresh();
//	}
//
//	
//	public void refresh(){
//		
//		contributeToActionBars();
//		refreshTransformationDependencyViewer();
//		refreshTransformationCoverageAnalysisViewer();
//	}
//	
//	
//	class CoverageAnalysisLabelProvider extends LabelProvider implements ITableLabelProvider{
//
//		@Override
//		public Image getColumnImage(Object element, int columnIndex) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public String getColumnText(Object element, int columnIndex) {
//			CoverageUnit unit = (CoverageUnit) element;
//			if (columnIndex == CURRENTSCOPE_COLUMN) {
//				if (selectedTransformationRule == null) {
//					return "Global";
//				}
//				else {
//					return selectedTransformationRule.getName().getName();
//				}
//			}
//			else if (columnIndex == METAMODEL_COLUMN) {
//				return unit.getMetaModel();
//			}
//			else if (columnIndex == METAELEMENT_COLUMN) {
//				return unit.getMetaElement();
//			}
//			else if (columnIndex == PROPERTY_COLUMN) {
//				return unit.getProperty();
//			}
//			else if (columnIndex == PROPERTYTYPE_COLUMN) {
//				return unit.getPropertyType();
//			}
//			else if (columnIndex == USED_COLUMN) {
//				return unit.getUsed();
//			}
//			return "_";
//		}
//		
//	}
//	
//	class ViewContentProvider implements IStructuredContentProvider {
//		public void dispose() {
//		}
//		public Object[] getElements(Object parent) {
//			if (selectedTransformationRule == null) {
//				return getUnits(coverageAnalyser.getContext().getCoverageForGlobal(), true).toArray();
//			}
//			else {
//				return getUnits(coverageAnalyser.getContext().getCoverageForTransformation(selectedTransformationRule), false).toArray();
//			}
//		}
//		@Override
//		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
//			// TODO Auto-generated method stub
//			
//		}
//	}
//
//	
//	public void createCoverageAnalysisTable(){
//		coverageAnalysisViewer = new TableViewer(folder, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
//		coverageAnalysisViewer.setContentProvider(new ViewContentProvider());
//		coverageAnalysisViewer.setLabelProvider(new CoverageAnalysisLabelProvider());
//		coverageAnalysisViewer.setInput(getViewSite());
//		
//		TableColumn column = new StringTableColumn(coverageAnalysisViewer, SWT.FULL_SELECTION);
//	    column.setText("Current Scope");
//	    column.setWidth(125);
//	    
//	    column = new StringTableColumn(coverageAnalysisViewer, SWT.FULL_SELECTION);
//	    column.setText("Meta-model");
//	    column.setWidth(125);
//	    
//	    column = new StringTableColumn(coverageAnalysisViewer, SWT.FULL_SELECTION);
//	    column.setText("Meta-element");
//	    column.setWidth(125);
//	    
//	    column = new StringTableColumn(coverageAnalysisViewer, SWT.FULL_SELECTION);
//	    column.setText("Property");
//	    column.setWidth(125);
//	    
//	    column = new StringTableColumn(coverageAnalysisViewer, SWT.FULL_SELECTION);
//	    column.setText("Property Type");
//	    column.setWidth(125);
//	    
//	    column = new StringTableColumn(coverageAnalysisViewer, SWT.FULL_SELECTION);
//	    column.setText("Used");
//	    column.setWidth(125);
//	    
//	    coverageAnalysisViewer.getTable().setHeaderVisible(true);
//	    coverageAnalysisViewer.getTable().setLinesVisible(true);
//
//	}
//	
//
//	public ArrayList<CoverageUnit> getUnits(ArrayList<MetaElementContainer> containers, boolean global)
//	{
//		ArrayList<CoverageUnit> result = new ArrayList<CoverageUnit>();
//		for(MetaElementContainer mec: containers)
//		{
//			EClass eClass = mec.getEClass();
//			for(EStructuralFeature feature: mec.getAllFeatures())
//			{
//				if (mec.getFeaturesAccessedViaOpposite().contains(feature)) {
//					result.add(new CoverageUnit(eClass, feature, "via opposite"));
//				}
//				else {
//					result.add(new CoverageUnit(eClass, feature, "true"));	
//				}
//			}
//			for(EStructuralFeature feature: mec.getAllUnusedFeatures())
//			{
//				result.add(new CoverageUnit(eClass, feature, "false"));
//			}
//		}
//		if (global) {
//			for(EClass eClass: coverageAnalyser.getContext().getUnusedClasses())
//			{
//				if (eClass.isAbstract() || eClass.isInterface()) {
//					
//				}
//				else {
//					result.add(new CoverageUnit(eClass, null, "false"));
//				}
//				
//			}
//		}
//		return result;
//	}
//	
//	public void setSelectedTransformationRule(
//			TransformationRule selectedTransformationRule) {
//		this.selectedTransformationRule = selectedTransformationRule;
//	}
//	
//	public void selectElementInEditor(EolElement selectedElement)
//	{
//		if (selectedElement != null) {
//			EtlxEditor editor = getEditor();
//			try {
//				IDocument doc = editor.getDocumentProvider().getDocument(editor.getEditorInput());
//				TextRegion region = selectedElement.getRegion();
//				
//				int startOffset = doc.getLineOffset(region.getStart().getLine()-1) + region.getStart().getColumn();
//				int endOffset = doc.getLineOffset(region.getEnd().getLine()-1) + region.getEnd().getColumn();
//				
//				FileEditorInput fileInputEditor = (FileEditorInput) editor.getEditorInput();
//				IFile file = fileInputEditor.getFile();
//
//				EclipseUtil.openEditorAt(file, region.getStart().getLine(), 
//						region.getStart().getColumn(), endOffset - startOffset, false);
//
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
//
//	
//}
