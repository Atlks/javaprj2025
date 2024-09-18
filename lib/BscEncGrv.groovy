//  BscEncGrv.groovy
package lib

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

def encodeJsonGrv(Object map) throws JsonProcessingException {
    def objectMapper = new ObjectMapper()
    return objectMapper.writeValueAsString(map)
}


// 返回函数供外部调用
//must have ,,if not have ,invike file cant get hadl...
return this