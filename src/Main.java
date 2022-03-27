public class Main {
    private static final int NUMBER_OF_DELIVERIES = 10;
    private static final int NUMBER_OF_SELLERS = 10;
    private static final int TIME_BETWEEN_THREADS_SELLERS = 1000;
    private static final int TIME_BETWEEN_THREADS_DELIVERIES = 2000;
    private static final AutoShow shop = new AutoShow();

    public static void main(String[] args){
        new Thread(null, Main::createSellers).start();
        new Thread(null, Main::createDeliveries).start();
    }


    public static void createDeliveries() {
        for (int i = 0; i < NUMBER_OF_DELIVERIES; i++) {
            try {
                new Thread(shop::acceptCar).start();
                Thread.sleep(TIME_BETWEEN_THREADS_DELIVERIES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createSellers() {
        for (int i = 0; i < NUMBER_OF_SELLERS; i++) {
            try {
                new Thread(null, shop::sellCar, "Покупатель " + (i + 1)).start();
                Thread.sleep(TIME_BETWEEN_THREADS_SELLERS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
