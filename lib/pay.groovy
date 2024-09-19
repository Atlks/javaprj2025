import static  lib.BscEncGrv.*;
import groovy.lang.GroovyShell
def print888() {
    println 888999
}
def Object evalFil(String funFil) {
    def functions = new GroovyShell().evaluate(new File(funFil))
    functions
}
def Object evalFilFuns() {

    String funFil="BscImptSttkGrv.groovy"
    def functions = new GroovyShell().evaluate(new File(funFil))
    functions
}

// 调用函数
print888()
println 111
println 222

// main.groovy
def funFil = 'BscEncGrv.groovy'
//Object fns = evalFil(funFil)

//Object bscCls = evalFil('BscGrv.groovy')


// 将函数直接引入 当前作用域  cant effect
//this.&encodeJsonGrv =fns .&encodeJsonGrv

//bscCls.hashCode()


// 现在可以直接使用 encodeJsonGrv
fns=evalFilFuns()
def resultJson = fns.encodeJsonGrv([key: 55443])

println resultJson
//lib.BscEncGrv.encodeJsonGrv(333)

// 使用全局方法
//def map = [key: "value", number: 42]
//def jsonString = encodeJsonGrv(map)
//println jsonString