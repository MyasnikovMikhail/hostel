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

import static org.example.model.TypeComfort.*;
import static org.example.model.TypeGender.MEN;
import static org.example.model.TypeGender.WOMEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FilterGuestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void filtersGuestsTest() throws Exception {
        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1, 1, MEN, LUXE, 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1, 2, WOMEN, STANDARD, 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1, 3, MEN, STANDARD, 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1, 4, WOMEN, DE_LUXE, 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(1, "Ivan", "Ivanov", "Ivanovich", MEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(2, "Dasha", "Ivanova", "Ivanovna", WOMEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(3, "Sergey", "Ivanov", "Ivanovich", MEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(1, "Vlad", "Ivanov", "Ivanovich", MEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(4, "Sasha", "Ivanova", "Ivanovna", WOMEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        post("/guest-create")
                                .content(asJsonString(new GuestDto(4, "Masha", "Ivanova", "Ivanovna", WOMEN)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(
                        get("/guest-read-all/gender/MEN")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].numFlat", 1).exists())
                .andExpect(jsonPath("$[0].name", "Ivan").exists())
                .andExpect(jsonPath("$[0].surname", "Ivanov").exists())

                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].numFlat", 3).exists())
                .andExpect(jsonPath("$[1].name", "Sergey").exists())
                .andExpect(jsonPath("$[1].surname", "Ivanov").exists())

                .andExpect(jsonPath("$[2].id").exists())
                .andExpect(jsonPath("$[2].numFlat", 1).exists())
                .andExpect(jsonPath("$[2].name", "Vlad").exists())
                .andExpect(jsonPath("$[2].surname", "Ivanov").exists());

        mockMvc.perform(
                        get("/guest-read-all/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].numFlat", 1).exists())
                .andExpect(jsonPath("$[0].name", "Ivan").exists())
                .andExpect(jsonPath("$[0].surname", "Ivanov").exists())

                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].numFlat", 3).exists())
                .andExpect(jsonPath("$[1].name", "Vlad").exists())
                .andExpect(jsonPath("$[1].surname", "Ivanov").exists());

        mockMvc.perform(
                        get("/guest-read-all/comfort/STANDARD")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].numFlat", 2).exists())
                .andExpect(jsonPath("$[0].name", "Dasha").exists())
                .andExpect(jsonPath("$[0].surname", "Ivanova").exists())

                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].numFlat", 3).exists())
                .andExpect(jsonPath("$[1].name", "Sergey").exists())
                .andExpect(jsonPath("$[1].surname", "Ivanov").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
