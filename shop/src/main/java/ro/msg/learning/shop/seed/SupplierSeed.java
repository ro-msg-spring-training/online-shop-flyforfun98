package ro.msg.learning.shop.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.repository.SupplierRepository;

@Component
@RequiredArgsConstructor
public class SupplierSeed {


    public SupplierSeed(RepositoryFactory repositoryFactory) {

        SupplierRepository supplierRepository = repositoryFactory.supplierRepository();

        if(supplierRepository.findAll().isEmpty()){

            supplierRepository.save(new Supplier("Flavius", null));
            supplierRepository.save(new Supplier("Antonio", null));
        }
    }
}
