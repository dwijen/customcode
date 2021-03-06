package multithreading.examples.deadlock;

public class DeadlockV2 {

    public static void main(String[] args) {
        DeadlockV2 dld = new DeadlockV2();
        dld.manifestDeadlock();
        DeadlockV1.detectDeadlockThreads();
    }

    public void manifestDeadlock(){
        String o1 = "Lock " ;
        String o2 = "Step ";

        Thread t1 = (new Thread("Printer1") {
            public void run() {
                while(true) {
                    synchronized(o1) {
                        System.out.println(Thread.currentThread().getName()+" waiting for o2");
                        synchronized(o2) {
                            System.out.println("Acquired o2");
                            System.out.println(o1 + o2);
                        }
                    }
                }
            }
        });

        Thread t2 = (new Thread("Printer2") {
            public void run() {
                while(true) {
                    synchronized(o2) {
                        System.out.println(Thread.currentThread().getName()+" waiting for o1");
                        synchronized(o1) {
                            System.out.println("Acquired o1");
                            System.out.println(o2 + o1);
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
