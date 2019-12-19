package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.repository.LocationRepository;

public interface DataLocationRepository extends Repository<Location, Integer>, LocationRepository {

    void delete(Location location);

    @Override
    default void remove(Location location){
        delete(location);
    }
}
