package io.github.cafeteriaguild.lin.parser.parselets.nodes

import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.nodes.LongNode
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.utils.maybeIgnoreNL

object LongParser : PrefixParser<TokenType, Expr> {
    override fun parse(ctx: ParserContext<TokenType, Expr>, token: Token<TokenType>): Expr {
        ctx.maybeIgnoreNL()
        return LongNode(token.value.toLong(), token.section)
    }
}