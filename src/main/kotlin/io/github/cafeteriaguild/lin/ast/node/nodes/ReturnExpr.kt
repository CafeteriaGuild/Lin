package io.github.cafeteriaguild.lin.ast.node.nodes

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.node.AbstractNode
import io.github.cafeteriaguild.lin.ast.node.Expr
import io.github.cafeteriaguild.lin.ast.node.NodeParamVisitor
import io.github.cafeteriaguild.lin.ast.node.NodeVisitor

class ReturnExpr(val value: Expr, section: Section) : AbstractNode(section), Expr {
    // NOTE by AdrianTodt:
    // "return" and "throw" is a Node, not an Expr.
    // You can val a = return b

    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}