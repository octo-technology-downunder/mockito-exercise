Goal of this exercise is to get yourself familiar with the concept of Mock/Stub.

Why do you need mocks?

-	Sometimes it is just difficult to test a component, because it depends on other components which are not available
-	In general, while unit testing, you should focus on testing and don’t care about if other components work properly
-	You want to avoid side effects of making actual calls. e.g. Any modification in database or

It’s also referred as TestDouble.
Widely used TestDoubles are:
1.	Mock
2.	Spy
3.	Stub

Mocks are pre-programmed with expectations which form a specification of the calls they are expected to receive. 
They can throw an exception if they receive a call they don't expect and are checked during verification to ensure they got all the calls they were expecting. We can configure/override the behaviour of a method using the same syntax we would use with a mock.

public class CustomerServiceTest {

    @Mock
    private CustomerDao daoMock;

    @InjectMocks
    private CustomerService service;

    @Before
    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {

         //assertion here
    }

}


Stubs provide canned answers to calls made during the test, usually not responding at all to anything outside what's programmed in for the test.

@Test
public void whenStubASpy_thenStubbed() {
    List<String> list = new ArrayList<String>();
    List<String> spyList = Mockito.spy(list);

    assertEquals(0, spyList.size());

    Mockito.doReturn(100).when(spyList).size();
    assertEquals(100, spyList.size());
}

Spies are stubs that also record some information based on how they were called.
	Well, they are kind of mix of real objects and stub. By default, all the methods called on stub are going to be called on real objects. But Some of the behaviour of a spy could be mocked if needed.
	You can verify or track interactions with that spy.

@Spy
private CustomerDaoImpl daoSpy;
verify(daoSpy).save(any(Customer.class));





