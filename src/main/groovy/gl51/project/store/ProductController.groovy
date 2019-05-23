package gl51.project.store

import io.micronaut.http.annotation.*
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body


@Controller("/products")
class ProductController {
    MemoryProductStorage storage = new MemoryProductStorage()

    @Post("/")
    String create(@Body Product p){
        //HttpStatus.OK
        storage.save(p)
    }

    @Get("/")
    List<Product> index() {
        HttpStatus.OK
        storage.all()
    }

    @Get("/{id}")
    Product get(String id) {
        try {
            storage.getByID(id)
        }
        catch(NoSuchElementException e) {
            null
        }
    }

    @Put("/{id}")
    void update(@Body Product p,@Body String id){
        HttpStatus.OK
        storage.update(id, p)
    }

    @Delete("/{id}")
    void delete(@Body String id){
        HttpStatus.OK
        storage.delete(id)
    }

}