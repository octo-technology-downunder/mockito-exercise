
Goal of this exercise is to learn concepts of Mock/Stub.

# Why do you need mocks?

-	Sometimes it is just difficult to test a component, because it depends on other components which are not available
-	In general, while unit testing, you should focus on testing and don’t care about if other components work properly
-	You want to avoid side effects of making actual calls. e.g. Any modification in database
-   Mocks and stubs are fake Java classes that replace these external dependencies. These fake classes are then instructed before the test starts to behave as you expect.

This concept is also referred as TestDouble.
Widely used TestDoubles are:

1.	Mock
2.	Spy
3.	Stub

## Mock
- They are nothing but dummy class instead of the real one. 
- They pre-programmed with expectations which form a specification of the calls they are expected to receive. 
- They can throw an exception if they receive a call they don't expect and are checked during verification to ensure they got all the calls they were expecting. 
- We can configure/override the behaviour of any method.

Example: If you want to inject CustomerDao as a mock to your CustomerServiceTest, because you don't want to call actual save call on DAO:

```
public class CustomerServiceTest {

    @Mock
    private CustomerDao daoMock;

    @InjectMocks
    private CustomerService service;

    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }

}
```

## Stub

- Stubs provide fake answers to calls made during the test, usually not responding at all to anything outside what's programmed in for the test. 
- When stubbing a method, you don’t care if and how many times the method is going to be called; you just want it to return some value, or perform some side effect, whenever it gets called.
- Even though stubbing and mocking are two different things, Mockito uses "mocks" for everything. But this terminology may change based on test framework you use. But the syntax `when(something).thenReturn(somethingElse)` is usual way of stubbing. 

```
 public class CustomerReaderTest {

        //Class to be tested
        private CustomerReader customerReader;

        //Dependencies
        private EntityManager entityManager;

        @Before
        public void setup(){
            customerReader = new CustomerReader();

            entityManager = mock(EntityManager.class);
            customerReader.setEntityManager(entityManager);
        }

        @Test
        public void happyPathScenario(){
            Customer sampleCustomer = new Customer();
            sampleCustomer.setFirstName("Aditi");
            sampleCustomer.setLastName("Phadke");

            when(entityManager.find(Customer.class,1L)).thenReturn(sampleCustomer);

            String fullName = customerReader.findFullName(1L);
            assertEquals("Aditi Phadke",fullName);
        }
```

## Spy

- They are kind of mix of real objects and stub. By default, all the methods called on stub are going to be called on real objects. But Some of the behaviour of a spy could be mocked if needed.
- You can verify or track interactions with that spy.

```
@Spy
private CustomerDaoImpl daoSpy;
verify(daoSpy).save(any(Customer.class));
```

# Alert!!
Unit tests should treat the classes they test as black boxes. 
When you feel that you need to much of mocking or that it is impossible to create meaningful tests this way, it is a sign that your classes are too powerful and do too much. So it's the time to refactor your classes!

# Exercise 
Notice the structure of the project. Imagine that all the components that you have in `external` package are the one relying on some external service.
You have following tasks to accomplish:
1. Finish the implementation of `WareHouse`
2. Write one or more test cases for the `WareHouse` with following requirements: 
- Use Mock or Stub or Spy to inject dependencies as per needed.
- Create a list of 5 products in your test and use it to test `processProducts` method.


- Test if `GSTCalculator` is called 5 times.
- Test if `productRepository` is called with right method parameters
- Test if `EmailService` is called with right parameters
- Obtain caluclated prices with GST from the repository and verify the results for all 5 products.
