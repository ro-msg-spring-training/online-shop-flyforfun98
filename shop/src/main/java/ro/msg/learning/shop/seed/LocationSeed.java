package ro.msg.learning.shop.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

@Component
@RequiredArgsConstructor
public class LocationSeed {

    public LocationSeed(RepositoryFactory repositoryFactory) {

        LocationRepository locationRepository = repositoryFactory.locationRepository();

        if(locationRepository.findAll().isEmpty()){

            locationRepository.save(new Location("Location 1","Romania","Cluj-Napoca", "Cluj",
                    "George Baritiu 24",null,null));
            locationRepository.save(new Location("Location 2","Romania","Targu Mures", "Mures",
                    "Muncii 12",null,null));
            locationRepository.save(new Location("Location 3","Romania","Ploiesti", "Prahova",
                    "Cazarmii 9",null,null));
        }
    }
}
