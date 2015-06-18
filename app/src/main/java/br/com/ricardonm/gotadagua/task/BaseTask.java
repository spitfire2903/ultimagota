package br.com.ricardonm.gotadagua.task;

import java.lang.ref.WeakReference;

import br.com.ricardonm.gotadagua.BaseFragment;
import br.com.ricardonm.gotadagua.MainActivity;

/**
 * Created by ricardomiranda on 17/06/15.
 */
/**
 * Base asynchronous thread pool manager.<br />
 * Overriding only {@link BaseTask#onPreExecute() onPreExecute},
 * {@link BaseTask#doInBackground(Object...) doInBackground} and
 * {@link BaseTask#onPostExecute(Object) onPostExecute} is enough for most uses.
 *
 * @param <T>
 *              Parent type.
 * @param <A>
 *              Data type for the <code>doInBackground</code> method.
 * @param <B>
 *              Data type for the <code>onProgressUpdate</code> method.
 * @param <C>
 *              Data type for the <code>onPostExecute</code> method.
 */
public class BaseTask<T, A, B, C> extends android.os.AsyncTask<A, B, C> {

    // ---------------------------------------------------------------------------------------------
    // Instance attributes
    // ---------------------------------------------------------------------------------------------

    /**
     * Parent reference.
     */
    private WeakReference<T> mParent = null;

    // ---------------------------------------------------------------------------------------------
    // Public methods
    // ---------------------------------------------------------------------------------------------

    /**
     * Constructor.
     * Creates an asynchronous task bound to a parent.
     *
     * @param parent
     *              Parent reference.
     */
    public BaseTask(T parent) {
        this.mParent = new WeakReference<T>(parent);
    }

    /**
     * Returns the parent of the current task.
     *
     * @return Parent of the current task.
     */
    public final T getParent() {
        // Local variables
        T parent = null;

        if (this.mParent != null) {
            parent = this.mParent.get();
        }

        return parent;
    }

    /**
     * Unbinds the task from it's parent.
     */
    public final void unbind() {
        if (this.mParent != null) {
            this.mParent.clear();
            this.mParent = null;
        }
    }

    /**
     * Binds the task to a parent.
     *
     * @param parent
     *              Parent to bind the task.
     */
    public final void bind(T parent) {
        this.unbind();
        this.mParent = new WeakReference<T>(parent);
    }

    /**
     * Checks if the task is bound.
     *
     * @return Whether the task is bound.
     */
    public final boolean isBound() {
        return this.mParent != null && this.mParent.get() != null && !this.isCancelled();
    }

    // ---------------------------------------------------------------------------------------------
    // Protected methods
    // ---------------------------------------------------------------------------------------------

    /* (non-Javadoc)
     * @see android.os.AsyncTask#onCancelled()
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
        this.unbind();
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#onCancelled(java.lang.Object)
     */
    @Override
    protected void onCancelled(C result) {
        super.onCancelled(result);
        this.unbind();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (getCurrentActivity() != null){
            getCurrentActivity().showThrobber();
        }
    }

    /**
     * <b>Should always be called at the end of this method's overrides.</b>
     * Runs on the UI thread and receives the result from
     * {@link #doInBackground(A...) doInBackground}. At the end, unbinds the task from its
     * parent.<br />
     */
    @Override
    protected void onPostExecute(C result) {
        super.onPostExecute(result);

        if (getCurrentActivity() != null){
            getCurrentActivity().hideThrobber();
        }

        this.unbind();
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    @Override
    protected C doInBackground(A... params) {
        return null;
    }

    private MainActivity getCurrentActivity(){
        BaseFragment fragment = null;
        MainActivity activity = null;

        if (getParent() instanceof BaseFragment){
            fragment = (BaseFragment) getParent();
            activity = fragment.getParentActivity();
        } else if(getParent() instanceof MainActivity){
            activity = (MainActivity) getParent();
        }

        return activity;
    }

}