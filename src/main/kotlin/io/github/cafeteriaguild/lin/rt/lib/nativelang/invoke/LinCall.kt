package io.github.cafeteriaguild.lin.rt.lib.nativelang.invoke

import io.github.cafeteriaguild.lin.rt.lib.LObj

interface LinCall {
    operator fun invoke(args: List<LObj> = emptyList()): LObj
}