package oneSupply

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
                ]
        ])
        bodyMatchers {
            jsonPath('$', byType {
                minOccurrence(1)
                maxOccurrence(1)
            })
        }
        headers {
            contentType('application/json')
        }
    }
}
