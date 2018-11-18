org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url('/supplies') {
            queryParameters {
                parameter day: 'abc'
            }
        }
    }
    response {
        status BAD_REQUEST()
    }
}
