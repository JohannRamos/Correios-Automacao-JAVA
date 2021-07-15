import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CorreiosTest {

    public static WebDriver browser;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.manage().window().maximize();
    }



    @Test
    public void buscar_cep_e_endereco_com_sucesso() throws InterruptedException {

        this.browser.navigate().to("https://buscacepinter.correios.com.br/app/endereco/index.php");
        WebElement elementoInputEndereco = browser.findElement(By.name("endereco"));
        elementoInputEndereco.sendKeys("93048345");
        WebElement buttonBuscar = browser.findElement(By.name("btn_pesquisar"));
        buttonBuscar.click();

        Thread.sleep(500);
        WebElement elementoTextoLogradouro = browser.findElement(By.xpath("//td[@data-th='Logradouro/Nome']"));
        String retornaTextoLogradouro = elementoTextoLogradouro.getText();
        Assert.assertEquals("Avenida Feitoria - de 2351 a 3161 - lado ímpar", retornaTextoLogradouro);

        WebElement elementoTextoBairro = browser.findElement(By.xpath("//td[@data-th='Bairro/Distrito']"));
        String retornaTextoBairro = elementoTextoBairro.getText();
        Assert.assertEquals("Feitoria", retornaTextoBairro);

        WebElement elementoTextoLocalidade = browser.findElement(By.xpath("//td[@data-th='Localidade/UF']"));
        String retornaTextoLocalidade = elementoTextoLocalidade.getText();
        Assert.assertEquals("São Leopoldo/RS", retornaTextoLocalidade);

        WebElement elementoTextoCep = browser.findElement(By.xpath("//td[@data-th='CEP']"));
        String retornaTextoCep = elementoTextoCep.getText();
        Assert.assertEquals("93048-345", retornaTextoCep);

    }
    @Test
    public void buscar_cep_invalido(){

        this.browser.navigate().to("https://buscacepinter.correios.com.br/app/endereco/index.php");
        WebElement elementoInputEndereco = browser.findElement(By.name("endereco"));
        elementoInputEndereco.sendKeys("bloodborne");
        WebElement buttonBuscar = browser.findElement(By.name("btn_pesquisar"));
        buttonBuscar.click();
    }
    @Test
    public void buscar_por_nome_logradouro(){

        this.browser.navigate().to("https://buscacepinter.correios.com.br/app/endereco/index.php");
        WebElement elementoInputEndereco = browser.findElement(By.name("endereco"));
        elementoInputEndereco.sendKeys("av imperatriz leopoldina");
        WebElement buttonBuscar = browser.findElement(By.name("btn_pesquisar"));
        buttonBuscar.click();
    }

    @After
    public void quit(){
        this.browser.quit();
    }

}
