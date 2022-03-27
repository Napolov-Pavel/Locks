
public class Seller {
    private final static int TIME_OF_SALE = 1000;
    private final static int TIME_OF_CAR_RECEPTION = 3000;
    private final static int POSITION_OF_THE_CAR_IN_AUTO_SHOP = 0;
    private final static int EMPTY_AUTO_SHOP = 0;

    private final AutoShow autoShop;

    public Seller(AutoShow autoShop) {
        this.autoShop = autoShop;
    }

    public synchronized void receiveCar() {
        try {
            Thread.sleep(TIME_OF_CAR_RECEPTION);
            autoShop.getCars().add(new Car("BMW"));
            System.out.println("Производитель BMW выпустил 1 автомобиль");
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (autoShop.getCars().size() == EMPTY_AUTO_SHOP) {
                System.out.println("Машин нету!");
                wait();
            }
            Thread.sleep(TIME_OF_SALE);
            System.out.println(Thread.currentThread().getName() + " уезжает на новом " + autoShop.getCars().get(POSITION_OF_THE_CAR_IN_AUTO_SHOP).getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return autoShop.getCars().remove(POSITION_OF_THE_CAR_IN_AUTO_SHOP);
    }
}
