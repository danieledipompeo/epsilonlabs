/**
 * Generated with Acceleo
 */
package ewe.parts.impl;

// Start of user code for imports
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import ewe.parts.EmlTaskPropertiesEditionPart;
import ewe.parts.EweViewsRepository;
import ewe.providers.EweMessages;


// End of user code

/**
 * 
 * 
 */
public class EmlTaskPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, EmlTaskPropertiesEditionPart {

	protected Text src;
protected ReferencesTable exports;
protected List<ViewerFilter> exportsBusinessFilters = new ArrayList<ViewerFilter>();
protected List<ViewerFilter> exportsFilters = new ArrayList<ViewerFilter>();
protected ReferencesTable uses;
protected List<ViewerFilter> usesBusinessFilters = new ArrayList<ViewerFilter>();
protected List<ViewerFilter> usesFilters = new ArrayList<ViewerFilter>();
protected ReferencesTable models;
protected List<ViewerFilter> modelsBusinessFilters = new ArrayList<ViewerFilter>();
protected List<ViewerFilter> modelsFilters = new ArrayList<ViewerFilter>();
	protected Text useMatchTrace;
	protected Text exportTransformationTrace;
	protected Text exportMergeTrace;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public EmlTaskPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createFigure(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public Composite createFigure(final Composite parent) {
		view = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(view);
		return view;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createControls(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControls(Composite view) { 
		CompositionSequence emlTaskStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = emlTaskStep.addStep(EweViewsRepository.EmlTask.Properties.class);
		propertiesStep.addStep(EweViewsRepository.EmlTask.Properties.src);
		propertiesStep.addStep(EweViewsRepository.EmlTask.Properties.exports);
		propertiesStep.addStep(EweViewsRepository.EmlTask.Properties.uses);
		propertiesStep.addStep(EweViewsRepository.EmlTask.Properties.models);
		propertiesStep.addStep(EweViewsRepository.EmlTask.Properties.useMatchTrace);
		propertiesStep.addStep(EweViewsRepository.EmlTask.Properties.exportTransformationTrace);
		propertiesStep.addStep(EweViewsRepository.EmlTask.Properties.exportMergeTrace);
		
		
		composer = new PartComposer(emlTaskStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == EweViewsRepository.EmlTask.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == EweViewsRepository.EmlTask.Properties.src) {
					return createSrcText(parent);
				}
				if (key == EweViewsRepository.EmlTask.Properties.exports) {
					return createExportsAdvancedTableComposition(parent);
				}
				if (key == EweViewsRepository.EmlTask.Properties.uses) {
					return createUsesAdvancedTableComposition(parent);
				}
				if (key == EweViewsRepository.EmlTask.Properties.models) {
					return createModelsAdvancedTableComposition(parent);
				}
				if (key == EweViewsRepository.EmlTask.Properties.useMatchTrace) {
					return createUseMatchTraceText(parent);
				}
				if (key == EweViewsRepository.EmlTask.Properties.exportTransformationTrace) {
					return createExportTransformationTraceText(parent);
				}
				if (key == EweViewsRepository.EmlTask.Properties.exportMergeTrace) {
					return createExportMergeTraceText(parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	/**
	 * 
	 */
	protected Composite createPropertiesGroup(Composite parent) {
		Group propertiesGroup = new Group(parent, SWT.NONE);
		propertiesGroup.setText(EweMessages.EmlTaskPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createSrcText(Composite parent) {
		SWTUtils.createPartLabel(parent, EweMessages.EmlTaskPropertiesEditionPart_SrcLabel, propertiesEditionComponent.isRequired(EweViewsRepository.EmlTask.Properties.src, EweViewsRepository.SWT_KIND));
		src = new Text(parent, SWT.BORDER);
		GridData srcData = new GridData(GridData.FILL_HORIZONTAL);
		src.setLayoutData(srcData);
		src.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.src, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, src.getText()));
			}

		});
		src.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.src, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, src.getText()));
				}
			}

		});
		EditingUtils.setID(src, EweViewsRepository.EmlTask.Properties.src);
		EditingUtils.setEEFtype(src, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.EmlTask.Properties.src, EweViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createExportsAdvancedTableComposition(Composite parent) {
		this.exports = new ReferencesTable(EweMessages.EmlTaskPropertiesEditionPart_ExportsLabel, new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.exports, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				exports.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.exports, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				exports.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.exports, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				exports.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.exports, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				exports.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.exportsFilters) {
			this.exports.addFilter(filter);
		}
		this.exports.setHelpText(propertiesEditionComponent.getHelpContent(EweViewsRepository.EmlTask.Properties.exports, EweViewsRepository.SWT_KIND));
		this.exports.createControls(parent);
		this.exports.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.exports, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData exportsData = new GridData(GridData.FILL_HORIZONTAL);
		exportsData.horizontalSpan = 3;
		this.exports.setLayoutData(exportsData);
		this.exports.setLowerBound(0);
		this.exports.setUpperBound(-1);
		exports.setID(EweViewsRepository.EmlTask.Properties.exports);
		exports.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createUsesAdvancedTableComposition(Composite parent) {
		this.uses = new ReferencesTable(EweMessages.EmlTaskPropertiesEditionPart_UsesLabel, new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.uses, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				uses.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.uses, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				uses.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.uses, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				uses.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.uses, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				uses.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.usesFilters) {
			this.uses.addFilter(filter);
		}
		this.uses.setHelpText(propertiesEditionComponent.getHelpContent(EweViewsRepository.EmlTask.Properties.uses, EweViewsRepository.SWT_KIND));
		this.uses.createControls(parent);
		this.uses.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.uses, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData usesData = new GridData(GridData.FILL_HORIZONTAL);
		usesData.horizontalSpan = 3;
		this.uses.setLayoutData(usesData);
		this.uses.setLowerBound(0);
		this.uses.setUpperBound(-1);
		uses.setID(EweViewsRepository.EmlTask.Properties.uses);
		uses.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createModelsAdvancedTableComposition(Composite parent) {
		this.models = new ReferencesTable(EweMessages.EmlTaskPropertiesEditionPart_ModelsLabel, new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.models, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				models.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.models, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				models.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.models, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				models.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.models, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				models.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.modelsFilters) {
			this.models.addFilter(filter);
		}
		this.models.setHelpText(propertiesEditionComponent.getHelpContent(EweViewsRepository.EmlTask.Properties.models, EweViewsRepository.SWT_KIND));
		this.models.createControls(parent);
		this.models.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.models, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData modelsData = new GridData(GridData.FILL_HORIZONTAL);
		modelsData.horizontalSpan = 3;
		this.models.setLayoutData(modelsData);
		this.models.setLowerBound(0);
		this.models.setUpperBound(-1);
		models.setID(EweViewsRepository.EmlTask.Properties.models);
		models.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createUseMatchTraceText(Composite parent) {
		SWTUtils.createPartLabel(parent, EweMessages.EmlTaskPropertiesEditionPart_UseMatchTraceLabel, propertiesEditionComponent.isRequired(EweViewsRepository.EmlTask.Properties.useMatchTrace, EweViewsRepository.SWT_KIND));
		useMatchTrace = new Text(parent, SWT.BORDER);
		GridData useMatchTraceData = new GridData(GridData.FILL_HORIZONTAL);
		useMatchTrace.setLayoutData(useMatchTraceData);
		useMatchTrace.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.useMatchTrace, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, useMatchTrace.getText()));
			}

		});
		useMatchTrace.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.useMatchTrace, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, useMatchTrace.getText()));
				}
			}

		});
		EditingUtils.setID(useMatchTrace, EweViewsRepository.EmlTask.Properties.useMatchTrace);
		EditingUtils.setEEFtype(useMatchTrace, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.EmlTask.Properties.useMatchTrace, EweViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createExportTransformationTraceText(Composite parent) {
		SWTUtils.createPartLabel(parent, EweMessages.EmlTaskPropertiesEditionPart_ExportTransformationTraceLabel, propertiesEditionComponent.isRequired(EweViewsRepository.EmlTask.Properties.exportTransformationTrace, EweViewsRepository.SWT_KIND));
		exportTransformationTrace = new Text(parent, SWT.BORDER);
		GridData exportTransformationTraceData = new GridData(GridData.FILL_HORIZONTAL);
		exportTransformationTrace.setLayoutData(exportTransformationTraceData);
		exportTransformationTrace.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.exportTransformationTrace, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, exportTransformationTrace.getText()));
			}

		});
		exportTransformationTrace.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.exportTransformationTrace, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, exportTransformationTrace.getText()));
				}
			}

		});
		EditingUtils.setID(exportTransformationTrace, EweViewsRepository.EmlTask.Properties.exportTransformationTrace);
		EditingUtils.setEEFtype(exportTransformationTrace, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.EmlTask.Properties.exportTransformationTrace, EweViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createExportMergeTraceText(Composite parent) {
		SWTUtils.createPartLabel(parent, EweMessages.EmlTaskPropertiesEditionPart_ExportMergeTraceLabel, propertiesEditionComponent.isRequired(EweViewsRepository.EmlTask.Properties.exportMergeTrace, EweViewsRepository.SWT_KIND));
		exportMergeTrace = new Text(parent, SWT.BORDER);
		GridData exportMergeTraceData = new GridData(GridData.FILL_HORIZONTAL);
		exportMergeTrace.setLayoutData(exportMergeTraceData);
		exportMergeTrace.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.exportMergeTrace, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, exportMergeTrace.getText()));
			}

		});
		exportMergeTrace.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(EmlTaskPropertiesEditionPartImpl.this, EweViewsRepository.EmlTask.Properties.exportMergeTrace, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, exportMergeTrace.getText()));
				}
			}

		});
		EditingUtils.setID(exportMergeTrace, EweViewsRepository.EmlTask.Properties.exportMergeTrace);
		EditingUtils.setEEFtype(exportMergeTrace, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.EmlTask.Properties.exportMergeTrace, EweViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code for tab synchronization
		
		// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#getSrc()
	 * 
	 */
	public String getSrc() {
		return src.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#setSrc(String newValue)
	 * 
	 */
	public void setSrc(String newValue) {
		if (newValue != null) {
			src.setText(newValue);
		} else {
			src.setText(""); //$NON-NLS-1$
		}
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#initExports(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initExports(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		exports.setContentProvider(contentProvider);
		exports.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#updateExports()
	 * 
	 */
	public void updateExports() {
	exports.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#addFilterExports(ViewerFilter filter)
	 * 
	 */
	public void addFilterToExports(ViewerFilter filter) {
		exportsFilters.add(filter);
		if (this.exports != null) {
			this.exports.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#addBusinessFilterExports(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToExports(ViewerFilter filter) {
		exportsBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#isContainedInExportsTable(EObject element)
	 * 
	 */
	public boolean isContainedInExportsTable(EObject element) {
		return ((ReferencesTableSettings)exports.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#initUses(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initUses(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		uses.setContentProvider(contentProvider);
		uses.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#updateUses()
	 * 
	 */
	public void updateUses() {
	uses.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#addFilterUses(ViewerFilter filter)
	 * 
	 */
	public void addFilterToUses(ViewerFilter filter) {
		usesFilters.add(filter);
		if (this.uses != null) {
			this.uses.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#addBusinessFilterUses(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToUses(ViewerFilter filter) {
		usesBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#isContainedInUsesTable(EObject element)
	 * 
	 */
	public boolean isContainedInUsesTable(EObject element) {
		return ((ReferencesTableSettings)uses.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#initModels(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initModels(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		models.setContentProvider(contentProvider);
		models.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#updateModels()
	 * 
	 */
	public void updateModels() {
	models.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#addFilterModels(ViewerFilter filter)
	 * 
	 */
	public void addFilterToModels(ViewerFilter filter) {
		modelsFilters.add(filter);
		if (this.models != null) {
			this.models.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#addBusinessFilterModels(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToModels(ViewerFilter filter) {
		modelsBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#isContainedInModelsTable(EObject element)
	 * 
	 */
	public boolean isContainedInModelsTable(EObject element) {
		return ((ReferencesTableSettings)models.getInput()).contains(element);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#getUseMatchTrace()
	 * 
	 */
	public String getUseMatchTrace() {
		return useMatchTrace.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#setUseMatchTrace(String newValue)
	 * 
	 */
	public void setUseMatchTrace(String newValue) {
		if (newValue != null) {
			useMatchTrace.setText(newValue);
		} else {
			useMatchTrace.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#getExportTransformationTrace()
	 * 
	 */
	public String getExportTransformationTrace() {
		return exportTransformationTrace.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#setExportTransformationTrace(String newValue)
	 * 
	 */
	public void setExportTransformationTrace(String newValue) {
		if (newValue != null) {
			exportTransformationTrace.setText(newValue);
		} else {
			exportTransformationTrace.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#getExportMergeTrace()
	 * 
	 */
	public String getExportMergeTrace() {
		return exportMergeTrace.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.EmlTaskPropertiesEditionPart#setExportMergeTrace(String newValue)
	 * 
	 */
	public void setExportMergeTrace(String newValue) {
		if (newValue != null) {
			exportMergeTrace.setText(newValue);
		} else {
			exportMergeTrace.setText(""); //$NON-NLS-1$
		}
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return EweMessages.EmlTask_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
