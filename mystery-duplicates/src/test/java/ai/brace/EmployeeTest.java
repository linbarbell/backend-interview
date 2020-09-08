package ai.brace;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    @Test // same as employee processor
    public void mainTest() {
        Employee a = new Employee("PUBLIC", "JOHN", "Q", "123-45-1234");
        Employee b = new Employee("ULRICH", "LARS", "X", "234-23-2342");
        Employee c = new Employee("PUBLIC", "JOHN", "Q", "123-45-1234");
        Employee d = new Employee("NICKS", "STEPHANIE", "L", "345-45-3453");

        final Map<Employee, Integer> duplicateCount = new HashMap<>();
        addEmployee(duplicateCount, a);
        addEmployee(duplicateCount, b);
        addEmployee(duplicateCount, c);
        addEmployee(duplicateCount, d);
        assertEquals(2, (int) duplicateCount.get(a));
    }

    @Test // test for having different spacing in name, same ssn, should be duplicate
    public void nameSpacingTest() {
        Employee a = new Employee("PUBLIC", "JOHN", "Q", "123-45-1234");
        Employee b = new Employee("PUBLIC", "JOHN Q", null, "123-45-1234");
        final Map<Employee, Integer> duplicateCount = new HashMap<>();
        addEmployee(duplicateCount, a);
        addEmployee(duplicateCount, b);
        assertEquals(2, (int) duplicateCount.get(a));
        assertEquals(2, (int) duplicateCount.get(b));
    }

    @Test // test for different names, same ssn, should be duplicate
    public void nameDifferenceTest() {
        Employee a = new Employee("a", "b", "c", "123-45-1234");
        Employee b = new Employee("d", "e", "f", "123-45-1234");
        final Map<Employee, Integer> duplicateCount = new HashMap<>();
        addEmployee(duplicateCount, a);
        addEmployee(duplicateCount, b);
        assertEquals(2, (int) duplicateCount.get(a));
        assertEquals(2, (int) duplicateCount.get(b));
    }

    @Test // test for same name, different ssn, should not be duplicate
    public void ssnDifferenceTest() {
        Employee a = new Employee("PUBLIC", "JOHN", "Q", "123-45-1234");
        Employee b = new Employee("PUBLIC", "JOHN", "Q", "123-45-1235");
        final Map<Employee, Integer> duplicateCount = new HashMap<>();
        addEmployee(duplicateCount, a);
        addEmployee(duplicateCount, b);
        assertEquals(1, (int) duplicateCount.get(a));
        assertEquals(1, (int) duplicateCount.get(b));
    }

    @Test // test everything is different, should not be duplicate
    public void allDifferenceTest() {
        Employee a = new Employee("a", "b", "c", "123-45-1235");
        Employee b = new Employee("d", "e", "f", "123-45-1234");
        final Map<Employee, Integer> duplicateCount = new HashMap<>();
        addEmployee(duplicateCount, a);
        addEmployee(duplicateCount, b);
        assertEquals(1, (int) duplicateCount.get(a));
        assertEquals(1, (int) duplicateCount.get(b));
    }

    @Test // test first null, should not be duplicate
    public void nullTest1() {
        Employee a = null;
        Employee b = new Employee("d", "e", "f", "123-45-1234");
        final Map<Employee, Integer> duplicateCount = new HashMap<>();
        addEmployee(duplicateCount, a);
        addEmployee(duplicateCount, b);
        assertEquals(1, (int) duplicateCount.get(a));
        assertEquals(1, (int) duplicateCount.get(b));
    }

    @Test // test second null, should not be duplicate
    public void nullTest2() {
        Employee a = new Employee("a", "b", "c", "123-45-1234");
        Employee b = null;
        final Map<Employee, Integer> duplicateCount = new HashMap<>();
        addEmployee(duplicateCount, a);
        addEmployee(duplicateCount, b);
        assertEquals(1, (int) duplicateCount.get(a));
        assertEquals(1, (int) duplicateCount.get(b));
    }

    @Test // test both null, should be duplicate
    public void nullTest3() {
        Employee a = null;
        Employee b = null;
        final Map<Employee, Integer> duplicateCount = new HashMap<>();
        addEmployee(duplicateCount, a);
        addEmployee(duplicateCount, b);
        assertEquals(2, (int) duplicateCount.get(a));
        assertEquals(2, (int) duplicateCount.get(b));
    }

    @Test // test different number of dashes but still the same ssn, should be duplicate
    public void ssnDashesTest() {
        Employee a = new Employee("PUBLIC", "JOHN", "Q", "123451234");
        Employee b = new Employee("PUBLIC", "JOHN", "Q", "123-45-1234");
        final Map<Employee, Integer> duplicateCount = new HashMap<>();
        addEmployee(duplicateCount, a);
        addEmployee(duplicateCount, b);
        assertEquals(2, (int) duplicateCount.get(a));
        assertEquals(2, (int) duplicateCount.get(b));
    }

    private void addEmployee(Map<Employee, Integer> dup, Employee emp) {
        dup.put(emp, dup.getOrDefault(emp, 0) + 1);
    }
}
