package ch.scs.dh.sm.efentogw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EfentogwApplicationTests {

    @Autowired
    private MockMvc mockMvc;

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
                                "\"measured_at\" : \"2018-10-12 15:28:21\",\n" +
                                "\"measurement_interval\" : 180,\n" +
                                "\"next_measurement_at\" : \"2018-10-12 18:28:21\",\n" +
                                "\"params\" : []}]}"));

        result.andExpect(status().isCreated());
    }

    @Test
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
}
