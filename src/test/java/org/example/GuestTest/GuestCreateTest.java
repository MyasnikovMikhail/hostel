package org.example.GuestTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.dto.GuestDto;
import org.example.model.dto.RoomDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GuestCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createGuestTest() throws Exception {
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
                        post("/room-create")
                                .content(asJsonString(new RoomDto(10, 6, WOMEN, STANDARD, 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(6, "Dariya", "Ivanova", "Ivanovna", WOMEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        //-------------------------------------------

        mockMvc.perform(
                        get("/guest-read-all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].numFlat", 20).exists())
                .andExpect(jsonPath("$[0].dateOfChange", LocalDate.now()).exists())
                .andExpect(jsonPath("$[0].name", "Ivan").exists())
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].numFlat", 6).exists())
                .andExpect(jsonPath("$[1].dateOfChange", LocalDate.now()).exists())
                .andExpect(jsonPath("$[1].name", "Dariya").exists());
    }

    @Test
    public void createGuestFailTest() throws Exception {
        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1, 4, MEN, LUXE, 1)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(5, "Dariya", "Ivanova", "Ivanovna", WOMEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(4, "", "Ivanova", "Ivanovna", WOMEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));

        mockMvc.perform(
                        get("/guest-read-all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(4, "Sergey", "Ivanov", "Ivanovich", MEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(4, "Vlad", "Ivanov", "Ivanovich", MEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.info").value("В комнате нет свободных мест"));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
