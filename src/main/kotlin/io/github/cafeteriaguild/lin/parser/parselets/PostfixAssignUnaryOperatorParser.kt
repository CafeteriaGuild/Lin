package io.github.cafeteriaguild.lin.parser.parselets

import com.github.adriantodt.tartar.api.parser.InfixParser
import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.SyntaxException
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.node.AccessExpr
import io.github.cafeteriaguild.lin.ast.node.Node
import io.github.cafeteriaguild.lin.ast.node.misc.InvalidNode
import io.github.cafeteriaguild.lin.ast.node.ops.PostfixAssignUnaryOperation
import io.github.cafeteriaguild.lin.ast.node.ops.UnaryAssignOperationType
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.Precedence
import io.github.cafeteriaguild.lin.parser.utils.maybeIgnoreNL

class PostfixAssignUnaryOperatorParser(private val operator: UnaryAssignOperationType) : InfixParser<TokenType, Node> {
    override val precedence: Int = Precedence.POSTFIX
    override fun parse(ctx: ParserContext<TokenType, Node>, left: Node, token: Token<TokenType>): Node {
        if (left !is AccessExpr) {
            return InvalidNode {
                section(token.section)
                child(left)
                error(SyntaxException("Expected an accessor", left.section))
            }
        }
        ctx.maybeIgnoreNL()
        return PostfixAssignUnaryOperation(left, operator, token.section)
    }
}