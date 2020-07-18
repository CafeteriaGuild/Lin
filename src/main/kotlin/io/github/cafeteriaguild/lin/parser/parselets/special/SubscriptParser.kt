package io.github.cafeteriaguild.lin.parser.parselets.special

import com.github.adriantodt.tartar.api.parser.InfixParser
import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.access.SubscriptAccessExpr
import io.github.cafeteriaguild.lin.ast.expr.access.SubscriptAssignExpr
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.Precedence
import io.github.cafeteriaguild.lin.parser.utils.maybeIgnoreNL

object SubscriptParser : InfixParser<TokenType, Expr> {
    override val precedence: Int = Precedence.POSTFIX

    override fun parse(ctx: ParserContext<TokenType, Expr>, left: Expr, token: Token<TokenType>): Expr {
        val arguments = mutableListOf<Expr>()

        if (!ctx.match(TokenType.R_BRACKET)) {
            do {
                arguments += ctx.parseExpression()
            } while (ctx.match(TokenType.COMMA))
            ctx.eat(TokenType.R_BRACKET)
        }

        val rBracket = ctx.last

        // TODO implement all the op-assign (plusAssign, etc)
        // TL;DR "a[b] += c" is completely valid...
        return if (ctx.match(TokenType.ASSIGN)) {
            val value = ctx.parseExpression()
            ctx.maybeIgnoreNL()
            SubscriptAssignExpr(left, arguments, value, left.span(value))
        } else {
            ctx.maybeIgnoreNL()
            SubscriptAccessExpr(left, arguments, left.span(rBracket))
        }
    }
}