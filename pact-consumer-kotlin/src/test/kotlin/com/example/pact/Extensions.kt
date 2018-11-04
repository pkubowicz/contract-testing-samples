package com.example.pact

import io.pactfoundation.consumer.dsl.LambdaDslJsonArray
import io.pactfoundation.consumer.dsl.LambdaDslObject

fun LambdaDslJsonArray.newObject(o: (LambdaDslObject) -> (Unit)): LambdaDslJsonArray {
    return this.`object`(o)
}
