package io.github.cafeteriaguild.lin.ast.node.access

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.node.*
import io.github.cafeteriaguild.lin.ast.node.ops.AccessResolver

class SubscriptAccessExpr(
    val target: Expr, val arguments: List<Expr>, section: Section
) : AbstractNode(section), AccessExpr {
    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
    override fun <T, R> resolve(visitor: AccessResolver<T, R>, param: T) = visitor.resolve(this, param)
}