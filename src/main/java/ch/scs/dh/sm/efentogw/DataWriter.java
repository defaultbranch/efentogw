package ch.scs.dh.sm.efentogw;

import java.time.Instant;
import java.util.Map;

public interface DataWriter {
    void writeData(
            String measurement,
            Instant timestamp,
            Map<String, String> tags,
            Map<String, Double> doubleField
    );
}
