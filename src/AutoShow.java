import java.util.ArrayList;
import java.util.List;

public class AutoShow {
    private final Seller seller = new Seller(this);
    List<Car> cars = new ArrayList<>();

    public void sellCar() {
        seller.sellCar();
    }

    public void acceptCar() {
            seller.receiveCar();
    }

    public List<Car> getCars() {
        return cars;
    }
}
