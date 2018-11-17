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
                ],
                [
                        count     : 4,
                        totalWeight: 20,
                        status    : "ACTIVE"
                ],
        ])
        bodyMatchers {
            jsonPath('$', byCommand('checkSizeIs2($it)'))
        }
        headers {
            contentType('application/json')
        }
    }
}
