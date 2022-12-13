from base_app import BasePage
from selenium.webdriver.common.by import By


class AmazonSearchLocators:
    LOCATOR_AMAZON_SEARCH_FIELD = (By.ID, "twotabsearchtextbox")
    LOCATOR_AMAZON_SEARCH_BUTTON = (By.CLASS_NAME, "nav-input nav-progressive-attribute")
    # LOCATOR_YANDEX_NAVIGATION_BAR = (By.CSS_SELECTOR, ".service__name")


class SearchHelper(BasePage):
    def enter_word(self, word):
        search_field = self.find_element(AmazonSearchLocators.LOCATOR_AMAZON_SEARCH_FIELD)
        search_field.click()
        search_field.send_keys(word)
        return search_field

    def click_on_the_search_button(self):
        return self.find_element(AmazonSearchLocators.LOCATOR_AMAZON_SEARCH_BUTTON).submit()

    def check_navigation_bar(self):
        # all_list = self.find_elements(AmazonSearchLocators.LOCATOR_YANDEX_NAVIGATION_BAR, time=2)
        # nav_bar_menu = [x.text for x in all_list if len(x.text) > 0]
        # return nav_bar_menu
        pass
