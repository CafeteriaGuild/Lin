package io.github.cafeteriaguild.lin.ast.expr.access

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.expr.AbstractNode
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.NodeParamVisitor
import io.github.cafeteriaguild.lin.ast.expr.NodeVisitor

class PropertyAssignNode(
    val target: Expr, val nullSafe: Boolean, val name: String, val value: Expr, section: Section
) : AbstractNode(section) {
    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}