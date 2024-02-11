package org.example.RoomTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.dto.RoomDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.example.model.TypeComfort.*;
import static org.example.model.TypeComfort.STANDARD;
import static org.example.model.TypeGender.MEN;
import static org.example.model.TypeGender.WOMEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FilterRoomTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void filtersRoomsTest() throws Exception {
        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1,  1, MEN, LUXE, 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));


        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1,2, WOMEN, STANDARD, 20)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1,3, MEN, DE_LUXE, 13)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        get("/room-read-all/gender/MEN")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].flat", 1).exists())
                .andExpect(jsonPath("$[0].dateOfChange", LocalDate.now()).exists())
                .andExpect(jsonPath("$[0].typeGender", MEN).exists())
                .andExpect(jsonPath("$[0].typeComfort", LUXE).exists())

                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].floor", 1).exists())
                .andExpect(jsonPath("$[1].typeGender", MEN).exists())
                .andExpect(jsonPath("$[1].typeComfort", DE_LUXE).exists());

        mockMvc.perform(
                        get("/room-read-all/comfort/STANDARD")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].floor", 2).exists())
                .andExpect(jsonPath("$[0].flat", 1).exists())
                .andExpect(jsonPath("$[0].numberOfSeats", 2).exists())
                .andExpect(jsonPath("$[0].typeGender", WOMEN).exists())
                .andExpect(jsonPath("$[0].typeComfort", STANDARD).exists());

        mockMvc.perform(
                        get("/room-read-all/available-seats")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numberOfSeats", 2).exists())
                .andExpect(jsonPath("$[1].numberOfSeats", 20).exists())
                .andExpect(jsonPath("$[2].numberOfSeats", 13).exists());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
