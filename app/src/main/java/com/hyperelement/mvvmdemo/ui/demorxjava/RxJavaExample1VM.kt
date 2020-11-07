//Ref: https://codingwithmitch.com/courses/rxjava-rxandroid-for-beginners/rxjava-operators-create-just-range-repeat/

package com.hyperelement.mvvmdemo.ui.demorxjava

import com.hyperelement.mvvmdemo.data.repository.RxJavaExample1Repository
import com.hyperelement.mvvmdemo.utilities.RootViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber


private const val TAG = "RxJavaExample1VM"

class RxJavaExample1VM(
    private val mRepository: RxJavaExample1Repository
) : RootViewModel() {

    init {
//      createOperator()
//      justOperator()
//      rangeOperator()
        repeatOperator()
    }

    private fun createOperator() {
        val task = Task("This is demo description", false, 1)

        val mSingleTaskObservable = Observable.create<Task> {
            if (!it.isDisposed) {
                it.onNext(task)
                it.onComplete()
            }
        }
            .doOnSubscribe { Schedulers.io() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<Task?> {

                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable?) {}
                    override fun onError(e: Throwable?) {}

                    override fun onNext(t: Task?) {
                        Timber.d("ON NEXT ${t!!.aDescription}")
                    }
                }
            )
    }

    private fun justOperator() {
        Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<String?> {

                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable?) {}
                    override fun onError(e: Throwable?) {}

                    override fun onNext(t: String?) {
                        Timber.d("ON NEXT $t")
                    }
                }
            )
    }

    private fun rangeOperator() {
        Observable.range(0, 11)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Timber.d("OUTPUT $it")
            }
    }

    private fun repeatOperator() {
        Observable.range(0, 5).repeat(2)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Timber.d("OUTPUT $it")
            }
    }

    override fun onCleared() {
        super.onCleared()
    }
}