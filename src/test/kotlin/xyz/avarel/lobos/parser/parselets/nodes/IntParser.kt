package xyz.avarel.lobos.parser.parselets.nodes

import xyz.avarel.lobos.ast.expr.Expr
import xyz.avarel.lobos.ast.expr.nodes.I32Expr
import xyz.avarel.lobos.lexer.Token
import xyz.avarel.lobos.parser.Modifier
import xyz.avarel.lobos.parser.Parser
import xyz.avarel.lobos.parser.PrefixParser

object IntParser : PrefixParser {
    override fun parse(parser: Parser, modifiers: List<Modifier>, token: Token): Expr {
        val value = token.string.toInt()
        return I32Expr(value, token.section)
    }
}
