package xyz.avarel.lobos.ast.expr.declarations

import xyz.avarel.lobos.ast.expr.AbstractExpr
import xyz.avarel.lobos.ast.expr.Expr
import xyz.avarel.lobos.ast.expr.ExprVisitor
import xyz.avarel.lobos.ast.types.ArgumentParameterAST
import xyz.avarel.lobos.ast.types.GenericParameterAST
import xyz.avarel.lobos.ast.types.TypeAST
import xyz.avarel.lobos.lexer.Section

class DeclareFunctionExpr(
    val name: String,
    val generics: List<GenericParameterAST>,
    val arguments: List<ArgumentParameterAST>,
    val returnType: TypeAST,
    val body: Expr,
    section: Section
) : AbstractExpr(section), FunctionExpr {
    override fun <R> accept(visitor: ExprVisitor<R>) = visitor.visit(this)
}