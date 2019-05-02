package gl51.project.store

class MemoryProductStorage implements  ProductStorage {

    List<Product> products = []

    @Override
    void save(Product p) {
        this.products.add(p)
    }

    @Override
    void update(String id, Product p) {
        try{
            for(int i=0;i<products.size();i++)
            {
                if(products[i].id==id)
                {
                    products[i] = p
                }
            }
        }catch(Exception ex){
            println("no product with id "+id)
        }
    }

    @Override
    Product getByID(String id) {
        try{
            for(int i=0;i<products.size();i++)
            {
                if(products[i].id==id)
                {
                    return products[i]
                }
            }
        }catch(Exception ex){
            println("no product with id "+id)
        }
    }

    @Override
    void delete(String id) {
        try{
            products.remove(this.getByID(id))
        }catch(Exception ex){
            println("no product with id "+id)
        }
    }

    @Override
    List<Product> all() {
        return products
    }
}
