package pl.luxoft.simpletests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ListInterfaceMethodTest {

    @Test
    public void shouldAddAllElementsToTheList() {
        //arrange
        List<Integer> sourceList = List.of(1,2,3,4,5);
        List<Integer> target = new ArrayList<>();
        target.add(0);
        //act
        target.addAll(sourceList);
        //assert
        System.out.println(target);
        Assert.assertEquals(2, target.get(2));
    }
}
