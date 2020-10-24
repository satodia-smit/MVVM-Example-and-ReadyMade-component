package com.hyperelement.mvvmdemo.arch

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

//Ref: https://android.jlelse.eu/simplifying-recycler-view-with-epoxy-in-kotlin-nachos-tutorial-series-946d22116d57
abstract class KotlinHolder : EpoxyHolder() {

    private lateinit var view: View

    override fun bindView(itemView: View) {
        view = itemView
    }

    protected fun <V : View> bind(id: Int): ReadOnlyProperty<KotlinHolder, V> =
        Lazy { holder: KotlinHolder, prop ->
            holder.view.findViewById(id) as V?
                ?: throw IllegalStateException("View ID $id for '${prop.name}' not found.")
        }

    //Ref: https://github.com/JakeWharton/kotterknife
    private class Lazy<V>(private val initializer: (KotlinHolder, KProperty<*>) -> V) :
        ReadOnlyProperty<KotlinHolder, V> {

        private object EMPTY

        private var value: Any? = EMPTY

        override fun getValue(thisRef: KotlinHolder, property: KProperty<*>): V {
            if (value == EMPTY) {
                value = initializer(thisRef, property)
            }
            @Suppress("UNCHECKED_CAST")
            return value as V
        }
    }
}
