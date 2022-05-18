package com.reqres.viewbinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T : ViewBinding>(
    private val fragment: Fragment,
    private val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {
    private var _binding: T? = null

    private lateinit var observer: Observer<LifecycleOwner>

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if (_binding != null) {
            return _binding!!
        }

        validateLifecycleState()
        return createViewBinding(thisRef.requireView())
    }

    private fun validateLifecycleState() = with(fragment.viewLifecycleOwner) {
        require(lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            "Should not attempt to get bindings when Fragment views are destroyed."
        }
    }

    private fun createViewBinding(view: View) = viewBindingFactory(view).also { binding ->
        _binding = binding
        fragment.observeOnDestroyView {
            _binding = null
        }
    }

    private fun Fragment.observeOnDestroyView(action: () -> Unit = {}) {
        observer = Observer<LifecycleOwner> { lifecycleOwner ->
            if (lifecycleOwner == null) {
                action()
                viewLifecycleOwnerLiveData.removeObserver(observer)
            }
        }

        viewLifecycleOwnerLiveData.observeForever(observer)
    }
}

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T
) = FragmentViewBindingDelegate(this, viewBindingFactory)
