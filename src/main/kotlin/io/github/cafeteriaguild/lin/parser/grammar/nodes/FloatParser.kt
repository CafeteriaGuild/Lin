package io.github.cafeteriaguild.lin.parser.grammar.nodes

import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.nodes.FloatExpr
import io.github.cafeteriaguild.lin.lexer.TokenType

object FloatParser : PrefixParser<TokenType, Expr> {
    override fun parse(parser: ParserContext<TokenType, Expr>, token: Token<TokenType>): Expr {
        return FloatExpr(token.value.toFloat(), token.section)
    }
}