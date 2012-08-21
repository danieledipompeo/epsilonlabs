/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ewe.provider;

import ewe.util.EweAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EweItemProviderAdapterFactory extends EweAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable
{
  /**
   * This keeps track of the root adapter factory that delegates to this adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection<Object> supportedTypes = new ArrayList<Object>();

  /**
   * This constructs an instance.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EweItemProviderAdapterFactory()
  {
    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(IItemPropertySource.class);
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.Project} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProjectItemProvider projectItemProvider;

  /**
   * This creates an adapter for a {@link ewe.Project}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createProjectAdapter()
  {
    if (projectItemProvider == null)
    {
      projectItemProvider = new ProjectItemProvider(this);
    }

    return projectItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.Task} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TaskItemProvider taskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.Task}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createTaskAdapter()
  {
    if (taskItemProvider == null)
    {
      taskItemProvider = new TaskItemProvider(this);
    }

    return taskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.LoadModelTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LoadModelTaskItemProvider loadModelTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.LoadModelTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createLoadModelTaskAdapter()
  {
    if (loadModelTaskItemProvider == null)
    {
      loadModelTaskItemProvider = new LoadModelTaskItemProvider(this);
    }

    return loadModelTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.StoreModelTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StoreModelTaskItemProvider storeModelTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.StoreModelTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createStoreModelTaskAdapter()
  {
    if (storeModelTaskItemProvider == null)
    {
      storeModelTaskItemProvider = new StoreModelTaskItemProvider(this);
    }

    return storeModelTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.ParameterNestedElement} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ParameterNestedElementItemProvider parameterNestedElementItemProvider;

  /**
   * This creates an adapter for a {@link ewe.ParameterNestedElement}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createParameterNestedElementAdapter()
  {
    if (parameterNestedElementItemProvider == null)
    {
      parameterNestedElementItemProvider = new ParameterNestedElementItemProvider(this);
    }

    return parameterNestedElementItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.VariableNestedElement} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VariableNestedElementItemProvider variableNestedElementItemProvider;

  /**
   * This creates an adapter for a {@link ewe.VariableNestedElement}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createVariableNestedElementAdapter()
  {
    if (variableNestedElementItemProvider == null)
    {
      variableNestedElementItemProvider = new VariableNestedElementItemProvider(this);
    }

    return variableNestedElementItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.ExportsNestedElement} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExportsNestedElementItemProvider exportsNestedElementItemProvider;

  /**
   * This creates an adapter for a {@link ewe.ExportsNestedElement}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createExportsNestedElementAdapter()
  {
    if (exportsNestedElementItemProvider == null)
    {
      exportsNestedElementItemProvider = new ExportsNestedElementItemProvider(this);
    }

    return exportsNestedElementItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.UsesNestedElement} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UsesNestedElementItemProvider usesNestedElementItemProvider;

  /**
   * This creates an adapter for a {@link ewe.UsesNestedElement}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createUsesNestedElementAdapter()
  {
    if (usesNestedElementItemProvider == null)
    {
      usesNestedElementItemProvider = new UsesNestedElementItemProvider(this);
    }

    return usesNestedElementItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.ModelNestedElement} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelNestedElementItemProvider modelNestedElementItemProvider;

  /**
   * This creates an adapter for a {@link ewe.ModelNestedElement}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createModelNestedElementAdapter()
  {
    if (modelNestedElementItemProvider == null)
    {
      modelNestedElementItemProvider = new ModelNestedElementItemProvider(this);
    }

    return modelNestedElementItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.RollbackTransactionTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RollbackTransactionTaskItemProvider rollbackTransactionTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.RollbackTransactionTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createRollbackTransactionTaskAdapter()
  {
    if (rollbackTransactionTaskItemProvider == null)
    {
      rollbackTransactionTaskItemProvider = new RollbackTransactionTaskItemProvider(this);
    }

    return rollbackTransactionTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.DisposeModelTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DisposeModelTaskItemProvider disposeModelTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.DisposeModelTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createDisposeModelTaskAdapter()
  {
    if (disposeModelTaskItemProvider == null)
    {
      disposeModelTaskItemProvider = new DisposeModelTaskItemProvider(this);
    }

    return disposeModelTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.StartTransactionTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StartTransactionTaskItemProvider startTransactionTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.StartTransactionTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createStartTransactionTaskAdapter()
  {
    if (startTransactionTaskItemProvider == null)
    {
      startTransactionTaskItemProvider = new StartTransactionTaskItemProvider(this);
    }

    return startTransactionTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.EolTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EolTaskItemProvider eolTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.EolTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createEolTaskAdapter()
  {
    if (eolTaskItemProvider == null)
    {
      eolTaskItemProvider = new EolTaskItemProvider(this);
    }

    return eolTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.EmlTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EmlTaskItemProvider emlTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.EmlTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createEmlTaskAdapter()
  {
    if (emlTaskItemProvider == null)
    {
      emlTaskItemProvider = new EmlTaskItemProvider(this);
    }

    return emlTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.EglTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EglTaskItemProvider eglTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.EglTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createEglTaskAdapter()
  {
    if (eglTaskItemProvider == null)
    {
      eglTaskItemProvider = new EglTaskItemProvider(this);
    }

    return eglTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.EtlTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EtlTaskItemProvider etlTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.EtlTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createEtlTaskAdapter()
  {
    if (etlTaskItemProvider == null)
    {
      etlTaskItemProvider = new EtlTaskItemProvider(this);
    }

    return etlTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.EvlTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EvlTaskItemProvider evlTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.EvlTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createEvlTaskAdapter()
  {
    if (evlTaskItemProvider == null)
    {
      evlTaskItemProvider = new EvlTaskItemProvider(this);
    }

    return evlTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.CommitTransactionTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CommitTransactionTaskItemProvider commitTransactionTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.CommitTransactionTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createCommitTransactionTaskAdapter()
  {
    if (commitTransactionTaskItemProvider == null)
    {
      commitTransactionTaskItemProvider = new CommitTransactionTaskItemProvider(this);
    }

    return commitTransactionTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.Property} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PropertyItemProvider propertyItemProvider;

  /**
   * This creates an adapter for a {@link ewe.Property}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createPropertyAdapter()
  {
    if (propertyItemProvider == null)
    {
      propertyItemProvider = new PropertyItemProvider(this);
    }

    return propertyItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.Target} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TargetItemProvider targetItemProvider;

  /**
   * This creates an adapter for a {@link ewe.Target}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createTargetAdapter()
  {
    if (targetItemProvider == null)
    {
      targetItemProvider = new TargetItemProvider(this);
    }

    return targetItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.LoadEMFModelTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LoadEMFModelTaskItemProvider loadEMFModelTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.LoadEMFModelTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createLoadEMFModelTaskAdapter()
  {
    if (loadEMFModelTaskItemProvider == null)
    {
      loadEMFModelTaskItemProvider = new LoadEMFModelTaskItemProvider(this);
    }

    return loadEMFModelTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.Attribute} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AttributeItemProvider attributeItemProvider;

  /**
   * This creates an adapter for a {@link ewe.Attribute}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createAttributeAdapter()
  {
    if (attributeItemProvider == null)
    {
      attributeItemProvider = new AttributeItemProvider(this);
    }

    return attributeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.LoadXMLModelTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LoadXMLModelTaskItemProvider loadXMLModelTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.LoadXMLModelTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createLoadXMLModelTaskAdapter()
  {
    if (loadXMLModelTaskItemProvider == null)
    {
      loadXMLModelTaskItemProvider = new LoadXMLModelTaskItemProvider(this);
    }

    return loadXMLModelTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.NestedElement} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NestedElementItemProvider nestedElementItemProvider;

  /**
   * This creates an adapter for a {@link ewe.NestedElement}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createNestedElementAdapter()
  {
    if (nestedElementItemProvider == null)
    {
      nestedElementItemProvider = new NestedElementItemProvider(this);
    }

    return nestedElementItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.FlockTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FlockTaskItemProvider flockTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.FlockTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createFlockTaskAdapter()
  {
    if (flockTaskItemProvider == null)
    {
      flockTaskItemProvider = new FlockTaskItemProvider(this);
    }

    return flockTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.EUnitTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EUnitTaskItemProvider eUnitTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.EUnitTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createEUnitTaskAdapter()
  {
    if (eUnitTaskItemProvider == null)
    {
      eUnitTaskItemProvider = new EUnitTaskItemProvider(this);
    }

    return eUnitTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link ewe.EclTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EclTaskItemProvider eclTaskItemProvider;

  /**
   * This creates an adapter for a {@link ewe.EclTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createEclTaskAdapter()
  {
    if (eclTaskItemProvider == null)
    {
      eclTaskItemProvider = new EclTaskItemProvider(this);
    }

    return eclTaskItemProvider;
  }

  /**
   * This returns the root adapter factory that contains this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

  /**
   * This sets the composed adapter factory that contains this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
    this.parentAdapterFactory = parentAdapterFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object type)
  {
    return supportedTypes.contains(type) || super.isFactoryForType(type);
  }

  /**
   * This implementation substitutes the factory itself as the key for the adapter.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter adapt(Notifier notifier, Object type)
  {
    return super.adapt(notifier, this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object adapt(Object object, Object type)
  {
    if (isFactoryForType(type))
    {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter)))
      {
        return adapter;
      }
    }

    return null;
  }

  /**
   * This adds a listener.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.addListener(notifyChangedListener);
  }

  /**
   * This removes a listener.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.removeListener(notifyChangedListener);
  }

  /**
   * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void fireNotifyChanged(Notification notification)
  {
    changeNotifier.fireNotifyChanged(notification);

    if (parentAdapterFactory != null)
    {
      parentAdapterFactory.fireNotifyChanged(notification);
    }
  }

  /**
   * This disposes all of the item providers created by this factory. 
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void dispose()
  {
    if (projectItemProvider != null) projectItemProvider.dispose();
    if (taskItemProvider != null) taskItemProvider.dispose();
    if (loadModelTaskItemProvider != null) loadModelTaskItemProvider.dispose();
    if (storeModelTaskItemProvider != null) storeModelTaskItemProvider.dispose();
    if (parameterNestedElementItemProvider != null) parameterNestedElementItemProvider.dispose();
    if (variableNestedElementItemProvider != null) variableNestedElementItemProvider.dispose();
    if (exportsNestedElementItemProvider != null) exportsNestedElementItemProvider.dispose();
    if (usesNestedElementItemProvider != null) usesNestedElementItemProvider.dispose();
    if (modelNestedElementItemProvider != null) modelNestedElementItemProvider.dispose();
    if (rollbackTransactionTaskItemProvider != null) rollbackTransactionTaskItemProvider.dispose();
    if (disposeModelTaskItemProvider != null) disposeModelTaskItemProvider.dispose();
    if (startTransactionTaskItemProvider != null) startTransactionTaskItemProvider.dispose();
    if (eolTaskItemProvider != null) eolTaskItemProvider.dispose();
    if (emlTaskItemProvider != null) emlTaskItemProvider.dispose();
    if (eglTaskItemProvider != null) eglTaskItemProvider.dispose();
    if (etlTaskItemProvider != null) etlTaskItemProvider.dispose();
    if (evlTaskItemProvider != null) evlTaskItemProvider.dispose();
    if (commitTransactionTaskItemProvider != null) commitTransactionTaskItemProvider.dispose();
    if (propertyItemProvider != null) propertyItemProvider.dispose();
    if (targetItemProvider != null) targetItemProvider.dispose();
    if (loadEMFModelTaskItemProvider != null) loadEMFModelTaskItemProvider.dispose();
    if (attributeItemProvider != null) attributeItemProvider.dispose();
    if (loadXMLModelTaskItemProvider != null) loadXMLModelTaskItemProvider.dispose();
    if (nestedElementItemProvider != null) nestedElementItemProvider.dispose();
    if (flockTaskItemProvider != null) flockTaskItemProvider.dispose();
    if (eUnitTaskItemProvider != null) eUnitTaskItemProvider.dispose();
    if (eclTaskItemProvider != null) eclTaskItemProvider.dispose();
  }

}