package com.example.rafaelfeliciano.seriesarchitecture.ui.base;

import android.os.Bundle;
import android.view.View;

import org.jetbrains.annotations.Nullable;

public abstract class PresenterIFragment<Presenter extends IPresenter> extends InjectableIFragment {

    public abstract Presenter getPresenter();

    @Override
    @SuppressWarnings("unchecked")
    protected void init(@Nullable Bundle savedInstanceState, @Nullable View view) {
        super.init(savedInstanceState, view);
        if (getPresenter() != null) {
            getPresenter().attach(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (getPresenter() != null) {
            getPresenter().detach();
        }
        super.onDestroyView();
    }
}
