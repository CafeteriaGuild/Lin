package io.github.cafeteriaguild.lin.parser.grammar.nodes

import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.nodes.LongExpr
import io.github.cafeteriaguild.lin.lexer.TokenType

object LongParser : PrefixParser<TokenType, Expr> {
    override fun parse(parser: ParserContext<TokenType, Expr>, token: Token<TokenType>): Expr {
        return LongExpr(token.value.toLong(), token.section)
    }
}