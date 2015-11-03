/*
 * Copyright 2015 TOYAMA Sumio <jun.nama@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.jun_nama.sample.uiautomator2.espresso;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private UiDevice uiDevice;

    @Before
    public void setUp() {
        // UI Automator 2.xでは、このようにUiDeviceを初期化する。
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }


    /**
     * テスト対象アプリをEspressoで、それ以外のアプリをUI Automatorで操作するサンプルです。
     * 操作シナリオは以下の通りです。
     * <ol>
     * <li>テスト対象アプリの「設定アプリを表示する」ボタンを押して、設定アプリを表示します(Espresso)。</li>
     * <li>設定アプリから「日付と時刻」を探してタップします(UI Automator)。</li>
     * <li>「日付と時刻」画面から「24時間表示」を探して、24時間表示←→12時間表示を切り替えます(UI Automator)。</li>
     * <li>「戻る」ボタンを2回押して、テスト対象アプリに戻ります(UI Automator)。</li>
     * <li>テスト対象アプリのEditTextに、「Hello World!」と入力します(Espresso)。</li>
     * </ol>
     */
    @Test
    public void espresso_uiautomator() throws UiObjectNotFoundException {
        /* =============  テスト対象アプリの操作はEspressoでできる =========================== */
        // ボタンを押す。これで設定アプリに遷移する。
        onView(withId(R.id.button_setting)).perform(click());


        /* ==============  ここからは別アプリなので、UI Automatorで操作する =================== */
        // 「日付と時刻」を選択する。
        UiScrollable settingList = new UiScrollable(
                new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
        settingList.setAsVerticalList();
        UiObject dateTimeItem = settingList
                .getChildByText(new UiSelector().className(FrameLayout.class), "日付と時刻");
        dateTimeItem.clickAndWaitForNewWindow();

        // 「24時間表示」切り替える
        UiCollection dateTimeList = new UiCollection(new UiSelector().className(ListView.class));
        UiObject use24HourItem = dateTimeList
                .getChildByText(new UiSelector().className(LinearLayout.class), "24時間表示");
        use24HourItem.clickAndWaitForNewWindow();

        // 1画面前(設定アプリのメイン画面)に戻る
        uiDevice.pressBack();
        uiDevice.waitForIdle();

        // 更に1画面前(アプリ)に戻る
        uiDevice.pressBack();
        uiDevice.waitForWindowUpdate("jp.jun_nama.sample.uiautomator2.espresso", 1500L);

        /* ===============  ここからテスト対象アプリなので、Espressoで操作する ================== */
        // 「Hello World!」と入力する
        onView(withId(R.id.editText)).perform(typeText("Hello World!"));
    }
}
