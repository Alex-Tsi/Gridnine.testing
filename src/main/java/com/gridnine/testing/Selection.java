package com.gridnine.testing;

import java.util.List;

public interface Selection {
    List<Flight> beforeCurrentTime();

    List<Flight> arrivalBeforeDeparture();

    List<Flight> differentLandTime();
}
