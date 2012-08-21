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

import ewe.parts.EweViewsRepository;
import ewe.parts.NestedElementPropertiesEditionPart;
import ewe.providers.EweMessages;


// End of user code

/**
 * 
 * 
 */
public class NestedElementPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, NestedElementPropertiesEditionPart {

	protected Text name;
protected ReferencesTable attributes;
protected List<ViewerFilter> attributesBusinessFilters = new ArrayList<ViewerFilter>();
protected List<ViewerFilter> attributesFilters = new ArrayList<ViewerFilter>();
protected ReferencesTable taskelements;
protected List<ViewerFilter> taskelementsBusinessFilters = new ArrayList<ViewerFilter>();
protected List<ViewerFilter> taskelementsFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public NestedElementPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence nestedElementStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = nestedElementStep.addStep(EweViewsRepository.NestedElement.Properties.class);
		propertiesStep.addStep(EweViewsRepository.NestedElement.Properties.name);
		propertiesStep.addStep(EweViewsRepository.NestedElement.Properties.attributes);
		propertiesStep.addStep(EweViewsRepository.NestedElement.Properties.taskelements);
		
		
		composer = new PartComposer(nestedElementStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == EweViewsRepository.NestedElement.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == EweViewsRepository.NestedElement.Properties.name) {
					return createNameText(parent);
				}
				if (key == EweViewsRepository.NestedElement.Properties.attributes) {
					return createAttributesAdvancedTableComposition(parent);
				}
				if (key == EweViewsRepository.NestedElement.Properties.taskelements) {
					return createTaskelementsAdvancedTableComposition(parent);
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
		propertiesGroup.setText(EweMessages.NestedElementPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createNameText(Composite parent) {
		SWTUtils.createPartLabel(parent, EweMessages.NestedElementPropertiesEditionPart_NameLabel, propertiesEditionComponent.isRequired(EweViewsRepository.NestedElement.Properties.name, EweViewsRepository.SWT_KIND));
		name = new Text(parent, SWT.BORDER);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {

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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
			}

		});
		name.addKeyListener(new KeyAdapter() {

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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, EweViewsRepository.NestedElement.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.NestedElement.Properties.name, EweViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createAttributesAdvancedTableComposition(Composite parent) {
		this.attributes = new ReferencesTable(EweMessages.NestedElementPropertiesEditionPart_AttributesLabel, new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.attributes, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				attributes.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.attributes, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				attributes.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.attributes, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				attributes.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.attributes, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				attributes.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.attributesFilters) {
			this.attributes.addFilter(filter);
		}
		this.attributes.setHelpText(propertiesEditionComponent.getHelpContent(EweViewsRepository.NestedElement.Properties.attributes, EweViewsRepository.SWT_KIND));
		this.attributes.createControls(parent);
		this.attributes.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.attributes, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData attributesData = new GridData(GridData.FILL_HORIZONTAL);
		attributesData.horizontalSpan = 3;
		this.attributes.setLayoutData(attributesData);
		this.attributes.setLowerBound(0);
		this.attributes.setUpperBound(-1);
		attributes.setID(EweViewsRepository.NestedElement.Properties.attributes);
		attributes.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createTaskelementsAdvancedTableComposition(Composite parent) {
		this.taskelements = new ReferencesTable(EweMessages.NestedElementPropertiesEditionPart_TaskelementsLabel, new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.taskelements, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				taskelements.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.taskelements, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				taskelements.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.taskelements, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				taskelements.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.taskelements, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				taskelements.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.taskelementsFilters) {
			this.taskelements.addFilter(filter);
		}
		this.taskelements.setHelpText(propertiesEditionComponent.getHelpContent(EweViewsRepository.NestedElement.Properties.taskelements, EweViewsRepository.SWT_KIND));
		this.taskelements.createControls(parent);
		this.taskelements.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(NestedElementPropertiesEditionPartImpl.this, EweViewsRepository.NestedElement.Properties.taskelements, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData taskelementsData = new GridData(GridData.FILL_HORIZONTAL);
		taskelementsData.horizontalSpan = 3;
		this.taskelements.setLayoutData(taskelementsData);
		this.taskelements.setLowerBound(0);
		this.taskelements.setUpperBound(-1);
		taskelements.setID(EweViewsRepository.NestedElement.Properties.taskelements);
		taskelements.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
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
	 * @see ewe.parts.NestedElementPropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#setName(String newValue)
	 * 
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#initAttributes(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initAttributes(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		attributes.setContentProvider(contentProvider);
		attributes.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#updateAttributes()
	 * 
	 */
	public void updateAttributes() {
	attributes.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#addFilterAttributes(ViewerFilter filter)
	 * 
	 */
	public void addFilterToAttributes(ViewerFilter filter) {
		attributesFilters.add(filter);
		if (this.attributes != null) {
			this.attributes.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#addBusinessFilterAttributes(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToAttributes(ViewerFilter filter) {
		attributesBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#isContainedInAttributesTable(EObject element)
	 * 
	 */
	public boolean isContainedInAttributesTable(EObject element) {
		return ((ReferencesTableSettings)attributes.getInput()).contains(element);
	}




	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#initTaskelements(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initTaskelements(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		taskelements.setContentProvider(contentProvider);
		taskelements.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#updateTaskelements()
	 * 
	 */
	public void updateTaskelements() {
	taskelements.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#addFilterTaskelements(ViewerFilter filter)
	 * 
	 */
	public void addFilterToTaskelements(ViewerFilter filter) {
		taskelementsFilters.add(filter);
		if (this.taskelements != null) {
			this.taskelements.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#addBusinessFilterTaskelements(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToTaskelements(ViewerFilter filter) {
		taskelementsBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.NestedElementPropertiesEditionPart#isContainedInTaskelementsTable(EObject element)
	 * 
	 */
	public boolean isContainedInTaskelementsTable(EObject element) {
		return ((ReferencesTableSettings)taskelements.getInput()).contains(element);
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return EweMessages.NestedElement_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
