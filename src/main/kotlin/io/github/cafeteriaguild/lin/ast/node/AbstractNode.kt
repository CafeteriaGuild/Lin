package io.github.cafeteriaguild.lin.ast.node

import com.github.adriantodt.tartar.api.lexer.Section

abstract class AbstractNode(override val section: Section) : Node