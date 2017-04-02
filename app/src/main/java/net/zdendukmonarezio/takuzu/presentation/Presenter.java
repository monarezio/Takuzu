package net.zdendukmonarezio.takuzu.presentation;

import nucleus.presenter.RxPresenter;
import rx.Observable;

public class Presenter<E> extends RxPresenter<E> {
    public Observable<E> viewIfExists() {
        return view().filter(view -> view != null);
    }
}