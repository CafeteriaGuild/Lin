package io.github.cafeteriaguild.lin.parser.parselets.special

import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.SyntaxException
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.Node
import io.github.cafeteriaguild.lin.ast.expr.misc.IfExpr
import io.github.cafeteriaguild.lin.ast.expr.misc.IfNode
import io.github.cafeteriaguild.lin.ast.expr.misc.InvalidExpr
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.utils.matchAll
import io.github.cafeteriaguild.lin.parser.utils.parseBlock
import io.github.cafeteriaguild.lin.parser.utils.skipNewLinesUntil

object IfParser : PrefixParser<TokenType, Expr> {
    override fun parse(ctx: ParserContext<TokenType, Expr>, token: Token<TokenType>): Expr {
        ctx.matchAll(TokenType.NL)
        ctx.eat(TokenType.L_PAREN)
        ctx.matchAll(TokenType.NL)
        val condition = ctx.parseExpression().let {
            it as? Node ?: return InvalidExpr {
                section(token.section)
                child(it)
                error(SyntaxException("Expected a node but got a statement instead.", it.section))
            }
        }
        ctx.matchAll(TokenType.NL)
        ctx.eat(TokenType.R_PAREN)
        ctx.matchAll(TokenType.NL)
        val thenBranch = ctx.parseBlock() ?: ctx.parseExpression()

        ctx.skipNewLinesUntil(TokenType.ELSE)
        val elseBranch = if (ctx.match(TokenType.ELSE)) {
            ctx.matchAll(TokenType.NL)
            if (ctx.nextIs(TokenType.IF)) {
                ctx.parseExpression()
            } else {
                ctx.parseBlock() ?: ctx.parseExpression()
            }
        } else {
            null
        }

        if (thenBranch is Node && elseBranch is Node) {
            return IfNode(condition, thenBranch, elseBranch, token.section)
        }
        return IfExpr(condition, thenBranch, elseBranch, token.section)
    }
}