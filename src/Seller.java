import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Seller {
    private final static int TIME_OF_SALE = 1000;
    private final static int TIME_OF_CAR_RECEPTION = 4000;
    private final static int POSITION_OF_THE_CAR_IN_AUTO_SHOP = 0;
    private final static int EMPTY_AUTO_SHOP = 0;

    private final AutoShow autoShop;
    private final Lock lock = new ReentrantLock(true);
    private final Condition conditionAddCar = lock.newCondition();

    public Seller(AutoShow autoShop) {
        this.autoShop = autoShop;
    }

    public void receiveCar() {
        try {
            lock.lock();
            Thread.sleep(TIME_OF_CAR_RECEPTION);
            autoShop.getCars().add(new Car("BMW"));
            System.out.println("Производитель BMW выпустил 1 автомобиль");
            conditionAddCar.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Car sellCar() {
        lock.lock();
        try {

            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (autoShop.getCars().size() == EMPTY_AUTO_SHOP && lock.tryLock()) {
                System.out.println("Машин нету! " + Thread.currentThread().getName());

                conditionAddCar.await();
            }
            Thread.sleep(TIME_OF_SALE);
            System.out.println(Thread.currentThread().getName() + " уезжает на новом " + autoShop.getCars().get(POSITION_OF_THE_CAR_IN_AUTO_SHOP).getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return autoShop.getCars().remove(POSITION_OF_THE_CAR_IN_AUTO_SHOP);
    }
}
