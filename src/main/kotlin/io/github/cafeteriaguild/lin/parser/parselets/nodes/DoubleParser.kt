package io.github.cafeteriaguild.lin.parser.parselets.nodes

import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Node
import io.github.cafeteriaguild.lin.ast.expr.nodes.DoubleExpr
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.utils.maybeIgnoreNL

object DoubleParser : PrefixParser<TokenType, Node> {
    override fun parse(ctx: ParserContext<TokenType, Node>, token: Token<TokenType>): Node {
        ctx.maybeIgnoreNL()
        return DoubleExpr(token.value.toDouble(), token.section)
    }
}