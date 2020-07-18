package io.github.cafeteriaguild.lin.ast.expr.declarations

import com.github.adriantodt.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.expr.AbstractExpr
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.ExprParamVisitor
import io.github.cafeteriaguild.lin.ast.expr.ExprVisitor

class DeclareVariableExpr(
    val name: String, val mutable: Boolean, val value: Expr?, section: Section
) : AbstractExpr(section) {
    override fun <R> accept(visitor: ExprVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: ExprParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}