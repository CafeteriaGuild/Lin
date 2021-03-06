//package io.github.cafeteriaguild.lin.rt
//
//import io.github.cafeteriaguild.lin.ast.expr.Expr
//import io.github.cafeteriaguild.lin.ast.expr.ExprParamVisitor
//import io.github.cafeteriaguild.lin.ast.expr.access.AssignExpr
//import io.github.cafeteriaguild.lin.ast.expr.access.PropertyAccessExpr
//import io.github.cafeteriaguild.lin.ast.expr.access.PropertyAssignExpr
//import io.github.cafeteriaguild.lin.ast.expr.invoke.InvokeExpr
//import io.github.cafeteriaguild.lin.ast.expr.invoke.InvokeLocalExpr
//import io.github.cafeteriaguild.lin.ast.expr.invoke.InvokeMemberExpr
//import io.github.cafeteriaguild.lin.ast.expr.misc.InvalidExpr
//import io.github.cafeteriaguild.lin.ast.expr.misc.MultiExpr
//import io.github.cafeteriaguild.lin.ast.expr.misc.UnitExpr
//import io.github.cafeteriaguild.lin.ast.expr.nodes.*
//import io.github.cafeteriaguild.lin.rt.lib.*
//import io.github.cafeteriaguild.lin.rt.scope.FunctionScope
//import io.github.cafeteriaguild.lin.rt.scope.Scope
//
//class LinRuntime : ExprParamVisitor<Scope, LObj> {
//    override fun visit(node: NullExpr, param: Scope) = (LNull)
//
//    override fun visit(node: IntExpr, param: Scope) = LInt(expr.value)
//
//    override fun visit(node: LongExpr, param: Scope) = LLong(expr.value)
//
//    override fun visit(node: FloatExpr, param: Scope) = LFloat(expr.value)
//
//    override fun visit(node: DoubleExpr, param: Scope) = LDouble(expr.value)
//
//    override fun visit(node: BooleanExpr, param: Scope) = LBoolean(expr.value)
//
//    override fun visit(node: AssignExpr, param: Scope): LObj {
//        param.findProperty(expr.name).set(expr.value.accept(this, param))
//        return LUnit
//    }
//
//    override fun visit(node: IdentifierExpr, param: Scope): LObj {
//        return param.findProperty(expr.name).get()
//    }
//
//    override fun visit(node: ReturnExpr, param: Scope): LObj {
//        throw ReturnException(expr.value.accept(this, param))
//    }
//
//    override fun visit(node: CharExpr, param: Scope) = (LChar(expr.value))
//
//    override fun visit(node: StringExpr, param: Scope) = (LString(expr.value))
//
//    override fun visit(node: UnitExpr, param: Scope) = (LUnit)
//
//    override fun visit(node: MultiExpr, param: Scope): LObj {
//        val childScope = FunctionScope(param)
//        return try {
//            expr.list.fold<Expr, LObj>(LUnit) { _, it -> it.accept(this, childScope) }
//        } catch (r: ReturnException) {
//            r.obj
//        }
//
//    }
//
//    override fun visit(node: InvalidExpr, param: Scope): LObj {
//        TODO("Not yet implemented")
//    }
//
//    override fun visit(node: PropertyAccessExpr, param: Scope): LObj {
//        TODO("Not yet implemented")
//    }
//
//    override fun visit(node: PropertyAssignExpr, param: Scope): LObj {
//        TODO("Not yet implemented")
//    }
//
//    override fun visit(node: InvokeExpr, param: Scope): LObj {
//        TODO("Not yet implemented")
//    }
//
//    override fun visit(node: InvokeLocalExpr, param: Scope): LObj {
//        val f = param.findLocalFunction(expr.name)
//        return f.call(LUnit, expr.arguments.map { it.accept(this, param) })
//    }
//
//    override fun visit(node: InvokeMemberExpr, param: Scope): LObj {
//        val target = expr.target.accept(this, param)
//        val name = expr.name
//        val ef = param.findExtensionFunctions(target.type, name)
//        if (ef != null) {
//            val f = ef
//            return f.call(target, expr.arguments.map { it.accept(this, param) })
//        }
//        val mf = target.type.memberFunctions[name]
//        if (mf != null) {
//            val f = mf
//            return f.call(target, expr.arguments.map { it.accept(this, param) })
//        }
//        throw RuntimeException("Function ${target.type}.$name does not exist.")
//    }
//
//}