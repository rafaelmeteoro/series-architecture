package com.example.rafaelfeliciano.seriesarchitecture.ui.base;

import android.os.Bundle;

import org.jetbrains.annotations.Nullable;

public abstract class PresenterIActivity<Presenter extends IPresenter> extends InjectableIActivity {

    public abstract Presenter getPresenter();

    @Override
    @SuppressWarnings("unchecked")
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        if (getPresenter() != null) {
            getPresenter().attach(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().detach();
        }
        super.onDestroy();
    }
}
