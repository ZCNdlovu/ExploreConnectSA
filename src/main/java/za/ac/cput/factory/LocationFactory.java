 package za.ac.cput.factory;

import za.ac.cput.domain.Location;
import za.ac.cput.util.Helper;
import java.time.LocalDateTime;


    public class LocationFactory {

        public static Location createLocation(double latitude, double longitude, String address) {
            return new Location.Builder(latitude, longitude)
                    .setAddress(address)
                    .setTimestamp(LocalDateTime.now())
                    .build();
        }
    }