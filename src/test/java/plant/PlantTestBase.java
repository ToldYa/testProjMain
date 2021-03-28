package plant;

import com.vesa.testprojmain.AppServices;
import com.vesa.testprojmain.BaseProcedure;
import com.vesa.testprojmain.domain.Season;
import com.vesa.testprojmain.service.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class PlantTestBase {

    protected final String DEFAULT_PLANT_NAME = "defaultPlantName";
    protected final List<Season> DEFAULT_SEASONS =
            Arrays.asList(Season.FALL, Season.SPRING, Season.SUMMER, Season.WINTER);

    protected AppServices appServices;

    @Mock
    protected PlantService plantService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        appServices = new AppServices();

        appServices.setPlantService(plantService);

        when(plantService.isPlantRegistered()).thenReturn(true);

        BaseProcedure.setAppServices(appServices);
    }

}