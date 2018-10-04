
Goal of this exercise is to learn concepts of Mock/Stub.

# Why do you need mocks?

-	Sometimes it is just difficult to test a component, because it depends on other components which are not available
-	In general, while unit testing, you should focus on testing and don’t care about if other components work properly
-	You want to avoid side effects of making actual calls. e.g. Any modification in database

This concept is also referred as TestDouble.
Widely used TestDoubles are:
    1.	Mock
    2.	Spy
    3.	Stub

## Mock
They are nothing but dummy class instead of the real one. They pre-programmed with expectations which form a specification of the calls they are expected to receive. 
They can throw an exception if they receive a call they don't expect and are checked during verification to ensure they got all the calls they were expecting. 
We can configure/override the behaviour of any method.

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

Stubs provide fake answers to calls made during the test, usually not responding at all to anything outside what's programmed in for the test.

```
@Test
public void whenStubASpy_thenStubbed() {
    List<String> list = new ArrayList<String>();
    List<String> spyList = Mockito.spy(list);

    assertEquals(0, spyList.size());

    Mockito.doReturn(100).when(spyList).size();
    assertEquals(100, spyList.size());
}
```

## Spy

They are kind of mix of real objects and stub. By default, all the methods called on stub are going to be called on real objects. But Some of the behaviour of a spy could be mocked if needed.
- You can verify or track interactions with that spy.

```
@Spy
private CustomerDaoImpl daoSpy;
verify(daoSpy).save(any(Customer.class));
```



# Exercise 
Notice the structure of the project. Imagine that all the components that you have in `external` package are the one relying on some external service.
You have following tasks to accomplish:
1. Finish the implementation of `WareHouse`
2. Write a test case for the `WareHouse`. 
- Use Mock or Stub or Spy to inject dependencies.
- Create a list of 5 products in your test and use it to test `processProducts` method.
- Test if `GSTCalculator` is called 5 times.
- Test if `productRepository` is called with right method parameters
- Test if `EmailService` is called with right parameters

