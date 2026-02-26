package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MeterTest {
    Meter meter = new Meter();

    @Test
    public void testSpeedCalc() {
        assertAll(
                () -> assertEquals(1.67, meter.calculateSpeed(100, 60))
        );
    }

}