//  BscEncGrv.groovy
package lib

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

def Object evalFil(String funFil) {
    def functions = new GroovyShell().evaluate(new File(funFil))
    functions
}
def encodeJsonGrv(Object map) throws JsonProcessingException {

    return evalFil('BscEncGrv.groovy').encodeJsonGrv(map);
}


//abt top 150 fun

return this