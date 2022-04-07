package com.example.checklist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import com.example.checklist.repo.TaskRepo;
import com.example.checklist.presenter.MainPresenter;
import com.example.checklist.views.contracts.View;
import com.example.checklist.repo.SharedPreferenceSingleton;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    View presenterInterface;
    @Mock
    TaskRepo model;
    @Mock
    SharedPreferenceSingleton preferenceSingleton;

    MainPresenter presenter;

    @Before
    public void setup(){
        presenter = new MainPresenter(presenterInterface, model);
    }

    @Test
    public void isMainPresenterNull(){
        assertNotNull(presenter);
    }

    @Test
    public void ableToAddTask() throws ExceededListSizeException {
        assert(presenter.addTask("el"));
    }
}