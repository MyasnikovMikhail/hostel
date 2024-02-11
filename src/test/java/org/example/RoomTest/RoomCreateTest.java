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

import static com.datical.liquibase.ext.command.init.InitProjectCommandStep$FileTypeEnum.json;
import static org.example.model.TypeComfort.*;
import static org.example.model.TypeGender.MEN;
import static org.example.model.TypeGender.WOMEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createRoomTest() throws Exception {
        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(1,  20, MEN, LUXE, 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));


        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(10,6, WOMEN, STANDARD, 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));


        mockMvc.perform(
                        get("/room-read-all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].flat", 20).exists())
                .andExpect(jsonPath("$[0].dateOfChange", LocalDate.now()).exists())
                .andExpect(jsonPath("$[0].typeComfort", LUXE).exists())
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].floor", 10).exists())
                .andExpect(jsonPath("$[1].dateOfChange", LocalDate.now()).exists())
                .andExpect(jsonPath("$[1].typeGender", WOMEN).exists());
    }


    @Test
    public void createRoomFailTest() throws Exception {
        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(11,  4, MEN, LUXE, 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

        mockMvc.perform(
                        get("/room-read-all")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        mockMvc.perform(
                        post("/room-create")
                                .content(asJsonString(new RoomDto(10,  101, MEN, LUXE, 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

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
