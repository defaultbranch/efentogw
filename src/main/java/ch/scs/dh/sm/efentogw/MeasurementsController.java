package ch.scs.dh.sm.efentogw;

import ch.scs.dh.sm.efentogw.EfentoMeasurementMessage.Measurement;
import ch.scs.dh.sm.efentogw.EfentoMeasurementMessage.Measurement.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController()
public class MeasurementsController {

    public static final DateTimeFormatter EFENTO_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'");
    public static final String INFLUX_MEASUREMENT = "from_gateway";
    public static final ZoneId UTC_ZONE = ZoneId.of("UTC");
    private DataWriter influxOutput;

    @Autowired
    public MeasurementsController(DataWriter influxOutput) {
        this.influxOutput = influxOutput;
    }

    @PostMapping(path = "/api/v2/measurements", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void postMeasurements(@RequestBody EfentoMeasurementMessage data) {

        for (Measurement measurement : data.measurements) {

            Instant measured_at = LocalDateTime.parse(measurement.measured_at, EFENTO_DATE_FORMATTER).atZone(UTC_ZONE).toInstant();

            Map<String, String> measurementTags = new HashMap<>();
            measurementTags.put("serial", measurement.serial);

            for (Parameters param : measurement.params) {

                Map<String, String> paramTags = new HashMap<>();
                paramTags.putAll(measurementTags);
                paramTags.put("channel", "" + param.getChannel());

                Map<String, Double> doubleFields = new HashMap<>();
                doubleFields.put(param.getType(), param.getValue());

                influxOutput.writeData(INFLUX_MEASUREMENT, measured_at, paramTags, doubleFields);
            }
        }
    }
}
