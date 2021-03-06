package io.github.cafeteriaguild.lin.parser

import com.github.adriantodt.tartar.api.parser.SyntaxException
import com.github.adriantodt.tartar.createParser
import io.github.cafeteriaguild.lin.ast.node.Expr
import io.github.cafeteriaguild.lin.ast.node.Node
import io.github.cafeteriaguild.lin.ast.node.misc.InvalidNode
import io.github.cafeteriaguild.lin.ast.node.misc.MultiExpr
import io.github.cafeteriaguild.lin.ast.node.misc.MultiNode
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.utils.matchAll

@OptIn(ExperimentalStdlibApi::class)
val linStdParser = createParser(linStdGrammar) {
    val start = peek()
    val list = mutableListOf<Node>()
    matchAll(TokenType.NL, TokenType.SEMICOLON)
    val expr = try {
        do {
            list += parseExpression()
        } while (matchAll(TokenType.NL, TokenType.SEMICOLON) && !eof)

        if (list.isNotEmpty() && list.last() is Expr) {
            val last = list.removeLast() as Expr
            MultiExpr(list, last, start.section)
        } else
            MultiNode(list, start.section)

    } catch (e: Exception) {
        InvalidNode {
            section(start.section)
            child(*list.toTypedArray())
            error(e)
        }
    }

    if (!eof) InvalidNode {
        child(expr)
        error(SyntaxException("Should've reached end of content", eat().section))
    }
    else expr
}