package manySupplies

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url('/supplies') {
            queryParameters {
                parameter day: '2018-12-23'
            }
        }
    }
    response {
        status OK()
        body([
                [
                        status: "CANCELED",
                ],
                [
                        count     : 4,
                        totalWeight: 20,
                        status    : "ACTIVE"
                ],
        ])
        headers {
            contentType('application/json')
        }
    }
}





//bodyMatchers {
//    jsonPath('$', byType {
//        minOccurrence(1)
//        maxOccurrence(1)
//    })
//}
