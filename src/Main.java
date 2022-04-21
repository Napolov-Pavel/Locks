public class Main {
    private static final int NUMBER_OF_DELIVERIES = 10;
    private static final int NUMBER_OF_SELLERS = 10;
    private static final int TIME_BETWEEN_THREADS_SELLERS = 500;
    private static final int TIME_BETWEEN_THREADS_DELIVERIES = 1000;
    private final AutoShow autoShow = new AutoShow();


    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        new Thread(null, main::createSellers).start();
        Thread.sleep(1000);
        new Thread(null, main::createDeliveries).start();
    }

    public void createDeliveries() {
        for (int i = 0; i < NUMBER_OF_DELIVERIES; i++) {
            try {
                autoShow.acceptCar();
                Thread.sleep(TIME_BETWEEN_THREADS_DELIVERIES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void createSellers() {
        for (int i = 0; i < NUMBER_OF_SELLERS; i++) {
            try {
                new Thread(null, autoShow::sellCar, "Покупатель " + (i + 1)).start();
                Thread.sleep(TIME_BETWEEN_THREADS_SELLERS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
