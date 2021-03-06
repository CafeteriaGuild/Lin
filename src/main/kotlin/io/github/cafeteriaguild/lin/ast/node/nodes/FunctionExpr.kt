package io.github.cafeteriaguild.lin.ast.node.nodes

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.node.*

class FunctionExpr(
    val parameters: List<Parameter>,
    val body: Node?,
    section: Section
) : AbstractNode(section), Expr {
    data class Parameter(
        val name: String,
        val varargs: Boolean,
        val value: Expr?
    )

    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}