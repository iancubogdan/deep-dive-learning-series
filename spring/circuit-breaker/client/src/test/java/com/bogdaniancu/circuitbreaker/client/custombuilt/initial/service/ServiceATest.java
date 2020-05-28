package com.bogdaniancu.circuitbreaker.client.custombuilt.initial.service;

//@SpringBootTest
//class ServiceATest {
//
//    @Autowired
//    public ServiceA serviceA;
//
//    @Test
//    public void test() throws InterruptedException {
//        serviceA.setFail(true);
//        for (int i = 0; i < 12; i++) {
//            if (i == 8) {
//                Thread.sleep(1002);
//                serviceA.setFail(false);
//            }
//            serviceA.call();
//            System.out.println("__________________________________________\n");
//        }
//    }
//
//}