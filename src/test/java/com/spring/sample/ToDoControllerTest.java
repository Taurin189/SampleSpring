package com.spring.sample;

import com.spring.sample.controller.ToDoController;
import com.spring.sample.entity.ToDoEntity;
import com.spring.sample.service.ToDoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ToDoControllerTest {

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private ToDoController toDoController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void index() {
        List<ToDoEntity> entityList = new ArrayList<ToDoEntity>();
        ToDoEntity entity;
        for (int i = 1; i <= 10; i++) {
            entity = ToDoEntity.builder()
                    .id(i)
                    .todo("第" + i + "回目Springの勉強する")
                    .personInCharge("Taurin189")
                    .isFinished(false)
                    .build();
            entityList.add(entity);
        }
        when(toDoService.findAll()).thenReturn(entityList);
        ModelAndView mav = new ModelAndView();
        ModelAndView actual = toDoController.index(mav);
        ModelMap modelMap = actual.getModelMap();
        List<ToDoEntity> actualToDoEntityList = (List<ToDoEntity>) modelMap.get("todo_list");

        assertEquals("index.html", actual.getViewName());
        assertTrue(modelMap.containsKey("todo_list"));
        assertEquals(new Integer(1), actualToDoEntityList.get(0).getId());
        assertEquals("第1回目Springの勉強する", actualToDoEntityList.get(0).getTodo());
        assertEquals("Taurin189", actualToDoEntityList.get(0).getPersonInCharge());
        assertEquals(new Integer(5), actualToDoEntityList.get(4).getId());
        assertEquals("第5回目Springの勉強する", actualToDoEntityList.get(4).getTodo());
        assertEquals("Taurin189", actualToDoEntityList.get(4).getPersonInCharge());
        assertEquals(new Integer(10), actualToDoEntityList.get(9).getId());
        assertEquals("第10回目Springの勉強する", actualToDoEntityList.get(9).getTodo());
        assertEquals("Taurin189", actualToDoEntityList.get(9).getPersonInCharge());
    }
}
