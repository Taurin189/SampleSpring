package com.spring.sample;

import com.spring.sample.Exception.NotFoundException;
import com.spring.sample.controller.ExceptionHandleController;
import com.spring.sample.controller.ToDoController;
import com.spring.sample.entity.ToDoEntity;
import com.spring.sample.service.ToDoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.exceptions.misusing.MockitoConfigurationException;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ToDoControllerTest {

    @Mock
    private ToDoService toDoService;

    @Spy
    private ExceptionHandleController exceptionHandleController;

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

        assertEquals("index.html", actual.getViewName());
        assertTrue(modelMap.containsKey("todo_list"));

        List<ToDoEntity> actualToDoEntityList = (List<ToDoEntity>) modelMap.get("todo_list");

        assertEquals(10, actualToDoEntityList.size());
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

    @Test
    public void indexWithNoToDoList() {
        List<ToDoEntity> entityList = new ArrayList<ToDoEntity>();
        when(toDoService.findAll()).thenReturn(entityList);
        ModelAndView mav = new ModelAndView();
        ModelAndView actual = toDoController.index(mav);
        ModelMap modelMap = actual.getModelMap();

        assertEquals("index.html", actual.getViewName());
        assertTrue(modelMap.containsKey("todo_list"));

        List<ToDoEntity> actualToDoEntityList = (List<ToDoEntity>) modelMap.get("todo_list");

        assertEquals(0, actualToDoEntityList.size());
        }

    @Test
    public void detail() {
        ToDoEntity entity = ToDoEntity.builder()
                .id(1)
                .todo("第1回目Springの勉強する")
                .personInCharge("Taurin189")
                .isFinished(false)
                .build();
        when(toDoService.findById(1)).thenReturn(entity);

        ModelAndView mav = new ModelAndView();
        ModelAndView actual = toDoController.detail(1, mav);
        ModelMap modelMap = actual.getModelMap();

        assertEquals("detail.html", actual.getViewName());
        assertTrue(modelMap.containsKey("todo"));

        ToDoEntity actualEntity = (ToDoEntity) modelMap.get("todo");

        assertEquals(new Integer(1), actualEntity.getId());
        assertEquals("第1回目Springの勉強する", actualEntity.getTodo());
        assertEquals("Taurin189", actualEntity.getPersonInCharge());
        assertFalse( actualEntity.isFinished());
    }

    @Test(expected = NotFoundException.class)
    public void detailNotFound() {
        when(toDoService.findById(10)).thenThrow(new NotFoundException("ID: " + String.valueOf(10) + " Not Found"));

        ModelAndView mav = new ModelAndView();
        ModelAndView actual = toDoController.detail(10, mav);
        ModelMap modelMap = actual.getModelMap();

        assertEquals("error.html", actual.getViewName());
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatus());
    }
}
