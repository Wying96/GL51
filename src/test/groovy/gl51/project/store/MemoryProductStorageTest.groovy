package gl51.project.store

import spock.lang.Specification

class MemoryProductStorageTest extends Specification {

    ProductStorage store = new MemoryProductStorage()

    def "empty storage returns empty list"() {
         expect:
         store.all() ==  []
    }

    def "adding a product returns the product in the list"() {
        setup:
        store.save(new Product(name: "myproduct"))

        when:
        def all = store.all()

        then:
        all.size() == 1
        all.first().name == 'myproduct'
    }

    def "update a product in the list returns a new list"() {
        store.save(new Product(id: "1", name: "myproduct"))
        Product newProduct = new Product(
                id: "10",
                name: "myproduct updated",
                description: "new product",
                price: 2.50,
                idealTemperature: 10)
        setup:
        store.update("1", newProduct)

        when:
        def proInStore = store.getByID(newProduct.id)

        then:
        proInStore == newProduct
    }

    def "get a product by id"() {
        store.save(new Product(id: "1", name: "myproduct"))
        expect:
        store.getByID("1").name=='myproduct'
    }

    def "delete a product by id"() {
        store.save(new Product(id: "1", name: "myproduct"))
        store.delete("1")
        expect:
        store.all() == []
    }

}
