package io.github.cafeteriaguild.lin.parser.parselets.special

import com.github.adriantodt.tartar.api.parser.InfixParser
import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.SyntaxException
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.access.PropertyAccessExpr
import io.github.cafeteriaguild.lin.ast.expr.access.PropertyAssignExpr
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.Precedence
import io.github.cafeteriaguild.lin.parser.utils.maybeIgnoreNL

object DotParser : InfixParser<TokenType, Expr> {
    override val precedence: Int = Precedence.POSTFIX

    override fun parse(ctx: ParserContext<TokenType, Expr>, left: Expr, token: Token<TokenType>): Expr {
        while (ctx.match(TokenType.NL)) {
            ctx.eat()
        }
        val identifier = ctx.eat()
        if (identifier.type == TokenType.IDENTIFIER) {
            val name = identifier.value

            // TODO implement all the op-assign (plusAssign, etc)
            return if (ctx.match(TokenType.ASSIGN)) {
                val value = ctx.parseExpression()
                ctx.maybeIgnoreNL()
                PropertyAssignExpr(left, name, value, token.span(value))
            } else {
                ctx.maybeIgnoreNL()
                PropertyAccessExpr(left, name, token.span(identifier))
            }
        }
        throw SyntaxException("Invalid identifier", identifier.section)
    }
}