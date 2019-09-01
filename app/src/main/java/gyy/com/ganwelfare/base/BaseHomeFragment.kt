package gyy.com.ganwelfare.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import java.lang.reflect.ParameterizedType

/**
 * Fragment的基础类型
 * */
abstract open class BaseHomeFragment<T:BaseViewModel>:Fragment(){

    private val mViewModel by lazy {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType){
            val actualTypeArguments = type.actualTypeArguments
            if (actualTypeArguments.isNotEmpty()) {
                val entitiClass = actualTypeArguments[0] as Class<T>;
                ViewModelProviders.of(this).get(entitiClass)
            }
        }
    }


}