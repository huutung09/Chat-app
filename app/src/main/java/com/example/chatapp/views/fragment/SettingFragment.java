package com.example.chatapp.views.fragment;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.Observer;

import com.example.chatapp.Constants;
import com.example.chatapp.R;
import com.example.chatapp.databinding.SettingFragmentBinding;
import com.example.chatapp.viewmodel.SettingViewModel;
import com.example.chatapp.views.act.MainActivity;

public class SettingFragment extends BaseFragment<SettingFragmentBinding, SettingViewModel> {
    @Override
    protected Class<SettingViewModel> getViewModelClass() {
        return SettingViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.setting_fragment;
    }

    @Override
    protected void initViews() {
        binding.signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignOut();
            }
        });

        mViewModel.getLoggedOut().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    gotoSignInFragment();
                }
            }
        });
    }

    private void gotoSignInFragment(){
        callBack.callBack(Constants.KEY_SHOW_SIGN_IN, null);
    }

    private void handleSignOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialog);
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.sign_out_dialog, null);
        builder.setView(v);
        AlertDialog dialog = builder.create();
        v.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        v.findViewById(R.id.oke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.signOut();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
