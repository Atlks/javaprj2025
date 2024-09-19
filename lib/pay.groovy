import static  lib.BscEncGrv.*;
import groovy.lang.GroovyShell
import  static  lib.BscEncGrv.*
def print888() {
    println 888999
}
//def Object evalFil(String funFil) {
//    def functions = new GroovyShell().evaluate(new File(funFil))
//    functions
//}
//def Object evalFilFuns() {
//
//    String funFil="BscImptSttkGrv.groovy"
//    def functions = new GroovyShell().evaluate(new File(funFil))
//    functions
//}

// 调用函数
print888()
println 111
println 222

// main.groovy

//Object fns = evalFil(funFil)

//Object bscCls = evalFil('BscGrv.groovy')


// 将函数直接引入 当前作用域  cant effect
//this.&encodeJsonGrv =fns .&encodeJsonGrv

//bscCls.hashCode()




def resultJson = encodeJsonGrv([key: 8855443])
println resultJson
//lib.BscEncGrv.encodeJsonGrv(333)

// 使用全局方法
//def map = [key: "value", number: 42]
//def jsonString = encodeJsonGrv(map)
//println jsonString