package nba.ui_application;

import nba.sbr.domain.MatchWithOdds;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DataServiceControllerTest {

    @Test
    public void name() {
        List<MatchWithOdds> matchWithOdds = DataServiceController.scrapeSbrOdds();

        int x = 1;
    }

    @Test
    public void name2() {
        new DataServiceController().getDayEvents();
    }
}