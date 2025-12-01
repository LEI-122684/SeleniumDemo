package testsuite.lei122720.bookstore.category.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BookstoreCategoriesPage {

    // botão "Add New Category" (host <vaadin-button>, não o #button interno)
    private final SelenideElement addCategoryButton =
            $$("vaadin-button").findBy(text("Add New Category"));

    // notificação de sucesso
    private final SelenideElement successNotification =
            $(".v-Notification, .v-notification, .alert-success, vaadin-notification-card");

    /** Abre a aplicação Bookstore e espera pelo login */
    public BookstoreCategoriesPage openPage() {
        open("/"); // usa Configuration.baseUrl
        $("input[name='username']").should(appear);
        return this;
    }

    /** Clicar no botão de adicionar nova categoria */
    public BookstoreCategoriesPage goToCategoryForm() {
        // garantir que estamos no Admin (botão Add New Category visível)
        addCategoryButton.should(appear).shouldBe(enabled).click();

        // pequena pausa para o formulário ser renderizado
        sleep(1000);
        return this;
    }

    /**
     * Preenche o formulário com nome e descrição.
     *
     * Estratégia:
     *  - percorrer todos os elementos com shadowRoot
     *  - apanhar <input> e <textarea> visíveis (não password/hidden)
     *  - usar os dois primeiros como Name e Description.
     */
    public BookstoreCategoriesPage fillCategoryForm(String name, String description) {

        executeJavaScript(
                "const value1 = arguments[0];" +
                        "const value2 = arguments[1];" +
                        "const inputs = [];" +
                        "const all = document.querySelectorAll('*');" +
                        "for (const el of all) {" +
                        "  if (!el.shadowRoot) continue;" +
                        "  const inner = el.shadowRoot.querySelectorAll('input, textarea');" +
                        "  for (const inp of inner) {" +
                        "    const type = (inp.getAttribute('type') || '').toLowerCase();" +
                        "    const visible = !!(inp.offsetParent || inp.getClientRects().length);" +
                        "    if (visible && type !== 'password' && type !== 'hidden') {" +
                        "      inputs.push(inp);" +
                        "    }" +
                        "  }" +
                        "}" +
                        "if (inputs.length > 0) {" +
                        "  inputs[0].value = value1;" +
                        "  inputs[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "  inputs[0].dispatchEvent(new Event('change', {bubbles:true}));" +
                        "}" +
                        "if (inputs.length > 1) {" +
                        "  inputs[1].value = value2;" +
                        "  inputs[1].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "  inputs[1].dispatchEvent(new Event('change', {bubbles:true}));" +
                        "}",
                name, description
        );

        return this;
    }

    /** Submete o formulário de categoria */
    public BookstoreCategoriesPage submitCategory() {

        // clicar num botão de "guardar" baseado no texto
        executeJavaScript(
                "const candidates = document.querySelectorAll('vaadin-button, button');" +
                        "for (const el of candidates) {" +
                        "  const txt = (el.textContent || '').toLowerCase();" +
                        "  if (txt.includes('save') || txt.includes('add') || txt.includes('create')) {" +
                        "    el.click();" +
                        "    return;" +
                        "  }" +
                        "}" +
                        "throw new Error('Botão de guardar categoria não encontrado');"
        );

        return this;
    }

    /** Lê a mensagem de sucesso mostrada pela aplicação */
    public String getSuccessMessage() {
        return successNotification.shouldBe(visible).getText();
    }
}
