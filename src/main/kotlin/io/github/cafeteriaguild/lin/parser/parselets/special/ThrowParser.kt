package io.github.cafeteriaguild.lin.parser.parselets.special

import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.SyntaxException
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.Node
import io.github.cafeteriaguild.lin.ast.expr.misc.InvalidExpr
import io.github.cafeteriaguild.lin.ast.expr.nodes.ThrowNode
import io.github.cafeteriaguild.lin.lexer.TokenType

object ThrowParser : PrefixParser<TokenType, Expr> {
    override fun parse(ctx: ParserContext<TokenType, Expr>, token: Token<TokenType>): Expr {
        val node = ctx.parseExpression().let {
            it as? Node ?: return InvalidExpr {
                section(token.section)
                child(it)
                error(SyntaxException("Expected a node but got a statement instead", it.section))
            }
        }

        return ThrowNode(node, token.section)
    }
}