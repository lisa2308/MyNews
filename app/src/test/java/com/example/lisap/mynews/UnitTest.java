package com.example.lisap.mynews;

import com.example.lisap.mynews.activities.SearchSettingsActivity;
import com.example.lisap.mynews.utils.AlarmReceiver;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    private SearchSettingsActivity searchSettingsActivity = new SearchSettingsActivity();

    @Test
    public void getDateTextFormatTest() {
        String actualDate = searchSettingsActivity.getDateTextFormat(2019, 6, 10);
        String expectedDate = "10/07/2019";

        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void getDateNYTFormatTest() {
        String actualDate = searchSettingsActivity.getDateNYTFormat(2019, 6, 10);
        String expectedDate = "20190710";

        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void getNotifTextTest(){
        AlarmReceiver alarmReceiver = new AlarmReceiver();

        String actualText = alarmReceiver.getNotifText(0);
        String expectedText = "Aucun article n'a été publié aujourd'hui";
        assertEquals(expectedText, actualText);

        actualText = alarmReceiver.getNotifText(1);
        expectedText = "1 nouvel article a été publié aujourd'hui";
        assertEquals(expectedText, actualText);

        actualText = alarmReceiver.getNotifText(2);
        expectedText = "2 nouveaux articles ont été publiés aujourd'hui";
        assertEquals(expectedText, actualText);
    }
}