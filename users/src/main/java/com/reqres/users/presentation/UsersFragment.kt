package com.reqres.users.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.reqres.component.ImageLoader
import com.reqres.recyclerview.RecyclerViewOnItemClickListener
import com.reqres.users.R
import com.reqres.users.databinding.FragmentUsersBinding
import com.reqres.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users) {

    @Inject
    lateinit var imageLoader: ImageLoader

    private val binding: FragmentUsersBinding by viewBinding(FragmentUsersBinding::bind)

    private val viewModel: UsersViewModel by viewModels()

    private val usersAdapter by lazy {
        UsersAdapter(imageLoader, onItemClickListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareUI()
        observeState()
    }

    private fun prepareUI() {
        with(binding) {
            rvUsers.apply {
                adapter = usersAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        this.context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }
        viewModel.getUsers(1)
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            render(state)
        }
    }

    private fun render(state: UsersViewState) {
        when (state) {
            is UsersViewState.Completed -> renderCompletedState(state)
            is UsersViewState.Error -> renderErrorState(state)
            UsersViewState.Idle -> Unit
            UsersViewState.Loading -> renderLoadingState(true)
        }
    }

    private fun renderLoadingState(isLoading: Boolean) {
        with(binding) {
            cpiLoader.isGone = !isLoading
        }
    }

    private fun renderErrorState(state: UsersViewState.Error) {
        renderLoadingState(false)
        Timber.e(state.exception)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.dialog_error_title))
            .setMessage(state.exception.localizedMessage)
            .setPositiveButton(resources.getString(R.string.dialog_error_ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun renderCompletedState(state: UsersViewState.Completed) {
        renderLoadingState(false)
        if (state.users.isNotEmpty()) {
            usersAdapter.users = state.users
        }
    }

    private val onItemClickListener = object :
        RecyclerViewOnItemClickListener<UserModel> {
        override fun onClick(model: UserModel) {
            Toast.makeText(requireContext(), model.fullName, Toast.LENGTH_SHORT).show()
        }
    }
}