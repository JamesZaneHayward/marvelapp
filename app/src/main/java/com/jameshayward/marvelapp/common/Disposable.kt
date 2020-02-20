package com.jameshayward.marvelapp.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(disposableComposite: CompositeDisposable) {
    disposableComposite.add(this)
}
