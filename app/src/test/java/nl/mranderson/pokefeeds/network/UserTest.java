package nl.mranderson.pokefeeds.network;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class UserTest {

    private User user;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private LifespanCalculator lifespanCalculator;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void calculateRemainingLifespan() throws Exception {
        // Arrange
        user.setAge(40);
        when(lifespanCalculator.calculate(anyInt())).thenReturn(5);

        // Act
        int remainingLifespan = user.calculateRemainingLifespan(lifespanCalculator);

        // Assert
        assertThat(remainingLifespan, is(5));
    }
}
