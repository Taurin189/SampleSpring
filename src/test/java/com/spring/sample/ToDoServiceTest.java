package com.spring.sample;

import com.spring.sample.Exception.NotFoundException;
import com.spring.sample.entity.ToDoEntity;
import com.spring.sample.repository.ToDoRepository;
import com.spring.sample.service.ToDoService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ToDoServiceTest {
    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveToDoEntity() {
        ToDoEntity toDoEntity = ToDoEntity.builder()
                .id(1)
                .todo("Springの勉強する")
                .personInCharge("Taurin189")
                .isFinished(false)
                .build();
        when(toDoRepository.save(any(ToDoEntity.class))).thenReturn(toDoEntity);
        ToDoEntity actual = toDoService.save(toDoEntity);

        verify(toDoRepository, times(1)).save(toDoEntity);

        assertEquals(new Integer(1), actual.getId());
        assertEquals("Springの勉強する", actual.getTodo());
        assertEquals("Taurin189", actual.getPersonInCharge());
        assertFalse(actual.isFinished());
    }

    @Test
    public void saveToDoEntityByString() {
        ToDoEntity toDoEntity = ToDoEntity.builder()
                .id(1)
                .todo("Springの勉強する")
                .personInCharge("Taurin189")
                .isFinished(false)
                .build();
        when(toDoRepository.save(any(ToDoEntity.class))).thenReturn(toDoEntity);
        ToDoEntity actual = toDoService.save("Springの勉強する","Taurin189");

        assertEquals("Springの勉強する", actual.getTodo());
        assertEquals("Taurin189", actual.getPersonInCharge());
        assertFalse(actual.isFinished());
    }

    @Test
    public void findAll() {
        List<ToDoEntity> entityList = new ArrayList<>();
        ToDoEntity toDoEntity;
        for(int i = 1; i <= 10; i++) {
            toDoEntity = ToDoEntity.builder()
                    .id(i)
                    .todo("第" + i + "回目Springの勉強する")
                    .personInCharge("Taurin189")
                    .isFinished(false)
                    .build();
            entityList.add(toDoEntity);
        }
        when(toDoRepository.findAll()).thenReturn(entityList);
        List<ToDoEntity> actual = toDoService.findAll();

        assertEquals(10, actual.size());
        assertEquals("第1回目Springの勉強する", actual.get(0).getTodo());
        assertEquals("Taurin189", actual.get(0).getPersonInCharge());
        assertEquals("第5回目Springの勉強する", actual.get(4).getTodo());
        assertEquals("Taurin189", actual.get(4).getPersonInCharge());
        assertEquals("第10回目Springの勉強する", actual.get(9).getTodo());
        assertEquals("Taurin189", actual.get(9).getPersonInCharge());
    }

    @Test
    public void findById() {
        ToDoEntity toDoEntity = ToDoEntity.builder()
                .id(1)
                .todo("Springの勉強する")
                .personInCharge("Taurin189")
                .isFinished(false)
                .build();

        when(toDoRepository.findById(any(Integer.class))).thenReturn(Optional.of(toDoEntity));
        ToDoEntity actual = toDoService.findById(1);

        verify(toDoRepository, times(1)).findById(1);
        assertEquals(new Integer(1), actual.getId());
        assertEquals("Springの勉強する", actual.getTodo());
        assertEquals("Taurin189", actual.getPersonInCharge());
        assertFalse(actual.isFinished());
    }

    @Test
    public void findByIdThroughNotFoundException() {

        when(toDoRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        try {
            ToDoEntity actual = toDoService.findById(1);
            verify(toDoRepository, times(1)).findById(1);
        } catch (NotFoundException e) {
            assertThat(e.getMessage(), equalTo("ID: 1 Not Found"));
        }
    }
}
