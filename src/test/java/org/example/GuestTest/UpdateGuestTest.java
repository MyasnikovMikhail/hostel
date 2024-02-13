package org.example.GuestTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.dto.GuestDto;
import org.example.model.dto.GuestUpdDto;
import org.example.model.dto.RoomDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.example.model.TypeComfort.LUXE;
import static org.example.model.TypeGender.MEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateGuestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void updateGuestTest() throws Exception {
        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1, 20, MEN, LUXE, 1)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1, 1, MEN, LUXE, 1)))
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].flat", 1).exists())
                .andExpect(jsonPath("$[0].numberOfSeats", 1).exists())
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].flat", 20).exists())
                .andExpect(jsonPath("$[1].numberOfSeats", 0).exists());

        mockMvc.perform(
                        get("/guest-read-all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].numFlat", 20).exists())
                .andExpect(jsonPath("$[0].name", "Ivan").exists())
                .andExpect(jsonPath("$[0].surname", "Ivanov").exists())
                .andExpect(jsonPath("$[0].patronymic", "Ivanovich").exists());

        mockMvc.perform(
                        put("/guests/3")
                                .content(asJsonString(new GuestUpdDto(1, "Vlad", "Sidorov", "Pavlovich")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        get("/guest-read-all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].numFlat", 1).exists())
                .andExpect(jsonPath("$[0].name", "Vlad").exists())
                .andExpect(jsonPath("$[0].surname", "Sidorov").exists())
                .andExpect(jsonPath("$[0].patronymic", "Pavlovich").exists());

        mockMvc.perform(
                        get("/room-read-all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].flat", 1).exists())
                .andExpect(jsonPath("$[0].numberOfSeats", 0).exists())
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].flat", 20).exists())
                .andExpect(jsonPath("$[1].numberOfSeats", 1).exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
