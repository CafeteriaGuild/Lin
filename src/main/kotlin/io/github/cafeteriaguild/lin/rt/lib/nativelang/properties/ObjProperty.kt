package io.github.cafeteriaguild.lin.rt.lib.nativelang.properties

import io.github.cafeteriaguild.lin.rt.lib.LObj

class ObjProperty(private val obj: LObj, private val name: String) : Property {
    override val getAllowed: Boolean
        get() = obj.canGet(name)
    override val setAllowed: Boolean
        get() = obj.canSet(name)

    override fun get(): LObj {
        return obj[name]
    }

    override fun set(value: LObj) {
        obj[name] = value
    }
}
