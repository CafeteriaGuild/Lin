package io.github.cafeteriaguild.lin.ast.node.misc

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.node.*

class IfNode(
    val condition: Expr,
    val thenBranch: Node,
    val elseBranch: Node?,
    section: Section
) : AbstractNode(section) {
    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}
