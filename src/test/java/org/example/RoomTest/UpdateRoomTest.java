package org.example.RoomTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.dto.GuestDto;
import org.example.model.dto.RoomDto;
import org.example.model.dto.RoomUpdDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.example.model.TypeComfort.LUXE;
import static org.example.model.TypeComfort.STANDARD;
import static org.example.model.TypeGender.MEN;
import static org.example.model.TypeGender.WOMEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateRoomTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void updateRoomTest() throws Exception {
        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1, 20, MEN, LUXE, 1)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(20, "Ivan", "Ivanov", "Ivanovich", MEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        get("/room-read-all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].flat", 20).exists())
                .andExpect(jsonPath("$[0].typeGender", "MEN").exists())
                .andExpect(jsonPath("$[0].numberOfSeats", 0).exists())
                .andExpect(jsonPath("$[0].typeComfort", LUXE).exists());

        mockMvc.perform(
                        put("/rooms/20")
                                .content(asJsonString(new RoomUpdDto(2,WOMEN,STANDARD,4)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        get("/room-read-all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].flat", 2).exists())
                .andExpect(jsonPath("$[0].typeGender", "MEN").exists())
                .andExpect(jsonPath("$[0].numberOfSeats", 3).exists())
                .andExpect(jsonPath("$[0].typeComfort", STANDARD).exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
