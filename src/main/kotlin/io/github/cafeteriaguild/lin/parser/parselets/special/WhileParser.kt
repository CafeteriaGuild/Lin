package io.github.cafeteriaguild.lin.parser.parselets.special

import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.misc.WhileExpr
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.utils.parseBlock

object WhileParser : PrefixParser<TokenType, Expr> {
    override fun parse(ctx: ParserContext<TokenType, Expr>, token: Token<TokenType>): Expr {
        ctx.eat(TokenType.L_PAREN)
        val condition = ctx.parseExpression()
        ctx.eat(TokenType.R_PAREN)
        val expr = ctx.parseBlock()
        return WhileExpr(condition, expr, token.section)
    }
}