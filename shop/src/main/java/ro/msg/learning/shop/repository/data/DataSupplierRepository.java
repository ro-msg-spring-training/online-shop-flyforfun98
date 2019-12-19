package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.repository.SupplierRepository;

public interface DataSupplierRepository extends Repository<Supplier, Integer>, SupplierRepository {

    void delete(Supplier supplier);

    @Override
    default void remove(Supplier supplier){
        delete(supplier);
    }
}
