package io.github.cafeteriaguild.lin.rt.lib

import io.github.cafeteriaguild.lin.rt.exceptions.LinException
import io.github.cafeteriaguild.lin.rt.lib.nativelang.invoke.LinCall
import io.github.cafeteriaguild.lin.rt.lib.nativelang.properties.ObjProperty
import io.github.cafeteriaguild.lin.rt.lib.nativelang.properties.Property

interface LObj {
    fun properties(): Set<String> {
        return emptySet()
    }

    fun canGet(name: String): Boolean {
        return false
    }

    operator fun get(name: String): LObj {
        throw UnsupportedOperationException()
    }

    fun canSet(name: String): Boolean {
        return false
    }

    operator fun set(name: String, value: LObj) {
        throw UnsupportedOperationException()
    }

    fun canInvoke(): Boolean {
        return (this is LinCall) || canGet("invoke") && get("invoke").canInvoke()
    }

    fun callable(): LinCall {
        if (this is LinCall) return this
        if (!canInvoke()) throw LinException("Object does not support invocation.")
        return get("invoke").callable()
    }

    /**
     * Functions should override this and implement LinCall.
     */
//    operator fun invoke(args: List<LObj> = emptyList()): LObj {
//        if (!canInvoke()) throw LinException("Object does not support invocation.")
//        return callable().invoke(args)
//    }

    fun propertyOf(name: String): Property? = if (properties().contains(name)) ObjProperty(this, name) else null

    fun component(value: Int): LinCall? {
        val property = propertyOf("component$value")
        return if (property == null) {
            null
        } else {
            val componentFn = property.get()
            if (!componentFn.canInvoke()) null
            else componentFn.callable()

        }
    }
}
