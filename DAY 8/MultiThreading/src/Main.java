public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello World");
        System.out.println("The program is running in "+Thread.currentThread().getName()+" Thread");

        MyThread myThread = new MyThread();
        myThread.setName("MyThread");
        //myThread.start();

        Thread t = new Thread(new MyThreadVer_2(),"MyThread_ver2");
        // t.start();

//        for (int i=0;i<100;i++){
//            System.out.println(i+" from "+Thread.currentThread().getName()+" Thread");
//            try{
//                Thread.sleep(1000);
//            }catch (InterruptedException e){
//                e.getStackTrace();
//            }
//        }

//        Runnable runnable=()->{
//            for (int i=0;i<100;i++){
//                System.out.println(i+" from runnable"+Thread.currentThread().getName()+" Thread");
//                try{
//                    Thread.sleep(1000);
//                }catch (InterruptedException e){
//                    e.getStackTrace();
//                }
//            }
//        };

//       Counter counter = new Counter();
//       Thread t1 = new Thread(()->{
//           for (int i=0;i<100;i++){counter.increment();}
//        });
//
//        Thread t2 = new Thread(()->{
//            for (int i=0;i<100;i++){counter.increment();}
//        });
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//
//        System.out.println("Final count "+counter.getCount());

        SharedResource resource = new SharedResource();
        Thread producer = new Thread(()->{

            for(int i=0;i<5;i++)
            {
                try {
                    System.out.println("Resource Produed");
                    resource.produce(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(()->{
            for(int i=0;i<5;i++)
            {
                try {
                    System.out.println("Resource Consumed "+resource.consume());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}