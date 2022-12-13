import time

from pages import SearchHelper


def test_amazon_search(browser):
    amazon_search = SearchHelper(browser)
    amazon_search.go_to_site()
    amazon_search.enter_word("Hello")
    time.sleep(3)
    amazon_search.click_on_the_search_button()
    # elements = yandex_main_page.check_navigation_bar()
    # assert "Картинки" and "Видео" in elements
