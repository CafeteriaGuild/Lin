package io.github.cafeteriaguild.lin.parser.parselets.special

import com.github.adriantodt.tartar.api.parser.InfixParser
import com.github.adriantodt.tartar.api.parser.ParserContext
import com.github.adriantodt.tartar.api.parser.SyntaxException
import com.github.adriantodt.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.node.Expr
import io.github.cafeteriaguild.lin.ast.node.Node
import io.github.cafeteriaguild.lin.ast.node.access.PropertyAccessExpr
import io.github.cafeteriaguild.lin.ast.node.invoke.InvokeExpr
import io.github.cafeteriaguild.lin.ast.node.invoke.InvokeLocalExpr
import io.github.cafeteriaguild.lin.ast.node.invoke.InvokeMemberExpr
import io.github.cafeteriaguild.lin.ast.node.misc.InvalidNode
import io.github.cafeteriaguild.lin.ast.node.nodes.IdentifierExpr
import io.github.cafeteriaguild.lin.ast.node.nodes.LambdaExpr
import io.github.cafeteriaguild.lin.lexer.TokenType
import io.github.cafeteriaguild.lin.parser.Precedence

object LambdaInvocationParser : InfixParser<TokenType, Node> {
    override val precedence: Int = Precedence.POSTFIX

    override fun parse(ctx: ParserContext<TokenType, Node>, left: Node, token: Token<TokenType>): Node {
        if (left !is Expr) {
            return InvalidNode {
                section(token.section)
                child(left)
                error(SyntaxException("Expected an expression", left.section))
            }
        }
        ctx.back()
        val expr = ctx.parseExpression().let {
            it as? LambdaExpr ?: return InvalidNode {
                section(token.section)
                child(it)
                error(SyntaxException("Expected a lambda", it.section))
            }
        }

        if (left is PropertyAccessExpr) {
            return InvokeMemberExpr(left.target, left.nullSafe, left.name, listOf(expr), token.section)
        } else if (left is IdentifierExpr) {
            return InvokeLocalExpr(left.name, listOf(expr), token.section)
        }

        return InvokeExpr(left, listOf(expr), token.section)
    }
}