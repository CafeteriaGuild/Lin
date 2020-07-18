package io.github.cafeteriaguild.lin.parser.parselets.special

import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.misc.IfExpr
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.utils.parseBlock

object IfParser : PrefixParser<TokenType, Expr> {
    override fun parse(ctx: ParserContext<TokenType, Expr>, token: Token<TokenType>): Expr {
        ctx.eat(TokenType.L_PAREN)
        val condition = ctx.parseExpression()
        ctx.eat(TokenType.R_PAREN)
        val thenBranch = ctx.parseBlock()

        val elseBranch = if (ctx.match(TokenType.ELSE)) {
            if (ctx.nextIs(TokenType.IF)) ctx.parseExpression()
            else ctx.parseBlock()
        } else {
            null
        }

        return IfExpr(condition, thenBranch, elseBranch, token.section)
    }
}