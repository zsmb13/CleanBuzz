package co.zsmb.example.cleanbuzz.presentation.base

import android.content.Context
import android.content.Intent
import kotlin.reflect.KClass

class Navigator(val context: Context) {

    private fun <T : NavigableView> createIntent(view: KClass<T>): Intent {
        val intent = Intent(context, view.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        return intent
    }

    fun <T : NavigableView> goto(view: KClass<T>) {
        val intent = createIntent(view)
        context.startActivity(intent)
    }

    fun <T : NavigableView> goto(view: KClass<T>, extra: Pair<String, Int>) {
        val intent = createIntent(view)
        intent.putExtra(extra.first, extra.second)
        context.startActivity(intent)
    }

    fun <T : NavigableView> goto(view: KClass<T>, vararg extras: Pair<String, Int>) {
        val intent = createIntent(view)
        extras.forEach {
            intent.putExtra(it.first, it.second)
        }
        context.startActivity(intent)
    }

}
