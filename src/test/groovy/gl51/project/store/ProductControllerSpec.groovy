package gl51.project.store

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification


class ProductControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    def newProduct = new Product(name: "One")
//
//    def setup() {
//        someProducts.each {  save(it)}
//    }


    void "test empty index"() {
        given:
        List<Product> response = client.toBlocking().retrieve(HttpRequest.GET('/products'), Argument.listOf(Product).type)

        expect:
        response ==[]
    }

    void "test create"() {
        setup:
        Product newProduct = new Product(name: name, description: description, price: price, idealTemperature: idealTemperature)

        when:
        String id = client.toBlocking().retrieve(HttpRequest.POST('/products', newProduct))
        Product getProduct = client.toBlocking().retrieve(HttpRequest.GET('/products/'+id), Argument.of(Product).type)

        then:
        getProduct.name = newProduct.name
        getProduct.description = newProduct.description
        getProduct.price = newProduct.price
        getProduct.idealTemperature = newProduct.idealTemperature

        where:
        name | description | price | idealTemperature
        "WU" | "ying" | 0.0 | 123000
    }

//
//    void "test read by id"() {
//        given:
//        def response = client.toBlocking().retrieve(HttpRequest.GET('/products'), Argument.listOf(Product).type)
//
//        expect:
//        response.status == HttpStatus.OK
//        response.body()==[]
//    }
//
//    void "update one product"() {
//        given:
//        def response = client.toBlocking().retrieve(HttpRequest.GET('/products'), Argument.listOf(Product).type)
//
//        expect:
//        response.status == HttpStatus.OK
//        response.body()==[]
//    }
//
//    void "delete one product"() {
//        given:
//        def response = client.toBlocking().retrieve(HttpRequest.GET('/products'), Argument.listOf(Product).type)
//
//        expect:
//        response.status == HttpStatus.OK
//        response.body()==[]
//    }

}
