package com.bogdaniancu.java.modernjavainaction.effectiveprogramming;

public class Refactoring {

    /**
     * converting anonymous classes to lambda expressions can be a difficult process in certain situations.1 First,
     * \the meanings of this and super are different for anonymous classes and lambda expressions. Inside an
     * anonymous class, this refers to the anony- mous class itself, but inside a lambda, it refers to the enclosing
     * class. Second, anony- mous classes are allowed to shadow variables from the enclosing class. Lambda expressions
     * can’t (they’ll cause a compile error), as shown in the following code:
     */

    public static void demo() {
        int a = 10;
        Runnable r1 = () -> {
           // int a = 2;  // Compile error!
            System.out.println(a);
        };
        Runnable r2 = new Runnable(){
            public void run(){
                int a = 2;
                System.out.println(a);
            }
        };
    }

    /**
     * converting an anonymous class to a lambda expression can make the resulting code ambiguous in the context of
     * overloading. Indeed, the type of anonymous class is explicit at instantiation, but the type of the lambda
     * depends on its context. Here’s an example of how this situation can be problematic. Suppose that you’ve declared
     * a functional interface with the same signature as Runnable, here called Task (as might occur when you need
     * more-meaningful interface names in your domain model):
     */


    interface Task {
        public void execute();
    }

    public static void doSomething(Runnable r){
        r.run();
    }

    public static void doSomething(Task a) {
        a.execute();
    }

    public static void demo2() {
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger danger!!");
            }
        });



        // =. Ambiguoue method call
//        doSomething(() -> System.out.println("Danger danger!!"));

        doSomething((Task)() -> System.out.println("Danger danger!!"));
    }


}
