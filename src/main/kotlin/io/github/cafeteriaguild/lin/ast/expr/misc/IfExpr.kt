package io.github.cafeteriaguild.lin.ast.expr.misc

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.expr.*

class IfExpr(
    val condition: Expr,
    val thenBranch: Node,
    val elseBranch: Node?,
    section: Section
) : AbstractNode(section) {
    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}
