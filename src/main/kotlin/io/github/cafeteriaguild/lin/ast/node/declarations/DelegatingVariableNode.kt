package io.github.cafeteriaguild.lin.ast.node.declarations

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.LinModifier
import io.github.cafeteriaguild.lin.ast.node.*

class DelegatingVariableNode(
    val name: String,
    val mutable: Boolean,
    val delegate: Expr,
    section: Section,
    val modifiers: Set<LinModifier> = emptySet()
) : AbstractNode(section), Declaration {
    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}