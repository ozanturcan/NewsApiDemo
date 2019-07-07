package com.boyner.news.view

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.boyner.news.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber


open class BaseFragment : Fragment() {

    private val subscriptions = CompositeDisposable()

    fun subscribe(disposable: Disposable): Disposable {
        subscriptions.add(disposable)
        return disposable
    }


    fun changeFragmentWithBundle(fragment: Fragment, bundle: Bundle) {
        fragment.arguments = bundle
        fragmentManager!!.beginTransaction()
                .replace(R.id.frag_container, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }


    /* ${error.let { it.message }}*/
    fun showError(string: String, @Nullable error: Throwable? = null) {
        if (error != null) {
            Toast.makeText(context, "Error : $string on ${this.javaClass.name}", Toast.LENGTH_SHORT).show()
        }
        Timber.e("Error : $string and ${error?.message} on ${this.javaClass.name}")

    }
}
