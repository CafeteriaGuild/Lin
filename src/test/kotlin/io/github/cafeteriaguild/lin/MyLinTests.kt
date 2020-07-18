package io.github.cafeteriaguild.lin

import com.github.adriantodt.tartar.api.lexer.Source
import io.github.cafeteriaguild.lin.ast.ASTViewer
import io.github.cafeteriaguild.lin.ast.expr.Node
import io.github.cafeteriaguild.lin.ast.expr.misc.InvalidExpr
import io.github.cafeteriaguild.lin.lexer.linStdLexer
import io.github.cafeteriaguild.lin.parser.linStdParser

fun main() {
    val source = Source(
        """
            return a ?: throw b
    """.trimIndent()
    )

    println(buildString {
        val expr = linStdParser.parse(source, linStdLexer)
        println(expr is Node)
        expr.accept(ASTViewer(this, "", true))
        if (expr is InvalidExpr) {
            for (child in expr.children.filterIsInstance<InvalidExpr>()) {
                for (error in child.errors) {
                    error.printStackTrace()
                }
            }
            for (error in expr.errors) {
                error.printStackTrace()
            }
        }
    })
}