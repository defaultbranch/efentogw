package ch.scs.dh.sm.efentogw;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@ApplicationScope
public class InfluxOutput {

    private InfluxDB influxDB;

    @PostConstruct
    public void connect() {
        final String serverURL = "http://127.0.0.1:8086";
        influxDB = InfluxDBFactory.connect(serverURL);
        influxDB.setDatabase("efento_data");
    }

    @PreDestroy
    public void disconnect() {
        influxDB.close();
    }

    public void writeData(
            String measurement,
            Instant timestamp,
            Map<String, String> tags,
            Map<String, Integer> intFields
    ) {
        Point.Builder builder = Point.measurement(measurement)
                .time(timestamp.toEpochMilli(), TimeUnit.MILLISECONDS)
                .tag(tags);
        intFields.entrySet().forEach(it -> builder.addField(it.getKey(), it.getValue().intValue()));
        influxDB.write(builder.build());
    }
}
