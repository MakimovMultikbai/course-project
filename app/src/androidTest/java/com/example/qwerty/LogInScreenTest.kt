package com.example.qwerty

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.qwerty.navigation.NavRoutes
import com.example.qwerty.presentation.account.Authorization.LogInScreen
import com.example.qwerty.presentation.account.Authorization.LogInState
import com.example.qwerty.presentation.account.Authorization.LogInViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogInScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController
    private val mockViewModel: LogInViewModel = mockk(relaxed = true)

    @Before
    fun setup() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.navigatorProvider.addNavigator(ComposeNavigator())
        every { mockViewModel.state.value } returns LogInState()
    }
    @Test
    fun screen_displaysAllElements() {
        composeTestRule.setContent {
            LogInScreen(navController = navController, viewModel = mockViewModel)
        }
        composeTestRule.onNodeWithText("Вход").assertExists()
        composeTestRule.onNodeWithTag("email_field").assertExists()
        composeTestRule.onNodeWithTag("password_field").assertExists()
        composeTestRule.onNodeWithTag("login_button").assertExists()
        composeTestRule.onNodeWithText("Ещё не зарегистрированы?").assertExists()
        composeTestRule.onNodeWithTag("signup_link").assertExists()
    }
    @Test
    fun inputText_updatesViewModel() {
        composeTestRule.setContent {
            LogInScreen(navController = navController, viewModel = mockViewModel)
        }
        composeTestRule.onNodeWithTag("email_field").performTextInput("test@example.com")
        verify { mockViewModel.change_email("test@example.com") }
        composeTestRule.onNodeWithTag("password_field").performTextInput("password123")
        verify { mockViewModel.change_password("password123") }
    }
    @Test
    fun loginButton_triggersLogin() {
        composeTestRule.setContent {
            LogInScreen(navController = navController, viewModel = mockViewModel)
        }
        composeTestRule.onNodeWithTag("login_button").performClick()
        verify { mockViewModel.log_in() }
    }
    @Test
    fun successfulLogin_navigatesToMainScreen() {
        every { mockViewModel.state.value } returns LogInState(isComplete = true)
        composeTestRule.setContent {
            LogInScreen(navController = navController, viewModel = mockViewModel)
        }
        assertEquals(NavRoutes.MainNav.route, navController.currentBackStackEntry?.destination?.route)
    }
    @Test
    fun signUpLink_navigatesToSignUp() {
        composeTestRule.setContent {
            LogInScreen(navController = navController, viewModel = mockViewModel)
        }
        composeTestRule.onNodeWithTag("signup_link").performClick()
        assertEquals(NavRoutes.SignUpNav.route, navController.currentBackStackEntry?.destination?.route)
    }
}