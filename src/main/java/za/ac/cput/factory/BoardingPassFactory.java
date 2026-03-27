package za.ac.cput.factory;

import za.ac.cput.domain.BoardingPass;
import za.ac.cput.domain.FlightBooking;

        public class BoardingPassFactory {

    public static BoardingPass createBoardingPass(FlightBooking flightBooking) {
        return new BoardingPass.Builder(flightBooking).build();
    }
}