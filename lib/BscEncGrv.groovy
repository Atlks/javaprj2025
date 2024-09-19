//  BscEncGrv.groovy
package lib

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

def static encodeJsonGrv(Object map) throws JsonProcessingException {
    def objectMapper = new ObjectMapper()
    return objectMapper.writeValueAsString(map)
}

