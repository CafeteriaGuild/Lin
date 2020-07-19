package io.github.cafeteriaguild.lin.ast.expr.declarations

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.LinModifier
import io.github.cafeteriaguild.lin.ast.expr.AbstractNode
import io.github.cafeteriaguild.lin.ast.expr.Declaration
import io.github.cafeteriaguild.lin.ast.expr.NodeParamVisitor
import io.github.cafeteriaguild.lin.ast.expr.NodeVisitor
import io.github.cafeteriaguild.lin.ast.expr.nodes.FunctionExpr

class DeclareFunctionNode(
    val name: String,
    val function: FunctionExpr,
    section: Section,
    val modifiers: Set<LinModifier> = emptySet()
) : AbstractNode(section), Declaration {
    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}