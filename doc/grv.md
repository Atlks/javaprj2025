invk another file fun


在主脚本中使用这些函数


// main.groovy
def utils = new GroovyShell().evaluate(new File('utils.groovy'))

def resultAdd = utils.add(5, 3)





在 Groovy 中，如果你希望通过 evaluate 方法获取到其他文件中的函数，通常不需要显式地返回 this。不过，有些情况下，确实需要这样做，特别是当你在脚本中定义了多个函数，并且想要将它们全部导出时。

使用 return this 是一种确保在其他文件中定义的所有函数都可以被调用的好习惯。
如果你在脚本中仅定义了一个或两个函数，可能不需要添加 return this，但为了确保一致性和可读性，建议始终添加它。
通过这种方式，可以方便地在 Groovy 脚本之间共享和重用函数。