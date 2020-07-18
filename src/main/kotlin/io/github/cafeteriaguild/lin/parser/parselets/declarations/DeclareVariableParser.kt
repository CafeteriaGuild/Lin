package io.github.cafeteriaguild.lin.parser.parselets.declarations

import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.SyntaxException
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.Node
import io.github.cafeteriaguild.lin.ast.expr.declarations.DeclareVariableExpr
import io.github.cafeteriaguild.lin.ast.expr.misc.InvalidExpr
import io.github.cafeteriaguild.lin.lexer.TokenType

class DeclareVariableParser(val mutable: Boolean) : PrefixParser<TokenType, Expr> {
    override fun parse(ctx: ParserContext<TokenType, Expr>, token: Token<TokenType>): Expr {
        val ident = ctx.eat(TokenType.IDENTIFIER)

        if (ctx.match(TokenType.ASSIGN)) {
            val last = ctx.last
            val expr = ctx.parseExpression().let {
                it as? Node ?: return InvalidExpr {
                    section(token.section)
                    child(it)
                    error(SyntaxException("Expected a node but got a statement instead.", it.section))
                }
            }
            return DeclareVariableExpr(ident.value, mutable, expr, token.span(last))
        }
        return DeclareVariableExpr(ident.value, mutable, null, token.span(ident))
    }
}