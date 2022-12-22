package kakao.lango.thread;

import java.util.ArrayList;
import java.util.List;

// 공유 자원 클래스 만들기
class Product {
    // 실제로 사용할 공유 자원 List
    List<Character> list;

    // 공유 자원을 넘겨받기 위한 생성자 생성하기
    public Product(List<Character> list) {
        this.list = list;
    }

    // 공유 자원에 데이터를 삽입하는 메서드
    synchronized public void put(char ch) {
        list.add(ch);
        System.out.println(ch + "를 입고");
        try {
            Thread.sleep(1000);
        } catch (Exception e) { }
        System.out.println("재고 수량: " + list.size());
        // 대기중인 스레드에게 작업을 수행하도록 signal(신호)를 전송하기
        notify();
    }

    // 공유 자원을 소비하는 메서드
    synchronized public void get() {
        if (list.size() == 0) {
            try {
                // 해당 스레드를 notify가 호출될 때까지 대기시킨다.
                wait();
            } catch (Exception e) { }
        }
        // 첫번째 데이터 꺼내기
        Character ch = list.remove(0);
        System.out.println(ch + "를 출고");
        try {
            Thread.sleep(1000);
        } catch (Exception e) { }
        System.out.println("재고 수량: " + list.size());
    }
}

// 생산자 스레드 만들기
class Producer extends Thread {
    private Product product;

    public Producer(Product product) {
        this.product = product;
    }

    // 스레드로 수행할 내요을 작성한다.
    public void run() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            product.put(ch);
        }
    }
}

// 소비자 스레드 만들기
class Customer extends Thread {
    private Product product;

    public Customer(Product product) {
        this.product = product;
    }

    public void run() {
        for (int i = 0; i < 26; i++) {
            product.get();
        }
    }
}

public class ProducerComsumerMain {
    public static void main(String[] args) {
        // 공유 자원 생성하기
        List<Character> list = new ArrayList<>();
        Product product = new Product(list);

        // 생산자와 소비자 스레드 생성하기
        Producer producer = new Producer(product);
        Customer customer = new Customer(product);

        // 스레드 시작하기
        producer.start();
        customer.start();
    }
}
