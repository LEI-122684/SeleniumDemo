package testsuite.lei122720.bookstore.category;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testsuite.lei122720.bookstore.category.pages.BookstoreCategoriesPage;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookstoreCategoryTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://vaadin-bookstore-example.demo.vaadin.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @Epic("Bookstore")
    @Feature("Gestão de categorias")
    @Story("Adicionar uma nova categoria")
    @Severity(SeverityLevel.CRITICAL)
    void shouldAddNewCategorySuccessfully() {

        // 1) Abre a página inicial + inicializa a Page
        BookstoreCategoriesPage page = new BookstoreCategoriesPage()
                .openPage();

        // 2) Login admin/admin – usar sendKeys
        SelenideElement username = $("input[name='username']");
        username.click();
        username.sendKeys("admin");

        SelenideElement password = $("input[name='password']");
        password.click();
        password.sendKeys("admin");

        // botão de login
        $("vaadin-button[theme*='primary'], button[type='submit']").click();

        // 3) Clicar no link "Admin" no menu
        $("a.menu-link[href='Admin']").click();

        // 4) Clicar no botão de adicionar nova categoria
        page.goToCategoryForm();

        // 5) Preencher e submeter o formulário de nova categoria
        page.fillCategoryForm(
                        "Categoria Teste " + System.currentTimeMillis(),
                        "Criada automaticamente pelo teste de LEI-122720"
                )
                .submitCategory();

        // 6) Verificar mensagem de sucesso
        String msg = page.getSuccessMessage();

        assertTrue(
                msg != null &&
                        (msg.toLowerCase().contains("success")
                                || msg.toLowerCase().contains("categoria")
                                || msg.toLowerCase().contains("saved")),
                "A mensagem deve indicar que a categoria foi adicionada."
        );
    }
}
