package io.github.cafeteriaguild.lin.ast.node.ops

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.node.*

class PostfixAssignUnaryOperation(
    val target: AccessExpr,
    val operator: UnaryAssignOperationType,
    section: Section
) : AbstractNode(section), Expr {
    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}