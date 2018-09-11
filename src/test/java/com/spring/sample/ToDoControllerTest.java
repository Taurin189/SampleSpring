package com.spring.sample;

import com.spring.sample.controller.ToDoController;
import com.spring.sample.entity.ToDoEntity;
import com.spring.sample.service.ToDoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i < 10; i++) {
            entity = ToDoEntity.builder()
                    .id(new Integer(1))
                    .todo("第" + i + "回目Springの勉強する")
                    .personInCharge("Taurin189")
                    .isFinished(false)
                    .build();
            entityList.add(entity);
        }
        ModelAndView mav = new ModelAndView();
        ModelAndView actual = toDoController.index(mav);
        when(toDoService.findAll()).thenReturn(entityList);
    }
}
