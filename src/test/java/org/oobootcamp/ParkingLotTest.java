package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotTest {
    @Test
    void should_parking_succeed_when_parking_given_has_free_space() {
        ParkingLot parkingLot = new ParkingLot(30);
        assertThat(parkingLot.parking("A")).isEqualTo("succeed");
        assertThat(parkingLot.getFreeSpace()).isEqualTo(29);
    }

    @Test
    void should_parking_failed_when_parking_given_has_not_free_space() {
        ParkingLot parkingLot = new ParkingLot(0);
        assertThat(parkingLot.parking("A")).isEqualTo("failed");
    }

}
