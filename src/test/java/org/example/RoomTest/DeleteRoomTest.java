package org.example.RoomTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.dto.GuestDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteRoomTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deleteRoomsTest() throws Exception {
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
                        delete("/rooms/20")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.info").value("Еще не все жильцы выехали из комнаты"));

        mockMvc.perform(
                        delete("/guests/2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        delete("/rooms/20")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        get("/room-read-all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
