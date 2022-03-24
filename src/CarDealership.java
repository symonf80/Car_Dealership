import java.util.ArrayList;
import java.util.List;

public class CarDealership {
    private static final int QUANTITY_CARS = 10;
    private static final int RECEIVE_TIME = 3000;
    private static final int SELL_TIME = 1200;
    private final List<Automaker> automakers = new ArrayList<>();

    public void receiveCar() {
        for (int i = 0; i < QUANTITY_CARS; i++) {
            try {
                Thread.sleep(RECEIVE_TIME);
                automakers.add(new Automaker());
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто.");
                synchronized (this) {
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            while (automakers.size() == 0) {
                System.out.println("Машин нет,санкции!");
                wait();
            }
            Thread.sleep(SELL_TIME);
            System.out.println(Thread.currentThread().getName() + " уехал на новом авто.");
            automakers.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
