package io.github.cafeteriaguild.lin.ast.node.declarations

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.LinModifier
import io.github.cafeteriaguild.lin.ast.node.AbstractNode
import io.github.cafeteriaguild.lin.ast.node.Declaration
import io.github.cafeteriaguild.lin.ast.node.NodeParamVisitor
import io.github.cafeteriaguild.lin.ast.node.NodeVisitor
import io.github.cafeteriaguild.lin.ast.node.nodes.FunctionExpr

class DeclareFunctionNode(
    val name: String,
    val function: FunctionExpr,
    section: Section,
    val modifiers: Set<LinModifier> = emptySet()
) : AbstractNode(section), Declaration {
    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}