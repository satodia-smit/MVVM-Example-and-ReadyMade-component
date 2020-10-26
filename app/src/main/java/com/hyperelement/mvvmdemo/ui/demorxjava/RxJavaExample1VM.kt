package com.hyperelement.mvvmdemo.ui.demorxjava

import com.hyperelement.mvvmdemo.data.repository.RxJavaExample1Repository
import com.hyperelement.mvvmdemo.utilities.RootViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

private const val TAG = "RxJavaExample1VM"

class RxJavaExample1VM(
    private val mRepository: RxJavaExample1Repository
) : RootViewModel() {

    init {
        rxJAVA()
    }

    private fun loadData() = mRepository.generateData()
    private val mDisposable = CompositeDisposable()
    fun rxJAVA() {
        val taskObservable: Observable<Task> =
            Observable.fromIterable(loadData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        taskObservable.subscribe(object :
            Observer<Task?> {
            override fun onSubscribe(d: Disposable?) {
                mDisposable.add(d)
            }

            override fun onError(e: Throwable?) {}
            override fun onComplete() {}
            override fun onNext(t: Task?) {
                Timber.d("onNext: : " + t?.aDescription)
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
        mDisposable.dispose()
    }
}