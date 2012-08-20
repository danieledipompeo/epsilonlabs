/**
 * Generated with Acceleo
 */
package ewe.parts.impl;

// Start of user code for imports
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
import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import ewe.parts.EweViewsRepository;
import ewe.parts.ExportsNestedElementPropertiesEditionPart;
import ewe.providers.EweMessages;


// End of user code

/**
 * 
 * 
 */
public class ExportsNestedElementPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ExportsNestedElementPropertiesEditionPart {

	protected Text ref;
	protected Text as;
	protected Text optional;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ExportsNestedElementPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
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
		CompositionSequence exportsNestedElementStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = exportsNestedElementStep.addStep(EweViewsRepository.ExportsNestedElement.Properties.class);
		propertiesStep.addStep(EweViewsRepository.ExportsNestedElement.Properties.ref);
		propertiesStep.addStep(EweViewsRepository.ExportsNestedElement.Properties.as);
		propertiesStep.addStep(EweViewsRepository.ExportsNestedElement.Properties.optional);
		
		
		composer = new PartComposer(exportsNestedElementStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == EweViewsRepository.ExportsNestedElement.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == EweViewsRepository.ExportsNestedElement.Properties.ref) {
					return createRefText(parent);
				}
				if (key == EweViewsRepository.ExportsNestedElement.Properties.as) {
					return createAsText(parent);
				}
				if (key == EweViewsRepository.ExportsNestedElement.Properties.optional) {
					return createOptionalText(parent);
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
		propertiesGroup.setText(EweMessages.ExportsNestedElementPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createRefText(Composite parent) {
		SWTUtils.createPartLabel(parent, EweMessages.ExportsNestedElementPropertiesEditionPart_RefLabel, propertiesEditionComponent.isRequired(EweViewsRepository.ExportsNestedElement.Properties.ref, EweViewsRepository.SWT_KIND));
		ref = new Text(parent, SWT.BORDER);
		GridData refData = new GridData(GridData.FILL_HORIZONTAL);
		ref.setLayoutData(refData);
		ref.addFocusListener(new FocusAdapter() {

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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExportsNestedElementPropertiesEditionPartImpl.this, EweViewsRepository.ExportsNestedElement.Properties.ref, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, ref.getText()));
			}

		});
		ref.addKeyListener(new KeyAdapter() {

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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExportsNestedElementPropertiesEditionPartImpl.this, EweViewsRepository.ExportsNestedElement.Properties.ref, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, ref.getText()));
				}
			}

		});
		EditingUtils.setID(ref, EweViewsRepository.ExportsNestedElement.Properties.ref);
		EditingUtils.setEEFtype(ref, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.ExportsNestedElement.Properties.ref, EweViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createAsText(Composite parent) {
		SWTUtils.createPartLabel(parent, EweMessages.ExportsNestedElementPropertiesEditionPart_AsLabel, propertiesEditionComponent.isRequired(EweViewsRepository.ExportsNestedElement.Properties.as, EweViewsRepository.SWT_KIND));
		as = new Text(parent, SWT.BORDER);
		GridData asData = new GridData(GridData.FILL_HORIZONTAL);
		as.setLayoutData(asData);
		as.addFocusListener(new FocusAdapter() {

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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExportsNestedElementPropertiesEditionPartImpl.this, EweViewsRepository.ExportsNestedElement.Properties.as, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, as.getText()));
			}

		});
		as.addKeyListener(new KeyAdapter() {

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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExportsNestedElementPropertiesEditionPartImpl.this, EweViewsRepository.ExportsNestedElement.Properties.as, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, as.getText()));
				}
			}

		});
		EditingUtils.setID(as, EweViewsRepository.ExportsNestedElement.Properties.as);
		EditingUtils.setEEFtype(as, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.ExportsNestedElement.Properties.as, EweViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createOptionalText(Composite parent) {
		SWTUtils.createPartLabel(parent, EweMessages.ExportsNestedElementPropertiesEditionPart_OptionalLabel, propertiesEditionComponent.isRequired(EweViewsRepository.ExportsNestedElement.Properties.optional, EweViewsRepository.SWT_KIND));
		optional = new Text(parent, SWT.BORDER);
		GridData optionalData = new GridData(GridData.FILL_HORIZONTAL);
		optional.setLayoutData(optionalData);
		optional.addFocusListener(new FocusAdapter() {

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
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExportsNestedElementPropertiesEditionPartImpl.this, EweViewsRepository.ExportsNestedElement.Properties.optional, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, optional.getText()));
			}

		});
		optional.addKeyListener(new KeyAdapter() {

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
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ExportsNestedElementPropertiesEditionPartImpl.this, EweViewsRepository.ExportsNestedElement.Properties.optional, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, optional.getText()));
				}
			}

		});
		EditingUtils.setID(optional, EweViewsRepository.ExportsNestedElement.Properties.optional);
		EditingUtils.setEEFtype(optional, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.ExportsNestedElement.Properties.optional, EweViewsRepository.SWT_KIND), null); //$NON-NLS-1$
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
	 * @see ewe.parts.ExportsNestedElementPropertiesEditionPart#getRef()
	 * 
	 */
	public String getRef() {
		return ref.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.ExportsNestedElementPropertiesEditionPart#setRef(String newValue)
	 * 
	 */
	public void setRef(String newValue) {
		if (newValue != null) {
			ref.setText(newValue);
		} else {
			ref.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.ExportsNestedElementPropertiesEditionPart#getAs()
	 * 
	 */
	public String getAs() {
		return as.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.ExportsNestedElementPropertiesEditionPart#setAs(String newValue)
	 * 
	 */
	public void setAs(String newValue) {
		if (newValue != null) {
			as.setText(newValue);
		} else {
			as.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.ExportsNestedElementPropertiesEditionPart#getOptional()
	 * 
	 */
	public String getOptional() {
		return optional.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.ExportsNestedElementPropertiesEditionPart#setOptional(String newValue)
	 * 
	 */
	public void setOptional(String newValue) {
		if (newValue != null) {
			optional.setText(newValue);
		} else {
			optional.setText(""); //$NON-NLS-1$
		}
	}







	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return EweMessages.ExportsNestedElement_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
