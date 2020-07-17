package io.github.cafeteriaguild.lin.parser

import com.github.adriantodt.tartar.api.lexer.Section
import com.github.adriantodt.tartar.createParser
import com.github.adriantodt.tartar.extensions.ensureEOF
import io.github.cafeteriaguild.lin.ast.expr.misc.UnitExpr
import io.github.cafeteriaguild.lin.parser.grammar.linStdGrammar

val linStdParser = createParser(linStdGrammar) {
    if (eof) UnitExpr(Section(source, 0, 0, 0))
    else ensureEOF {
        try {
            parseStatements()
        } finally {
            while (!eof) {
                println(this.eat())
            }
        }
    }
}
