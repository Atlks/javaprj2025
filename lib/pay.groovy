import static  lib.BscEncGrv.*;
import groovy.lang.GroovyShell
def print888() {
    println 888999
}

// 调用函数
print888()
println 111
println 222

// main.groovy
def funFil = 'BscEncGrv.groovy'
def functions = new GroovyShell().evaluate(new File(funFil))


// 将函数直接引入当前作用域  cant effect
//this.&encodeJsonGrv =functions .&encodeJsonGrv



// 现在可以直接使用 encodeJsonGrv
def resultJson = functions.encodeJsonGrv([key: 553])

println resultJson
//lib.BscEncGrv.encodeJsonGrv(333)

// 使用全局方法
//def map = [key: "value", number: 42]
//def jsonString = encodeJsonGrv(map)
//println jsonString