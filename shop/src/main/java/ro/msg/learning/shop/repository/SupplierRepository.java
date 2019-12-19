package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository {

    Optional<Supplier> findById(int id);
    List<Supplier> findAll();
    Supplier save(Supplier supplier);
    void remove(Supplier supplier);
}
