package ch.scs.dh.sm.efentogw;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Instant;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EfentogwApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void receivesEmptyData() throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/api/v2/measurements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"measurements\" : []}"));

        result.andExpect(status().isCreated());
    }

    @Test
    public void receivesAlmostEmptyData() throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/api/v2/measurements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"measurements\" : [{" +
                                "\"serial\" : \"282C024FFFB1\",\n" +
                                "\"response_handle\": 1,\n" +
                                "\"battery\" : \"ok\",\n" +
                                "\"signal\" : -70,\n" +
                                "\"measured_at\" : \"2018-10-12 15:28:21 UTC\",\n" +
                                "\"measurement_interval\" : 180,\n" +
                                "\"next_measurement_at\" : \"2018-10-12 18:28:21 UTC\",\n" +
                                "\"params\" : []}]}"));

        result.andExpect(status().isCreated());
    }

    @Test
    @Disabled
    public void receivesData() throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/api/v2/measurements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                "\"measurements\" : [\n" +
                "{\n" +
                "\"serial\" : \"282C024FFFB1\",\n" +
                "\"response_handle\": 1,\n" +
                "\"battery\" : \"ok\",\n" +
                "\"signal\" : -70,\n" +
                "\"measured_at\" : \"2018-10-12 15:28:21\",\n" +
                "\"measurement_interval\" : 180,\n" +
                "\"next_measurement_at\" : \"2018-10-12 18:28:21\",\n" +
                "\"params\" : [\n" +
                "{ \"channel\" : 1, \"type\" : \"temperature\", \"value\" : 6 }\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "\"serial\" : \"282C024FFFB2\",\n" +
                "\"response_handle\": 2,\n" +
                "\"battery\" : \"ok\",\n" +
                "\"signal\" : -70,\n" +
                "\"measured_at\" : \"2018-10-12 15:28:21\",\n" +
                "\"measurement_interval\" : 180,\n" +
                "\"next_measurement_at\" : \"2018-10-12 18:58:21\",\n" +
                "\"params\" : [\n" +
                "{ \"channel\" : 1, \"type\" : \"temperature\", \"value\" : 12},\n" +
                "{ \"channel\" : 2, \"type\" : \"humidity\", \"value\" : 51}\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "\"serial\" : \"282C024FFFB3\",\n" +
                "\"response_handle\": 3,\n" +
                "\"battery\" : \"ok\",\n" +
                "\"signal\" : -70,\n" +
                "\"measured_at\" : \"2018-10-12 15:28:21\",\n" +
                "\"measurement_interval\" : 180,\n" +
                "\"next_measurement_at\" : \"2018-10-12 20:28:21\",\n" +
                "\"params\" : [\n" +
                "{ \"channel\" : 1, \"type\" : \"temperature\", \"value\" : 50},\n" +
                "{ \"channel\" : 2, \"type\" : \"humidity\", \"value\" : 30},\n" +
                "{ \"channel\" : 3, \"type\" : \"pressure_diff\", \"value\" : 21 }\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "\"serial\" : \"282C024FFFB4\",\n" +
                "\"response_handle\": 4,\n" +
                "\"battery\" : \"ok\",\n" +
                "\"signal\" : -70,\n" +
                "\"measured_at\" : \"2018-10-12 15:28:21\",\n" +
                "\"measurement_interval\" : 180,\n" +
                "\"next_measurement_at\" : \"2018-10-12 16:28:21\",\n" +
                "\"params\" : [\n" +
                "]\n" +
                "}\n" +
                "]\n" +
                "}"));

        result.andExpect(status().isCreated());
    }

    @Test
    void canParseRealMessage() throws Exception {
        String message = "{\"measurements\":[{\"serial\":\"282C02402FAD\",\"response_handle\":0,\"battery\":\"ok\",\"signal\":-32,\"measurement_interval\":180,\"measured_at\":\"2020-12-11 10:30:00 UTC\",\"params\":[{\"channel\":1,\"type\":\"temperature\",\"value\":21.9}]},{\"serial\":\"282C02402FB1\",\"response_handle\":1,\"battery\":\"ok\",\"signal\":-66,\"measurement_interval\":180,\"measured_at\":\"2020-12-11 10:30:00 UTC\",\"params\":[{\"channel\":1,\"type\":\"temperature\",\"value\":23.0}]}]}";
        EfentoMeasurementMessage data = mapper.readValue(message, EfentoMeasurementMessage.class);
        System.out.println(data);
        new MeasurementsController(new DataWriter() {
            @Override
            public void writeData(String measurement, Instant timestamp, Map<String, String> tags, Map<String, Double> doubleField) {
                System.out.println(measurement);
                System.out.println(timestamp);
            }
        }).postMeasurements(data);
    }
}
