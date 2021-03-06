package io.github.cafeteriaguild.lin.parser.parselets

import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.PrefixParser
import com.github.adriantodt.tartar.api.parser.SyntaxException
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.node.AccessExpr
import io.github.cafeteriaguild.lin.ast.node.Node
import io.github.cafeteriaguild.lin.ast.node.misc.InvalidNode
import io.github.cafeteriaguild.lin.ast.node.ops.PrefixAssignUnaryOperation
import io.github.cafeteriaguild.lin.ast.node.ops.UnaryAssignOperationType
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.Precedence
import io.github.cafeteriaguild.lin.parser.utils.matchAll
import io.github.cafeteriaguild.lin.parser.utils.maybeIgnoreNL

class PrefixAssignUnaryOperatorParser(private val operator: UnaryAssignOperationType) : PrefixParser<TokenType, Node> {
    override fun parse(ctx: ParserContext<TokenType, Node>, token: Token<TokenType>): Node {
        ctx.matchAll(TokenType.NL)
        val target = ctx.parseExpression(Precedence.PREFIX).let {
            it as? AccessExpr ?: return InvalidNode {
                section(token.section)
                child(it)
                error(SyntaxException("Expected an accessor", it.section))
            }
        }

        ctx.maybeIgnoreNL()
        return PrefixAssignUnaryOperation(target, operator, token.section)
    }
}