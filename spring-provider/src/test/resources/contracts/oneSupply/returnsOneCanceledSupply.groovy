package oneSupply

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url('/supplies') {
            queryParameters {
                parameter day: '2018-12-23'
            }
        }
        headers {
            contentType('application/json')
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
            jsonPath('$', byCommand('checkSizeIs1($it)'))
        }
        headers {
            contentType('application/json')
        }
    }
}
