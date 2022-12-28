package kakao.lango.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumMain {
    public static void main(String[] args) {
        // 드라이버 위치 설정하기
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        // 브라우저를 출력하지 않고 가져오기
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        // 드라이버 로드하기
//        WebDriver driver = new ChromeDriver(options);
        WebDriver driver = new ChromeDriver();
        // 사이트 접속하기
        driver.get("https://nid.naver.com/nidlogin.login");

        // 자바스크립트 실행하기
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // id, pw 입력하기
        js.executeScript("document.getElementsByName('id')[0].value=\'" + "xmun777" + "\'");
        js.executeScript("document.getElementsByName('pw')[0].value=\'" + "sodausrnltls1!" + "\'");
        // 로그인 버튼 클릭하기
        driver.findElement(By.xpath("//*[@id=\"log.login\"]")).click();
        // 네이버 카페 중고나라로 이동하기
        driver.get("https://cafe.naver.com/joonggonara");

        // 소스코드 가져오기
        System.out.println(driver.getPageSource());
    }
}
