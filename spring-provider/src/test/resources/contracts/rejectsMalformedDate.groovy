package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url('/supplies') {
            queryParameters {
                parameter day: 'abc'
            }
        }
        headers {
            contentType('application/json')
        }
    }
    response {
        status BAD_REQUEST()
    }
}
