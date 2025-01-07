package tests.owner;

import configOwner.FruitsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FruitsTest {

    @Test
    public void testArrayFruits() {
        System.setProperty("array", "banana, apple");

        FruitsConfig fruitsConfig = ConfigFactory.create(FruitsConfig.class, System.getProperties());

        assertThat(fruitsConfig.getFruitsArray()).containsExactly("banana", "apple");

    }

    @Test
    public void testListFruits() {
        System.setProperty("list", "apple, banana");

        FruitsConfig fruitsConfig = ConfigFactory.create(FruitsConfig.class, System.getProperties());

        assertThat(fruitsConfig.getFruitsList()).containsExactly("apple", "banana");
    }

    @Test
    public void testListDefaultFruits() {
        FruitsConfig fruitsConfig = ConfigFactory.create(FruitsConfig.class, System.getProperties());

        assertThat(fruitsConfig.getFruitsArrayWithDefaultValues()).containsExactly("orange", "banana");
    }

    @Test
    public void testSeparatorFruits() {
        FruitsConfig fruitsConfig = ConfigFactory.create(FruitsConfig.class, System.getProperties());

        assertThat(fruitsConfig.getFruitsListSeparatorWithDefaultValues()).containsExactly("orange", "banana");
    }

}
